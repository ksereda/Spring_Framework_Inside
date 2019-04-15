package quoters;

import org.springframework.beans.factory.config.BeanPostProcessor;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * beanName - никогда не меняется.
 * Когда на этапе Before из бина сделать getClass мы никогда не узнаем получим мы оригинал или прокси.
 *
 * На этапе Before: запоминать оригинальные классы тех бинов, для которых надо что-то сделать,
 * На этапе After: это что-то делать.
 *
 */

@Profiling
public class ProfilingHandlerBeanPostProcessor implements BeanPostProcessor {

    Map<String, Class> map = new HashMap();
    private ProfilingController controller = new ProfilingController();

    // Запоминаем оригинальные классы тех бинов, для которых надо что-то сделать,
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        Class<?> beanClass = bean.getClass();
        if (beanClass.isAnnotationPresent(Profiling.class)) {
            map.put(beanName, beanClass);
        }
        return bean;
    }

    // регистрируем контроллер в MBeanServer, т.к. он еще не зарегистрирован
    public ProfilingHandlerBeanPostProcessor() throws Exception {
        MBeanServer platformMBeanServer = ManagementFactory.getPlatformMBeanServer();
        platformMBeanServer.registerMBean(controller, new ObjectName("profiling", "name", "controller"));  // контроллер и его имя, по которому потом его можно будет найти через Consul
    }

    // Делаем что-то для бинов
    public Object postProcessAfterInitialization(final Object bean, String beanName) {
        Class beanClass = bean.getClass();
        if (beanClass != null) {
            return Proxy.newProxyInstance(beanClass.getClassLoader(), beanClass.getInterfaces(), new InvocationHandler() {
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    // здесь огика которая будет в каждом классе который мы создадим на лету
                    if (controller.isEnabled()) {   // если флаг включен, то делаем вот это, а если не включен, то просто вернуть прокси, который ничего не делает
                        System.out.println("Профилирую...");
                        long before = System.nanoTime();
                        Object retVal =  method.invoke(bean, args);   // args - аргументы оригинального метода
                        long after = System.nanoTime();
                        System.out.println(after - before);
                        System.out.println("Все");
                        return retVal;
                    } else {
                        return method.invoke(bean, args);
                    }

                }
            });  // создает объект из нового класса, который он сам сгенерирует на лету, где
                 // classLoader - при помощи него новый сгенерированный класс загрузится в кучу,
                 // getInterfaces - список интерфейсов, которые должен имплементировать тот класс который сгенерируется на лету,
                 // InvokationHandler - объект, который будет инкапсулировать логику, которая попадет во все методы класса, который сгенерируется на лету
        }
        return bean;
    }

}

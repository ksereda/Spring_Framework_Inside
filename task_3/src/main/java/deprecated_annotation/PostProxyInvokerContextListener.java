package deprecated_annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import java.lang.reflect.Method;

/**
 * Мы в класс инжекнули Спринговую фабрику. Это нормально, т.к. это не обычный бин, это ApplicationListener Spring-а.
 * В Spring инжекнуть Spring - это нормально!
 * Но если в свой самописный класс инжекнуть Spring - это ужас!
 *
 * Здесь мы имплементируем ApplicationListener и в дженериках указываем ContextRefreshedEvent - это значит что мы слушаем исключительно ContextRefreshedEvent !
 */

public class PostProxyInvokerContextListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private ConfigurableListableBeanFactory factory;  // Зачем она нужна? Т.к. только она умеет делать getBeanDefinition().
    // Просто вытаскивать бин (как ниже) бесполезно и неправильно. Например: кто-то создал бин lazy (singleton-ы по умолчанию все создаются,
    // но если он lazy то он не будет создаваться, а только тогда, когда его попросят). Мы сейчас будем проходиться по всем бинам (а вдруг у него есть метод,
    // аннотированный как @PostProxy и его надо запустить. И получится что мы создадим бин, который не должен сейчас создаваться.
    // Поэтому неправильно вытаскивать сами бины. Мы будем вытаскивать их BeanDefinitions и уже в них искать ту информацию, которая нас интересует !!!

    public void onApplicationEvent(ContextRefreshedEvent event) {
        ApplicationContext context = event.getApplicationContext();
        String[] names = context.getBeanDefinitionNames();  // достаем имена всех бинов, и по каждому будем проходить и у каждого будем проверять, если в его классе стояла @PostProxy
        for (String name : names) {
            // Мы не можем сейчас по имени бина вытаскивать бин и делать у него getClass, т.к. на этом этапе там уже будет прокси.
            // Соответственно, когда мы сделаем getClass() у того бина, который там сейчас, это будет явно класс, имя которого "$Proxy6" (см. предыдущий модуль).
            // Поэтому будем общаться с главной фабрикой Spring - ConfigurableListableBeanFactory
            BeanDefinition beanDefinition = factory.getBeanDefinition(name);
            String originalClassName = beanDefinition.getBeanClassName();  // можем достать оригинальное название класса (которое еще в XML прописали) !
            try {
                Class<?> originalClass =  Class.forName(originalClassName);  // получаем объект класса
                Method[] methods = originalClass.getMethods();  // достаем все его методы
                for (Method method : methods) {
                    if (method.isAnnotationPresent(PostProxy.class)) {
                        //todo этот метод надо запустить
//                        method.invoke();  // так отработает только через CGLib, но через DynamicProxy нет. Т.к. метод мы ищем в оригинальном классе, а у нас сейчас бин создан из прокси класса. Это 2 разных класса.
                        // Нам надо вытащить метод у текущего класса. Для этого вначале надо вытащить сам bean
                        Object bean = context.getBean(name);  // получаем bean по имени
                        Method currentMethod = bean.getClass().getMethod(method.getName(), method.getParameterTypes());  // вытащим класс у этого бина ("$Proxy7")
                        currentMethod.invoke(bean);  // запускаем метод
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}

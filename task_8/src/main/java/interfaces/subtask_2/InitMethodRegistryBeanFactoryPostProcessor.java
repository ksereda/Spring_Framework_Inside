package interfaces.subtask_2;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

import javax.annotation.PostConstruct;
import java.lang.reflect.Method;

/**
 * Аннотации в интерфейсах НЕ работают!
 * Но как сделать чтобы работали ?
 * Можно написать свой BeanPostProcessor или BeanFactoryPostProcessor.
 *
 * Напишем свой BeanFactoryPostProcessor.
 * BeanPostProcessor содержит 2 метода: 1 вызывается до init() метода, второй после.
 * Как может быть такое, что BeanPostProcessor и отвечает за вызов PostConstruct ???
 * Идеологически было бы правильней сделать чтобы не BeanPostProcessor отвечал за запуск PostConstruct, а чтобы еще на этапе до PostConstruct
 * (т.е. на этапе создания BeanDefinitions) кто-то находил, что у него есть PostConstruct через аннотация @PostConstruct над методом
 * и записывал эту информацию в BeanDefinitions (как будто это прописали в XML)
 *
 * Напишем эту логику здесь.
 *
 */

@Component
public class InitMethodRegistryBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        String[] names = beanFactory.getBeanDefinitionNames();
        for (String name : names) {
            AbstractBeanDefinition beanDefinition = (AbstractBeanDefinition) beanFactory.getBeanDefinition(name);  // получаем каждый BeanDefinition
            String beanClassName = beanDefinition.getBeanClassName();  // вытаскиваем название класса из BeanDefinition (из какого класса будет создаваться бин)
            Class<?> beanClass = ClassUtils.resolveClassName(beanClassName, ClassLoader.getSystemClassLoader());
            Class<?>[] allInterfacesForClass = ClassUtils.getAllInterfaces(beanClass);// получаем все интерфейсы, которые имплементирует данный бин
            for (Class<?> ifc : allInterfacesForClass) {
                Method[] methods = ifc.getMethods();  // получаем все методы интерфейса
                for (Method method : methods) {
                    if (method.isAnnotationPresent(PostConstruct.class)) {  // если метод аннотирован аннотацией @PostConstruct
                        // то мы его не запускаем, а просто зарегистрируем в этот BeanDefinition
                        beanDefinition.setInitMethodName(method.getName());
                    }
                }
            }
        }
    }

}

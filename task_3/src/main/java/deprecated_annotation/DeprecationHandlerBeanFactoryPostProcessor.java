package deprecated_annotation;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * Опасно для production :)
 * т.к. мы прописали один бин, на него можем сделать proxy, а в итоге нам приходит совершенно другой класс, т.к. наш BeanFactoryPostProcessor подкрутил
 * BeanDefinition и заменил.
 *
 * Удобно использовать, например, когда надо проверить, как система будет вести себя после определенного действия (например предвидеть, что будет после миграции)
 *
 * Listener - работает на этапе, когда все уже создано (context уже refreshed)
 * BeanFactoryPostProcessor - работает на этапе, когда кроме BeanDefinition-ов еще ничего нету, бины еще не создались.
 *
 */

public class DeprecationHandlerBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {  // используем ConfigurableListableBeanFactory, т.к.
        // Зачем она нужна? Т.к. только она умеет делать getBeanDefinition().
        // Просто вытаскивать бин бесполезно и неправильно.
        // Например: кто-то создал бин lazy (singleton-ы по умолчанию создаются все, но если он lazy то он не будет создаваться сразу, а только тогда,
        // когда его попросят). Может получится что мы создадим бин, который не должен сейчас создаваться.
        // Поэтому неправильно вытаскивать сами бины. Мы будем вытаскивать их BeanDefinitions и уже в них искать ту информацию, которая нас интересует !

        String[] names = beanFactory.getBeanDefinitionNames();   // достаем имена всех бинов и пробегаем по всем именам
        for (String name : names) {
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(name);
            String beanClassName = beanDefinition.getBeanClassName();
            try {
                Class<?> beanClass = Class.forName(beanClassName);
                DeprecatedClass annotation = beanClass.getAnnotation(DeprecatedClass.class);  // вытаскиваем аннотацию @DeprecatedClass
                if (annotation != null) {
                    beanDefinition.setBeanClassName(annotation.newImpl().getName());  // Если она есть, то бин устарел, и в его BeanDefinition надо установить новое имя для класса, которые мы возьмем из этой аннотации.
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}

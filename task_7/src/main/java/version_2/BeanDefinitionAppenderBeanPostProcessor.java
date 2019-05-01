package version_2;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class BeanDefinitionAppenderBeanPostProcessor implements BeanPostProcessor {

    @Autowired
    private ConfigurableListableBeanFactory factory;


    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Arrays.stream(factory.getBeanDefinitionNames()).parallel().forEach(name -> {
            if (neededMainBehaviour(bean)) {
                BeanDefinition beanDefinition = factory.getBeanDefinition(beanName);
                if (beanDefinition.getBeanClassName() == null) {
                    beanDefinition.setBeanClassName(bean.getClass().getCanonicalName());
                }
            }
        });
        return bean;
    }

    // Будем чинить только те бины, которые имеют аннотацию @Main
    private boolean neededMainBehaviour(Object bean) {
        return Arrays.stream(bean.getClass().getMethods()).anyMatch(method -> method.isAnnotationPresent(Main.class));
    }


    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}

package deprecated_annotation;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.util.ReflectionUtils;
import java.lang.reflect.Field;
import java.util.Random;

public class InjectRandomIntAnnotationBeanPostProcessor implements BeanPostProcessor {

    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        Field[] fields = bean.getClass().getDeclaredFields();  // достаем все fields
        for (Field field : fields) {
            InjectRandomInt annotation = field.getAnnotation(InjectRandomInt.class);  // если над полем есть @InjectRandomInt
            if (annotation != null) {
                int min = annotation.min();  // достаем min у аннотации
                int max = annotation.max();  // достаем max у аннотации
                Random random = new Random();
                int i = min + random.nextInt(max - min);
                field.setAccessible(true);
//                field.set(i);  // придется обрабатывать try/catch, мы также не сможем сделать throws т.к. имплементируем чужой интерфейс. Если чужой интерфейс не кидает исключения, то и мы не можем этого сделать по контракту.
                ReflectionUtils.setField(field, bean, i);  // field - для какого филда будем давать значение, bean - для какого объекта field надо будет засунуть это, i - значение
            }
        }
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

}

package interfaces.subtask_3;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.core.type.classreading.MethodMetadataReadingVisitor;
import java.lang.reflect.Field;

public class MyHelpUtilClass {

    public  static String resolveClassNameFromJavaConfig(BeanDefinition beanDefinition) {
        try {
            Object reader = Class.forName("org.springframework.context.annotation.ConfigurationClassBeanDefinitionReader$ConfigurationClassBeanDefinition")   // это внутренний вложенный приватный чей-то класс (обращаемся к нему через $)
                    .cast(beanDefinition);   // закастили
            Field field = reader.getClass().getDeclaredField("factoryMethodMetadata");  // в классе есть поле factoryMethodMetadata, которому надо установить флаг = true
            field.setAccessible(true);
            MethodMetadataReadingVisitor visitor = (MethodMetadataReadingVisitor) field.get(reader);
            return visitor.getReturnTypeName();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

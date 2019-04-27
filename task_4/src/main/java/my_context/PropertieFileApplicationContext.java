package my_context;

import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.context.support.GenericApplicationContext;

public class PropertieFileApplicationContext extends GenericApplicationContext {

    // с помощью PropertiesBeanDefinitionReader будем
    public PropertieFileApplicationContext(String fileName) {
        PropertiesBeanDefinitionReader reader = new PropertiesBeanDefinitionReader(this);  // он принимает в конструкторе тот context, для которого он будет регистрировать бины, когда он будет их находить, когда мы его запустим.
        int result = reader.loadBeanDefinitions(fileName);  // загружаем все BeanDefinitions, которые указаны в файле fileName. Метод возвращает кол-во бинов, которые он нашел.
        System.out.println("Found " + result + " beans");
        refresh();  // обязательно сделать refresh, после того, как закончили добавлять бины в context
    }

}

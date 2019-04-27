package three_phase_constructor;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * context.getBeanDefinitionNames() - имена всех бинов
 * context.getBean(my_context.Quoter.class).getClass() - мы увидим, что его название "com.sun.proxy.$Proxy6".
 */

public class Main {

    public static void main(String[] args) throws Exception  {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("web2.xml");  // имплементация этого контекста анализируется XmlBeanDefenitionsReader-ом
    }

}

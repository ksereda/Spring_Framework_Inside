package three_phase_constructor;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * context.getBeanDefinitionNames() - имена всех бинов
 * context.getBean(Quoter.class).getClass() - мы увидим, что его название "com.sun.proxy.$Proxy6".
 */

public class Main {

    public static void main(String[] args) throws Exception  {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("web.xml");  // имплементация этого контекста анализируется XmlBeanDefenitionsReader-ом
    }

}

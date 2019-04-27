package my_context;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * context.getBeanDefinitionNames() - имена всех бинов
 * context.getBean(my_context.Quoter.class).getClass() - мы увидим, что его название "com.sun.proxy.$Proxy6".
 */

public class Main {

    public static void main(String[] args) {
        PropertieFileApplicationContext context2 = new PropertieFileApplicationContext("context.properties");
        context2.getBean(Quoter.class).sayQuote();  // выведет бины, но не напечатает message, который мы указали в файле проперти, т.к. @InjectRandomInt не известна.
        // Для этого в файле context.properties надо явно указать + добавить setter
    }

}

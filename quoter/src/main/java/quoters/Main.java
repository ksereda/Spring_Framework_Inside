package quoters;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Если поставить брейкпоинт на строке 18, то можем воспользоваться некоторыми полезными методами:
 * context.getBeanDefinitionNames() - имена всех бинов
 * context.getBean(Quoter.class).getClass() - мы увидим, что его название "com.sun.proxy.$Proxy6".
 */

public class Main {

    public static void main(String[] args) throws Exception  {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("web.xml");  // имплементация этого контекста анализируется и сканируется XmlBeanDefenitionsReader-ом
//        context.getBean(TerminatorQuoter.class);  // по классу неверно! надо по интерфейсу! т.к. мы не знаем, что случится с классом в будущем
        while (true) {
            Thread.sleep(100);
            context.getBean(Quoter.class).sayQuote();
        }
    }

}

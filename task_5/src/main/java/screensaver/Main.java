package screensaver;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * В AnnotationConfigApplicationContext передаем наш кастомный Config, который при помощи компонента ClassPathBeanDefinitionScanner просканирует весь пакет
 * и найдет там еще один бин, аннотированный аннотацией @Component
 *
 * Frame должен быть 1 (значит он singleton), т.е. каждый раз, когда мы loockup-им его мы должны получать один и тот же объект,
 * а цвет должен быть всегда другой (т.е. prototype)
 *
 * т.е. сейчас если мы на цвет повесим prototype а на бин нет, то у нас будет каждый раз один и тот же цвет. Почему ? Потому что у нас создались фактически 2 объекта, т.е.
 * создается Frame 1 раз, т.к. он singleton, и каждый раз, когда мы делаем ему lookup ( context.getBean() ), мы всегда получаем тот-же бин и поэтому получаем один и тот-же цвет,
 * а не каждыый раз разный, как мы хотим.
 *
 * Как обновлять prototype в singleton ?
 * 1. Вместо private Color color - делаем ApplicationContext context; - НЕВЕРНО!!! но отработает.
 * 2. В конфиге указываем proxyMode = ScopedProxyMode.TARGET_CLASS - НЕВЕРНО!!! но отработает.
 * 3. Делаем метод protected abstract Color getColor(); - ВЕРНО!!!
 *
 */

public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        while (true) {
            context.getBean(ColorFrame_correct_colution.class).showOnRandomPlace3();  // достаем бин ColorFrame из context и каждый раз вызываем у него метод showOnRandomPlace()
            try {
                Thread.sleep(800);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

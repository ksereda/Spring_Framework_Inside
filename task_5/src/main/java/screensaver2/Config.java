package screensaver2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import java.awt.*;
import java.util.Random;

@Configuration
@ComponentScan(basePackages = "screensaver")
public class Config {

    @Bean
    @Scope("periodical")
    public Color color() {
        Random random = new Random();
        return new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
    }

    // Создать объект из абстрактного класса можно только тогда, если вы пропишите метод, который является абстрактным
    // Это естественное место, где у нас есть доступ к нашему бину color
    @Bean
    public ColorFrame_correct_colution frame() {
        return new ColorFrame_correct_colution() {
            @Override
            protected Color getColor() {
                return color();  // этот color - это НЕ вызов метода color(), который описан выше. Это обращение к бину, который color, т.е. если он prototype, То он будет всегда
                // возвращать новый бин, а если он singleton - то будет возвращать старый.
                // Мы сами контролируем тот момент, когда мы хотим, чтобы сменился prototype.
                // В прошлых решениях мы ничего не контролировали, т.е. каждый раз, когда мы обращаемся к бину, мы хотим по-новому его брать из контекста.
                // А теперь, когда мы вызываем там абстрактный метод getColor() - мы хотим сейчас получить новый объект. Мы можем его сохранить и пользоваться им, а спустя
                // некоторое время вызвать новый.
            }
        };
    }

}

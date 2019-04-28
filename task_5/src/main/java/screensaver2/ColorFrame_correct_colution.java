package screensaver2;

import org.springframework.stereotype.Component;
import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * Верное решение
 *
 * Если у нас есть потребность обратиться к контексту то мы не можем метод getColor() здесь прописать, т.к. мы не хотим здесь содержать контекст.
 * => метод и класс делаем abstract.
 * И в java конфиге прописываем @Bean
 *
 */

@Component
public abstract class ColorFrame_correct_colution extends JFrame {

    public ColorFrame_correct_colution() {
        setSize(200, 200);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);  // выключить программу по закрытию фрейма

    }

    //  Каждый раз будут меняться координаты Фрейма, обновляться бэкграунд, и всему этому делатсья repaint
    public void showOnRandomPlace3() {
        Random random = new Random();
        setLocation(random.nextInt(1200), random.nextInt(600));
        getContentPane().setBackground(getColor());
        repaint();
    }

    protected abstract Color getColor();

}

//package screensaver;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import javax.swing.*;
//import java.awt.*;
//import java.util.Random;
//
///**
// *  Frame должен быть 1 (значит он singleton), т.е. каждый раз, когда мы loockup-им его мы должны получать один и тот же объект,
// *  а цвет должен быть всегда другой (т.е. prototype)
// *
// *  т.е. сейчас если мы на цвет повесим prototype а на бин нет, то у нас будет каждый раз один и тот же цвет. Почему ? Потому что у нас создались фактически 2 объекта, т.е.
// *  создается Frame 1 раз, т.к. он singleton, и каждый раз, когда мы делаем ему lookup ( context.getBean() ), мы всегда получаем тот-же бин и поэтому получаем один и тот-же цвет,
// *  а не каждыый раз разный, как мы хотим.
// *
// *
// */
//@Component
//public abstract class ColorFrame extends JFrame {
//
//    @Autowired
//    private Color color;
//
//    public ColorFrame() {
//        setSize(200, 200);
//        setVisible(true);
//        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);  // выключить программу по закрытию фрейма
//
//    }
//
//    //  Каждый раз будут меняться координаты Фрейма, обновляться бэкграунд, и всему этому делатсья repaint
//    public void showOnRandomPlace() {
//        Random random = new Random();
//        setLocation(random.nextInt(1200), random.nextInt(600));
//        getContentPane().setBackground(color);
//        repaint();
//    }
//
//    protected abstract Color getColor();
//}

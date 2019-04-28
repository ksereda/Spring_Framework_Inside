//package screensaver;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
//import org.springframework.stereotype.Component;
//import javax.swing.*;
//import java.awt.*;
//import java.util.Random;
//
///**
// * Решение 1
// * НЕВЕРНО! но отработает.
// * Наш фрейм, который не имеет отношений к Spring, который ничего не должен знать, сейчас зависит от всего контекста из-за того, что ему нужен цвет.
// *
// */
//
//@Component
//public class ColorFrame_solution1 extends JFrame {
//
//    @Autowired
////    private Color color;
//    private ApplicationContext context;
//
//    public ColorFrame_solution1() {
//        setSize(200, 200);
//        setVisible(true);
//        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);  // выключить программу по закрытию фрейма
//
//    }
//
//    public void showOnRandomPlace1() {
//        Random random = new Random();
//        setLocation(random.nextInt(1200), random.nextInt(600));
////        getContentPane().setBackground(color);
//        getContentPane().setBackground(context.getBean(Color.class));
//        repaint();
//    }
//
//}

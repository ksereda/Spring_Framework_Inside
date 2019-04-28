//package screensaver;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import javax.swing.*;
//import java.awt.*;
//import java.util.Random;
//
///**
// * Решение 2
// * НЕВЕРНО! но отработает.
// * КОгда мы указали proxyMode = ScopedProxyMode.TARGET_CLASS, теперь нам не надо инжектить контекст.
// * Теперь каждый раз при обращении к Color (неважно где, пусть даже в одном и том-же методе обратимся к нему 2 раза), каждый раз мы получаем новый бин!
// *
// */
//
//@Component
//public class ColorFrame_solution2 extends JFrame {
//
//    @Autowired
//    private Color color;
//
//    public ColorFrame_solution2() {
//        setSize(200, 200);
//        setVisible(true);
//        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);  // выключить программу по закрытию фрейма
//
//    }
//
//    public void showOnRandomPlace2() {
//        Random random = new Random();
//        setLocation(random.nextInt(1200), random.nextInt(600));
//        getContentPane().setBackground(color);
//        getContentPane().setBackground(color);
//        repaint();
//    }
//
//}

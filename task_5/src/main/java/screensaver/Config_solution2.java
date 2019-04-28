//package screensaver;
//
//import org.springframework.context.annotation.*;
//
//import java.awt.*;
//import java.util.Random;
//
//@Configuration
//@ComponentScan(basePackages = "screensaver")
//public class Config_solution2 {
//
//    @Bean
//    @Scope(value = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS)
//    public Color color() {
//        Random random = new Random();
//        return new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
//    }
//
//}

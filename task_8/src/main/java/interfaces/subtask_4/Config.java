package interfaces.subtask_4;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Для бинов, прописанных в java-config, в BeanDefinition нет информации о классе, из котрого сделан этот бин
 *
 */

@Configuration
public class Config {

    // Совершенно не понятно из какого класса вернется объект, т.к. полиморфизм, даже Ship - не гарантирует что будет объект такого типа
    @Bean
    public Ship ship() {
        return new SuperShip();
    }

}

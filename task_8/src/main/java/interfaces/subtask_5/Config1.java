package interfaces.subtask_5;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config1 {

    @Bean
    @App1
    public String str1() {
        return "1";
    }

    @Bean
    @App1
    public String str2() {
        return "2";
    }

    @Bean
    @App1
    public String str3() {
        return "3";
    }

}

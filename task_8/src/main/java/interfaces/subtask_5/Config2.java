package interfaces.subtask_5;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class Config2 {

    @Bean
    @App2
    public List<String> messages() {
        List<String> strings = new ArrayList<>();
        strings.add("1");
        strings.add("2");
        return strings;
    }

}

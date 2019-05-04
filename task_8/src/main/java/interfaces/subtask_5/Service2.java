package interfaces.subtask_5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Инжектим только то, что использует @App2
 *
 */

@Service
public class Service2 {

    @Autowired
    @App2
    private void printList(List<String> list) {
        System.out.println("service_2");
        list.forEach(System.out::println);
    }

}

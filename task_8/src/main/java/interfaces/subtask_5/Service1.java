package interfaces.subtask_5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Инжектим только то, что использует @App1
 *
 */

@Service
public class Service1 {

    @Autowired
    @App1
    private void printList(List<String> list) {
        System.out.println("service_1");
        list.forEach(System.out::println);
    }

}

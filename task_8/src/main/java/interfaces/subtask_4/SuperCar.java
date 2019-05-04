package interfaces.subtask_4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Если ставим @Autowired над полем - то сетится поле,
 * Если ставим @Autowired над конструктором - то Spring поймет, что через этот конструктор надо создавать объект и туда все заинжектит,
 * НО если у вас есть 1 единственный конструктор с параметрами, то даже не надо ставить @Autowired, т.к. Spring сам поймет что с ним делать.
 * Если ставим @Autowired над методом - то с точки зрения Spring этот метод расценивается как setter, запускается и в него инжектится то, что нужно.
 *
 */

@Component
public class SuperCar implements Car {

    @Override
    public void speed() {
        System.out.println("Run");
    }

    @Autowired
    public void superSpeed(Engine engine) {
        System.out.println(engine + " start");
    }


}

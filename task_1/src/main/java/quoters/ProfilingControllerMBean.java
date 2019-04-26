package quoters;

/**
 *  Тут описываем те методы, которые будут доступны через JMX Console (чтобы мы могли менять флаг на true/false)
 *
 */

public interface ProfilingControllerMBean {

    void setEnabled(boolean enabled);

}

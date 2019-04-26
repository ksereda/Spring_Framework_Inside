package three_phase_constructor;

/**
 * по умолчанию включено Профилирование (enabled = true)
 */

public class ProfilingController implements ProfilingControllerMBean {

    private boolean enabled = true;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

}

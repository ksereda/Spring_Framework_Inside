package quoters;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * по умолчанию - RetentionPolicy.CLASS
 *
 * SOURCE - аннотация видна в сурсах. Когда мы копилируем в байткоде уже ничего не будет. (например @Override - она нужна только в процессе разработки кода, если вы поставили @Override там где это не логично, то компилятор будет ругаться)
 * CLASS - (по умолчанию) аннотация в байткод попасть должна, но через Reflection в рантайме мы ее считать не сможем (для всяких байткод инструментейшен и прочего)
 * RUNTIME - видны в рантайме и можно считать через Reflection
 */

@Retention(RetentionPolicy.RUNTIME)
public @interface Profiling {
}

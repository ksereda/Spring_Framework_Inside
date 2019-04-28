package screensaver;

import javafx.util.Pair;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class PeriodicalScopeConfigurer implements Scope {

    // каждый раз, когда Спрингу надо будет знать, как относиться к scope = periodical, он будет делегировать в метод get() а в методе get() уже мы решаем когда отдавать бин,
    // который мы создаем по новому или мы можем его как-то кешировать.
    // Как мы его будем кешировать ?
    // Нам надо создать map, в которой будет ключ - имя бина и значение - объект, который хранит в себе сам бин и сколько времени он уже существует.

    Map<String, Pair<LocalTime, Object>> map = new HashMap<>();

    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {
        // есть ли бин в map ?
        if (map.containsKey(name)) {  // перед тем как его вернуть, нам надо проверить, не истекло ли его время ?
            Pair<LocalTime, Object> pair = map.get(name);  // достаем пару по ключу (имя)
            // сколько времени назад был создан бин в той паре. Если он был создан давно, то нам надо получить новый объект, заменить в этой map и вернуть тому, кто сделает запрос на получение его из map
            int secondsSinceLastRequest = LocalTime.now().getSecond() - pair.getKey().getSecond();
            if (secondsSinceLastRequest > 3) {  // проверяем, если прошло > 3 секунды
                map.put(name, new Pair<>(LocalTime.now(), objectFactory.getObject()));  // то кладем в map имя бина и новую пару и еще раз по новому вытаскиваем бин
            }
        } else {
            map.put(name, new Pair<>(LocalTime.now(), objectFactory.getObject()));  // Если бина нету в map, то его надо туда положить. Значеие - это пара, в которой "Сейчас" и
            // объект, который вытаскиваем из objectFactory (создается новый объект и "Сейчас") кладется и запоминается сколько времени
        }

        return map.get(name).getValue();
    }

    @Override
    public Object remove(String s) {
        return null;
    }

    @Override
    public void registerDestructionCallback(String s, Runnable runnable) {

    }

    @Override
    public Object resolveContextualObject(String s) {
        return null;
    }

    @Override
    public String getConversationId() {
        return null;
    }

}

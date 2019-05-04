package interfaces.subtask_5;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Отработает или нет ?
 * Зависит от версии Spring !
 *
 * На версии 4.2 - не отработает (т.к. бина с @App2, который является String, не было). Spring видит аннотацию @Autowired над list (он не пытается найти бин который list), он смотрит тип дженерика.
 * Можно было бы сделать так:
 * 1) Указать ArrayList<String> вместо List<String> - но принято работать с интерфейсами.
 * 2) List<List<String>> list
 * 3) Можно не делать бин, который является list.
 * 4) Или использовать Spring 4.3+
 *
 * Начиная с версии 4.3 - отработает: в модуль 1 попадут только те, которые помечены аннотацией @App1, а в модуль 2 - те, что помечены @App2
 * Т.е. если есть конкретно бин, который является list (который нам подходит), Spring возьмет его и заинжектит, но если его нет, то тогда Spring найдет все бины, построит из них list и заинжектит его.
 *
 * Если мы ставим @Autowired над list и в дженериках <> указываем, что это там за хендлер, то все бины, которые являются этими хендлерами, туда заинжектятся.
 *
 */

public class Main {

    public static void main(String[] args) {
        new AnnotationConfigApplicationContext("subtask_5");
    }

}

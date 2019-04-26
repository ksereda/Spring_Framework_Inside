task 3: three_phase_constructor

- BeanFactoryPostProcessor

`BeanFactoryPostProcessor` - интерфейс. Он позволяет настраивать `BeanDefinitions`, до того, как создаются бины. Работает на этапе, когда кроме `BeanDefinitions` еще ничего нету (никакие бины не создались, ничего еще нету).

Что работает раньше, `BeanFactoryPostProcessor` или `BeanPostProcessor` ?
Ответ: `BeanFactoryPostProcessor` отработает раньше. Он работает еще до того, как бины созданы и он может что-то подкрутить в `BeanDefinitions`.

Он содержит 1 метод `postProcessorBeanFactory()` у которого есть доступ к `BeanFactory`, т.е. мы можем как-то повлиять на `BeanFactory` до того, как он начнет работать и повлиять на `BeanDefinitions` до того, как `BeanFactory` из них начнет создавать бины.


1. Создаем свою аннотацию `@DeprecatedClass`
2. Указываем обязательный параметр в ней (новая имплементация)
3. Мы хотим, чтобы все бины, которые помечены аннотацией `@DeprecatedClass` заменились на нормальные, т.е. до того, как `BeanFactory` начнет создавать объекты, поменять все классы в `BeanDefinitions` с `@DeprecatedClass` аннотации на `новую`.


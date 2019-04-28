task 4: PropertiesBeanDefinitionReader


- Пишем свой тип контекста


Какие бывают типы контекста:

1. ClassPathXmlApplicationContext  -  для XML
2. FileSystemXmlApplicationContext
3. XmlWebApplicationContext
4. AnnotationConfigApplicationContext  -  для аннотаций и для java-кода
5. GenericGroovyApplicationContext  -  для GroovyConfig


Напишем свой тип контекста `PropertyFileApplicationContext`. 
В properties файле сможем прописать свои бины и по этому properties файлу будем их создавать.

1. Создаем properties файл "context.properties"
2. В нем определаем название package (определяем bean)
3. Создаем свой `PropertieFileApplicationContext`, в котором при помощи `PropertiesBeanDefinitionReader` (он принимает в конструкторе тот context, для которого он будет регистрировать бины, когда он будет их находить) будем загружать все `BeanDefinitions`, которые указаны в файле fileName и возвращать кол-во бинов, которые он нашел.
4. Добавить `setter` (т.к. XML работает только с setter, точно также и в нашем случае)
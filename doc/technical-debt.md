# Технический долг
1. [Технический долг](#td)
2. [План мероприятий по устранению технического долга](#mtd)
3. [Сравнение объёма долга и недоимплементированных фич](#compare)
4. [Результаты устранения технического долга](#resault)

<a name="td"/>

## Технический долг в проекте

### Непонятный / нечитабельный код

<a name="duplicate_code_before"/>

### Дублирующийся кода
Отличный пример дублирующегося кода - бльшое количество методов get...ById при работе с БД([файл до рефакторинга](https://github.com/Andrlis/Review-Assistant/blob/18c70b3ffb3d62af002e02acb7dec2d4cb049ae8/src/main/java/resources/Hibernate/HibernateCore.java)):
```java
public Comment getCommentById(Integer id) throws HibernateShellQueryException {
  final Session session = getSession();
  Comment comment = null;
  try {
    comment = (Comment) session.get(Comment.class, id);
  } catch (Exception e) {
    throw new HibernateShellQueryException(e);
  } finally {
    session.close();
  }

  return comment;
}
```
```java
public TestMark getTestMarkById(Integer id) throws HibernateShellQueryException {
  final Session session = getSession();
  TestMark testMark = null;
  try {
    testMark = (TestMark) session.get(TestMark.class, id);
  } catch (Exception e) {
    throw new HibernateShellQueryException(e);
  } finally {
    logger.info("Close session.");
    session.close();
  }

  return testMark;
}
```
```java
public UniversityClass getUniversityClassById(Integer id) throws HibernateShellQueryException {
  final Session session = getSession();
  UniversityClass universityClass = null;
  try {
    universityClass = (UniversityClass) session.get(UniversityClass.class, id);
  } catch (Exception e) {
    throw new HibernateShellQueryException(e);
  } finally {
    logger.info("Close session.");
    session.close();
  }

  return universityClass;
}
```
[после устранения ТД](#duplicate_code_after)

### Отсутствие автоматизации (тестов, сборки, развёртывания)
Отсутсвует автоматизация(тестов, сборки, развёртывания)

<a name="architecture_before"/>

### Запутанная архитектура и ненужные сложные зависимости
Превосходный привер запутанной архитектуры - [усложнённая работа с БД](https://github.com/Andrlis/Review-Assistant/tree/5bd456b8c874fd04ebf2f4d1b27b021e63b9f9de/src/main/java/resources/Hibernate):

![](https://github.com/Andrlis/Review-Assistant/blob/master/doc/%D1%80%D0%B0%D0%B1%D0%BE%D1%82%D0%B0%20%D1%81%20%D0%B1%D0%B4%20%D0%B4%D0%BE%20%D1%80%D0%B5%D1%84%D0%B0%D0%BA%D1%82%D0%BE%D1%80%D0%B8%D0%BD%D0%B3%D0%B0.jpg)

Основные ошибки, допущенные при приектировании:
- Отсутствие в коде интерфейсов.
- Большое колличество повторяющегося кода.
- Искуственно усложнённая работа с БД.

[после исправления ТД](#architecture_after)

### Медленные / неэффективные средства
Основное время работы нашего приложения - работа с БД. Время обращения увеличивается ещё больше из-за сложных зависимостей, лишних таблиц в БД, отсутсвия lazyCollection.
### Незакоммиченый код / долгоживущие ветки
На момента начала написания данного отчёта, в проекте существует 3 ветки:

![](https://github.com/Andrlis/Review-Assistant/blob/master/doc/%D0%B2%D0%B5%D1%82%D0%BA%D0%B8.jpg)

Только одна из них(master) используется по назвачению.
### Отсутствие / несоответствие технической документации
Проблемы с документацией проявляются в отсутствии части отчётов по ЛР.
### Отсутствие тестовой среды
Из-за усложнённой структуры проекта, написание тестов становится сложной задачей.
### Длинные циклы интеграции / отсутствие непрерывной интеграции


<a name="mtd"/>

## План мероприятий по устранению технического долга
- Провести рефакторинг работы с БД
- Провести рефакторинг работы с BSUIR API
- Покрыть код тестами
- Упростить структура БД
- Уничтожить лишние ветки

<a name="compare"/>

## Сравнение объёма долга и недоимплементированных фич
Рефакторинг работы с БД и BSUIR API, в дальнейшем, позволит упростить написание unit тестов. И в целом упростит дальнейшее написание кода. Таким образос целесообразно провести данные мероприятия.

Покрытие кода тестами позволит ускорить цикл интеграции.

<a name="resault"/>

## Результаты устранения технического долга

<a name="duplicate_code_after"/>

### Дублирующийся кода
После рефакторинга, для получения любой сущности по её id используется один метод([файл после рефакторинга](https://github.com/Andrlis/Review-Assistant/blob/master/src/main/java/dao/DataBaseCore.java)):
```java
public Object getById(Class c, Integer id) throws DataBaseQueryException {
  logger.info("DataBaseCore.getById(). " + c.getName());

  final Session session = getSession();
  Object answer;

  try {
    answer = (Object) session.get(c, id);
  } catch (Exception e) {
    throw new DataBaseQueryException(e);
  } finally {
    session.close();
  }

  return answer;
}
```
[до устранения ТД](#duplicate_code_before)

<a name="architecture_after"/>

### Запутанная архитектура и ненужные сложные зависимости
После рефакторинга были исправлены основные ошибки. Так же рефакторинг позволит в дальнейшем писать более качественные unit тесты, за счёт выделения основных интерфейсов.

![](https://github.com/Andrlis/Review-Assistant/blob/master/doc/%D1%80%D0%B0%D0%B1%D0%BE%D1%82%D0%B0%20%D1%81%20%D0%B1%D0%B4%20%D0%BF%D0%BE%D1%81%D0%BB%D0%B5%20%D1%80%D0%B5%D1%84%D0%B0%D0%BA%D1%82%D0%BE%D1%80%D0%B8%D0%BD%D0%B3%D0%B0.jpg)

[до устранения ТД](#architecture_before)
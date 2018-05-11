# Улучшение UI
1. [Оценка проекта](#atr)
2. [Пути улучшения UI](#vais)
3. [Результаты улучшений](#res)
 
<a name="atr"/>
 
## Оценка проекта по атрибутам
### Распознаваемость соответствия
При поподании на web-приложение пользователь сразу поподает на главную страницу, благодаря чему пользователь сразу понимает, удовлетворяет ли web-приложение его потребности или нет.
### Обучаемость
Для не авторизированного пользователя приложения является интуитивно понятным. Авторизированный пользователь первое время будет нуждаться в обчучении, т.к. некоторый фукционал предоставляется посредсвом использования мыши (клик по определенным зонам, которы не обеспечены кнопкой).
### Используемость
Проект разрабатывается для автоматизации работы преподавателей кафедры ЭВМ, по методике, разработанной в рамках курса ТРиТПО. Поэтому далее этот продукт сможет принести непосредственную пользу его потребителям.
### Защита от ошибок пользователя
До улучшения UI пользователь никак не был защищен от совершения ошибок.
### Эстэтика GUI
До улучшения UI всплывающие окна в приложении не имели общего стиля. При появлении на экране формы для заполнения, курсор не фокусировался на поле ввода, что делало использование приложения неудобным. Так же формы не реагировали на нажатие клавиши Enter. Эти факторы не позволяли приложению обеспечить приятное и удовлетворяющее взаимодействие с пользователем.
### Доступность
Web-приложение не учитывает особенности пользователей с ограниченными возможностями.

<a name="vais"/>

## Пути улучшения UI
После анализа недостатков приложения было принято решение о проведении следующих мер для улучшения UI:
- Добавить оповещения об ошибках во время аутентификации;
- Добавить предупреждения пользователя о необратимых действиях (удаление);
- Привести все всплывающие окна к единому стилю;
- Фокусировать курсор на полях ввода в формах;
- Обрабатывать формы при нажатии клавиши Enter.

<a name="res"/>

## Результаты улучшения
### Форма авторизации
#### До:

![](https://github.com/Andrlis/Review-Assistant/blob/master/doc/resource/lr6/lr6_authorization_before.png)

##### После:

  ![](https://github.com/Andrlis/Review-Assistant/blob/master/doc/resource/lr6/lr6_authorization_after.png)
![](https://github.com/Andrlis/Review-Assistant/blob/master/doc/resource/lr6/lr6_authorization_after1.png)
![](https://github.com/Andrlis/Review-Assistant/blob/master/doc/resource/lr6/lr6_authorization_after2.png)

### Форма для редактирование информации о студенте
##### До

![](https://github.com/Andrlis/Review-Assistant/blob/master/doc/resource/lr6/lr6_add_student.png)
![](https://github.com/Andrlis/Review-Assistant/blob/master/doc/resource/lr6/lr6_delete_student_defore.png)

##### После

![](https://github.com/Andrlis/Review-Assistant/blob/master/doc/resource/lr6/lr6_add_student_after.png)
![](https://github.com/Andrlis/Review-Assistant/blob/master/doc/resource/lr6/lr6_delete_student_after.png)

### Редактирование комментариев
#### До

![](https://github.com/Andrlis/Review-Assistant/blob/master/doc/resource/lr6/lr6_lab_commant_before.png)

#### После

![](https://github.com/Andrlis/Review-Assistant/blob/master/doc/resource/lr6/lr6_lab_commanr_after.png)

### Форма для сбора статистики
#### До

![](https://github.com/Andrlis/Review-Assistant/blob/master/doc/resource/lr6/lr6_statistics_before.png)

#### После

![](https://github.com/Andrlis/Review-Assistant/blob/master/doc/resource/lr6/lr6_stats_after.png)

### Форма для выдачи лабораторных/контрольных работ
####

![](https://github.com/Andrlis/Review-Assistant/blob/master/doc/resource/lr6/lr6_add_lab_test_before.png )

####

![](https://github.com/Andrlis/Review-Assistant/blob/master/doc/resource/lr6/lr6_add_lab_test_after.png)

### Страница администратора
#### До

![](https://github.com/Andrlis/Review-Assistant/blob/master/doc/resource/lr6/lr6_group_before.png)

#### После

![](https://github.com/Andrlis/Review-Assistant/blob/master/doc/resource/lr6/lr6_group_after.png)

### Работа с подгруппой 
#### ДО

![](https://github.com/Andrlis/Review-Assistant/blob/master/doc/resource/lr6/lr6_subgroup_before.png)

#### После

![](https://github.com/Andrlis/Review-Assistant/blob/master/doc/resource/lr6/lr6_subgroup_after.png)

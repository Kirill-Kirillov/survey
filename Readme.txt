Для работы приложения необходимо выполнить следующее :
1. Создать БД в postgreSql
2. Изменить настройки в application.properties под свой postgre
3. Запустить проект


Описание API:

1. Создание опроса POST запрос http://localhost:8080/survey-api/surveys в боди передать:

    -Для создания опроса вместе с вопросами

    "name" : "Имя опрсоа",
    "dayOfStart" : "yyyy-mm-dd",
    "dayOfEnd" : "yyyy-mm-dd",
    "surveyActive" : "ACTIVE", (либо INACTIVE),
    "questions" : [
        {
            "text": "вопрос 1",
            "order": 1
        },
        {
            "text": "вопрос 2",
            "order": 2
        }
    ]

    -Для создания опроса без вопросов

    "name" : "Имя опрсоа",
    "dayOfStart" : "yyyy-mm-dd",
    "dayOfEnd" : "yyyy-mm-dd",
    "surveyActive" : "ACTIVE" (либо INACTIVE)

2. Измменение опроса PUT запрос http://localhost:8080/survey-api/surveys/{id} в боди передать

        {
            "name": "Имя опроса",
            "dayOfStart": "yyyy-mm-dd",
            "dayOfEnd": "yyyy-mm-dd",
            "surveyActive": "INACTIVE" (либо INACTIVE)
        }

3. Удаление опроса Delete запрос http://localhost:8080/survey-api/surveys/{id}

4. Получение всех запросов по дате c сортировкой по имени http://localhost:8080/survey-api/surveys?date=yyyy-mm-dd&sortBy=name
4.1 Получение всех запросов по дате c сортировкой по дате http://localhost:8080/survey-api/surveys?date=yyyy-mm-dd&sortBy=date
4.2 Получение всех запросов по наименованию c сортировкой по имени http://localhost:8080/survey-api/surveys?name=surveyName&sortBy=name
4.3 Получение всех запросов по наименованию c сортировкой по дате http://localhost:8080/survey-api/surveys?name=surveyName&sortBy=date
4.4 Получение всех запросов по активности с сортировкой по имени http://localhost:8080/survey-api/surveys?active=ACTIVE&sortBy=name
4.5 Получение всех запросов по активности с сортировкой по дате http://localhost:8080/survey-api/surveys?active=INACTIVE&sortBy=date

5. Добавление вопроса к опросу Post запрос http://localhost:8080/survey-api/surveys/questions/{Surveyid} в body передаем

    {
    "text" : "yourText",
    "order" : 7
    }

6. Изменение вопроса Put запрос  http://localhost:8080/survey-api/questions/{questionId} в тело передаем поля вопроса, которые необходимо изменить
7. Удаление вопроса Delete запрос http://localhost:8080/survey-api/questions/{questionId}
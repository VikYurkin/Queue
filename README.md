# Queue
Test task for Geek Prtns

Createв RestAPI app for generating series on RestAPI_to_series branch.

Для запуска через docker compose:
1) Создать файл  docker-compose.yml:


```sh
version: '3.7'

services:
  queue:
    depends_on:
      - postgres
    image: vyurkin/queue
    environment:
      DB_USERNAME: postgres
      DB_PASSWORD: 9876543219
      DB_URL: jdbc:postgresql://postgres:5432/queue
    restart: always
    ports:
      - 8083:8070

  postgres:
    image: postgres:15.2
    restart: always
    environment:
      POSTGRES_PASSWORD: 9876543219
      POSTGRES_USER: postgres
      POSTGRES_DB: queue
    ports:
      - 5433:5432
```

2) Запустить сборку образа командой:

docker compose -p "project-queue" -f "docker-compose.yml" up -d.
 
если образы vyurkin/queue и postgres:15.2 отсутствуют, они будут скачаны с DockerHub.

API:

1) Request: GET http://localhost:8083/series 

   Exaple response: JSON: {
                          "series": "a0a0"
                        }
                        
2) Request: GET http://localhost:8083/series/next

    Exaple response: JSON: {
                          "series": "a0a1"
                        } 
                        
                        
3)  Request: POST http://localhost:8083/series

    Exaple request: JSON: {
                          "series": "d0d1"
                          }

    Response: "OK"

Apps generated and saved in DB unique series for get request and continues series for get request.  

Микросервис для генерации уникального кода (a0a0, a0a1, a0a2, ..., a0a9, a0b0, a0b1, ..., a0z9, a1a0...).

 Функиционал:
1) По запросу 1 из API в ответе будет выдан текущее значение кода в базе данных(миграция выставляет первое значение в a0a0).
2) По запросу 2 из API в ответе будет выдан следующий код и записан в базу данных.
3) В запросе 3 из API можно указать код, который необходимо задать как текущий (данные валидируются, последовательность должна соответствовать формату кода), ответом будет выдано либо "OK", либо сообщение об ошибке.
  

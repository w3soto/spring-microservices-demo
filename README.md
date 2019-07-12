# Spring Microservices Demo

See **JavaBrains**

* Spring Boot Microservices Level 1 - https://www.youtube.com/watch?v=y8IQb4ofjDo&list=PLqq-6Pq4lTTZSKAFG6aCDVDP86Qx4lNas
* Spring Boot Microservices Level 2 - https://www.youtube.com/watch?v=o8RO38KbWvA&list=PLqq-6Pq4lTTbXZY_elyGv7IkKrfkSrX5e

#### Run all 4 servers
* Discovery (Eureka) server - http://localhost:8761/
* **Movie catalog service** - http://localhost:8081/catalog/user
* Movie info service - http://localhost:8082/
* Ratings data service - http://localhost:8083/

#### Actuator & Hystrix Dashboard
* http://localhost:8081/actuator
* http://localhost:8081/hystrix
* http://localhost:8081/hystrix/monitor?stream=http%3A%2F%2Flocalhost%3A8081%2Factuator%2Fhystrix.stream


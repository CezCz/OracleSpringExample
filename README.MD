# Projekt Bazy Danych OracleDB

In order to run app type:

```mvn spring-boot:run```

If trying to run application differently, remember that theres loose ojdbc8.jar in src/main/resources (oracle retards don't publish it on public maven, but allow downloading it manually - it was easier than another registration&configuration, which probably should be done per each developer).
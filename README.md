Launch embedded pgsql

```
./gradlew startEmbeddedPgSQL
```

Launch web app

```
./gradlew :bootRun
```

Stop embedded pgsql
```
./gradlew stopEmbeddedPgSQL
```

Actuator url: http://localhost:8080/actuator

Rest api:

- http://localhost:8080/option/list


TODO:
 - check what additional role we need to grant (most likely on sequence)
 - set the value dynamically from a request param
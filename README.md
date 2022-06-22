## Flavor service

This module is responsible for manage flavors for another modules and applications using REST adapter and InMemory repository.

### Running application

```
gradlew bootRun 
```

### Running tests

```
gradlew test
```


### Building jar

```
gradlew build
```

### Building Docker Image

```
docker build --build-arg JAR_FILE=build/libs/*.jar -t coffexplorer/flavorservice:v1.0 .
```

### Running Docker Container

```
docker run -p 8080:8080 coffexplorer/flavorservice:v1.0
```
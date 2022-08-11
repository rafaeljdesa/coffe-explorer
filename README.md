## Core domain

This module is responsible for isolate the domain layer and business logic, defining their input and output ports.

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
docker run -p 8080:8081 coffexplorer/flavorservice:v1.0
```

### Viewing API documentation

Navigate to "/swagger-ui.html" and explore "/api-docs".

### Generating Open API documentation

```
gradle clean generateOpenApiDocs -DapiDocsUrl={url}/api-docs
```

The doc file will be generated within /build/docs/


## Coffe service

This module is responsible for manage coffes for another modules and applications using REST adapter, H2 or RDS database and Amazon S3.

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
docker build --build-arg JAR_FILE=build/libs/*.jar -t coffexplorer/coffeservice:v1.0 .
```

### Running Docker Container

```
docker run -p 8080:8082 coffexplorer/coffeservice:v1.0
```

### Viewing API documentation

Navigate to "/swagger-ui.html" and explore "/api-docs".

### Generating Open API documentation

```
gradle clean generateOpenApiDocs -DapiDocsUrl={url}/api-docs
```

The doc file will be generated within /build/docs/
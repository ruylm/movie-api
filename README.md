# Spring Boot Movie Project

This is a sample Java / Maven / Spring Boot application that can be used to read the list of nominees and winners in the Worst Film category at the Golden Raspberry Awards..


## Requirements

For building and running the application you need:

- [JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Maven 3](https://maven.apache.org)


## How to Run 

### Clone this repository 
```
- git clone https://github.com/ruylm/movie-api.git
- cd movie-api
```

### Replace  the csv file that will be loaded in the following directory
```
- movies/
```


### You will now be able to run the various commands below.

- `mvn clean`<br>
  remove _target_ directory.

- `mvn compile`<br>
  compiles the project, deposits results in the _target_ directory. 

- `mvn test`<br>
  runs all project tests.


### Producing executable code (depends on JVM)

- `mvn package`<br>
  generates jar file in _target_ directory.

### Once successfully built, you can run the service by one of these two methods:

```
        java -jar target/movie-api-0.0.1-SNAPSHOT.jar
or
        mvn spring-boot:run
```

Once the application runs you should see something like this

```
2024-08-19T19:06:37.122-03:00  INFO 2856 --- [movie] [main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port 8080 (http) with context path '/api'
2024-08-19T19:06:37.163-03:00  INFO 2856 --- [movie] [main] br.com.outsera.movie.MovieApplication    : Started MovieApplication in 18.134 seconds (process running for 19.329)
```

### To view your H2 in-memory datbase
- The application runs on H2 in-memory database. To view and query the database you can browse to http://localhost:8080/api/h2/. Default username is 'sa' with a blank password. Make sure you disable this in your production profiles


### To view Swagger API docs
- Open the following url in the browser http://localhost:8080/api/movie-documentation<br><br>


## Retrieve a list of nominees and winners in the Worst Film category at the Golden Raspberry Awards.

```
http://localhost:8080/api/movies/interval-awards

Response: HTTP 200
Content: list 
```




# Questions and Comments: ruylm@hotmail.com
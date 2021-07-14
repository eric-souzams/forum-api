<h1 align="center">Forum API</h1>

<p align="center">
  <a href="#about">About</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#preview">Preview</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#technologies">Technologies</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#building">Building</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#contributing">Contributing</a>&nbsp;&nbsp;&nbsp;
</p>


## About
This application is an API for a discussion forum on various topics and subjects, like the website Brainly.


## Preview
```
Matter

GET: http://localhost:8080/matters
POST: http://localhost:8080/matters
```

```
Author

GET: http://localhost:8080/author/1
POST: http://localhost:8080/author
```

```
Topic

GET: http://localhost:8080/topics
POST: http://localhost:8080/topics

PATCH: http://localhost:8080/topics/1
PATCH: http://localhost:8080/topics/1/close
PATCH: http://localhost:8080/topics/1/solve
PATCH: http://localhost:8080/topics/1/not-solve

GET: http://localhost:8080/topics/1/answers
POST: http://localhost:8080/topics/1/answers
POST: http://localhost:8080/topics/1/answers/1/like
```


## Technologies
This project was developed using the following technologies:
- [Java](https://www.oracle.com/br/java/technologies/javase-jdk11-downloads.html)
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring Security](https://spring.io/projects/spring-security)
- [Spring Data JPA](https://spring.io/projects/spring-data)
- [Lombok](https://projectlombok.org/)


## Building
You'll need [Java 11+](https://www.oracle.com/br/java/technologies/javase-jdk11-downloads.html) and [Maven](https://maven.apache.org/download.cgi) installed on your computer in order to build this app.

```bash
$ git clone https://github.com/eric-souzams/forum-api.git
$ cd /forum-api
$ mvn spring-boot:run
```


## Contributing
This repository is currently under development. If you want to contribute please fork the repository and get your hands dirty, and make the changes as you'd like and submit the Pull request.
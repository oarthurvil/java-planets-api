# Java REST API Planets

A REST API about planets.

## Table of contents

- [Overview](#overview)
- [Built with](#built-with)
- [The project](#the-project)
  - [Database](#database)
- [Challenge reference](#challenge-reference)


## Overview

- REST API model.
- For each planet, the following data must be obtained from the application database and manually entered:
  - Name
  - Climate
  - Terrain

## Built with

* Java 11
* Maven
* Spring Boot
* Postgres

## The project

### Database
In "application.properties" (path: src/main/resources/application.properties) make the necessary settings for the database(PORT, database name, user and password):

~~~
spring.datasource.url= jdbc:postgresql://localhost:5432/planets-db
spring.datasource.username= postgres
spring.datasource.password= root
~~~

Using Postgres (pgAdmin 4) create a database with the same name declared in "application.properties", in this case "planets-db".
   
## Challenge reference
Source of the challenge [Github AmeDigital](https://github.com/AmeDigital/challenge-back-end-hit).

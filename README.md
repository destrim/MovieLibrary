# Movie Library

### Introduction

Project goal is to create and manage your own movie library with watched movies.

User can add and delete movies from database, show them in order from the oldest or choose to show them sorted by title, released year or IMDB rating. 
Moreover, database can be exported as a json file, which also can be imported into another database.

### Technologies

It is written in _Java_ programming language and made in _IntelliJ IDEA_ IDE.
The movie database, where are sent requestes for movie to add, is available by connection with the _OMDb API_ via _java.net_ package.
Hibernate for mapping model to a local database.
Maven for building and managing project.

- Java 14.0.2
- Maven 4.0.0
- Hibernate 5.4.27.Final
- 

### Summary

Project was made as a self-learning exercise. It helped me become familiar with hibernate, maven and some design patterns.

#### Tasks list

- [x] add database to the project instead of JSON file
- [x] change all functionalities from using JSON file to operating on database
- [x] change usage of JSON file to additional functionality eg. importing other database or exporting it to another program
- [x] change "choosing number" system to proper command system
- [x] separate persistence from model
- [x] clean code

# Movie Library
[![](https://img.shields.io/badge/Intellij_IDEA-2020.2-brown)]()
[![](https://img.shields.io/badge/Java-14.0.2-white)]()
[![](https://img.shields.io/badge/Maven-4.0.0-black)](https://maven.apache.org/)
[![](https://img.shields.io/badge/Hibernate-5.4.27-green)](https://mvnrepository.com/artifact/org.hibernate/hibernate-core/5.4.27.Final)
[![](https://img.shields.io/badge/lombok-1.18.12-yellow)](https://mvnrepository.com/artifact/org.projectlombok/lombok/1.18.12)
[![](https://img.shields.io/badge/mysql-8.0.22-blue)](https://mvnrepository.com/artifact/mysql/mysql-connector-java/8.0.22)
[![](https://img.shields.io/badge/gson-2.8.6-red)](https://mvnrepository.com/artifact/com.google.code.gson/gson/2.8.6)
[![](https://img.shields.io/badge/org.json-20200518-orange)](https://mvnrepository.com/artifact/org.json/json/20200518)

### Introduction

Project goal is to create and manage your own movie library with watched movies.

User can add and delete movies from database, show them in order from the oldest or choose to show them sorted by title, released year or IMDB rating. 
Moreover, database can be exported as a json file, which also can be imported into another database.

### Technologies

It is written in _Java_ programming language and made in _IntelliJ IDEA_ IDE.
The movie database, where are sent requestes for movie to add, is available by connection with the _OMDb API_ via _java.net_ package.
Hibernate for mapping model to a local database.
Maven for building and managing project.

### Summary

Project was made as a self-learning exercise. It helped me become familiar with hibernate, maven and some design patterns.

#### Tasks list

- [x] add database to the project instead of JSON file
- [x] change all functionalities from using JSON file to operating on database
- [x] change usage of JSON file to additional functionality eg. importing other database or exporting it to another program
- [x] change "choosing number" system to proper command system
- [x] separate persistence from model
- [x] clean code

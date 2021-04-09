# Movie Library

[![](https://img.shields.io/badge/Java-14.0.2-lightgray)]()
[![](https://img.shields.io/badge/Maven-4.0.0-brown)](https://maven.apache.org/)
[![](https://img.shields.io/badge/Hibernate-5.4.27-green)](https://mvnrepository.com/artifact/org.hibernate/hibernate-core/5.4.27.Final)
[![](https://img.shields.io/badge/lombok-1.18.12-yellow)](https://mvnrepository.com/artifact/org.projectlombok/lombok/1.18.12)
[![](https://img.shields.io/badge/MySQL-8.0.22-blue)](https://mvnrepository.com/artifact/mysql/mysql-connector-java/8.0.22)
[![](https://img.shields.io/badge/gson-2.8.6-red)](https://mvnrepository.com/artifact/com.google.code.gson/gson/2.8.6)
[![](https://img.shields.io/badge/org.json-20200518-orange)](https://mvnrepository.com/artifact/org.json/json/20200518)

### Introduction

Project goal is to create and manage your own movie library with watched movies.

User can add and delete movies from database, show them in order from the oldest or choose to show them sorted by title, released year or IMDB rating. 
Moreover, database can be exported as a json file, which also can be imported into another database.

### Technologies

App connects to local MySQL database via Hibernate and fetches added movie data from OMDb API. Classes use Lombok for automatic generation of getters, setters and constructors, which help in keeping the code clean. Gson is used for effortless mapping objects to json. Project is managed by Maven library.

### Summary

Project was made as a self-learning exercise. It helped me become familiar with Maven, Hibernate and some design patterns.

#### Tasks list

- [x] add database to the project instead of JSON file
- [x] change all functionalities from using JSON file to operating on database
- [x] change usage of JSON file to additional functionality eg. importing other database or exporting it to another program
- [x] change "choosing number" system to proper command system
- [x] separate persistence from model
- [x] clean code

# Introduction to Service Design and Engineering (fall 2016) - Assignment 3

This repository contains the solution for the third assignment of the course Introduction to Service Design and Engineering from the University of Trento (fall semester, 2016).

The following topics / technologies are covered in this lab assignment:
- SOAP
- Java
- XML
- JAX-WS
- Java Persistence API (JPA)

## Getting started

This project is composed of two parts (client and server) located in different Github repositories. This repository contains only the SOAP server.

The code was written using IntelliJ IDEA 2016.2.5 and the dependencies are managed with Maven.

Health profiles are built dynamically (i.e. support multiple types of measures).

## Demo server

A demo version of the Web Services have been deployed to Heroku in the following URL:

[https://introsde2016-jcamposanok-a3.herokuapp.com/ws/people](https://introsde2016-jcamposanok-a3.herokuapp.com/ws/people)

This test instance has been integrated to Heroku PostgreSQL.

## Running locally

The server can be deployed to a local Tomcat instance. In that case, the persistence drivers will be set up automatically to use SQLite.

The path to the local database file can be specified in the [src/main/resources/project.properties](src/main/resources/project.properties) file.

## Author

* **José Carlos Camposano** - [jcamposanok](https://github.com/jcamposanok)

See also the list of [contributors](https://github.com/jcamposanok/introsde-2016-assignment-3-server/contributors) who participated in this project.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
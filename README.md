# Sample API built with Java Spring Boot

## Database: 
* h2database in memory for test & develop
* Demo database cache

## Services follow Singleton pattern:
* ArticleService interface ensures a contract in terms of functionalities that the application can use. We can have different implementations of that service

## Profiles
* nocache
* cachedb
* cacheview
* redis
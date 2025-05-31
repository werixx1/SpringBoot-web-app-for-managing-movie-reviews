# Movie Reviews Manager
A web application for managing movies, reviews and users using the springboot framework:) 

https://github.com/user-attachments/assets/dfda8174-71c8-402e-b520-113660014bbc

## App features:
- implemented simple website ui using thymeleaf that allows the user to view, add, edit and delete resources using the browser (forms, buttons, etc.). via localhost
- implemented REST API that enables programmatic access to the same data (by using Postman for example) and performing all basic operations (GET, POST, PUT, DELETE)
- implemented a set of custom REST endpoints for data analytics and reports such as for example (access via localhost:8080/ + these):
  ```
  api/movies/title/{movieTitle} <- get movie by title
  api/movies/genre/{genre} <- get movies by genre
  api/movies/best-rated-movie/genre/{genre} <- get best rated movie
  api/movies/worst-rated-movie/genre/{genre} <- get worst rated movie
  api/movies/movie-with-most-reviews <- get movie with most reviews added
  ```
  ```
  api/reviews/user/{userId} <- get all reviews of user
  api/reviews/movie/id/{movieId} <- get reviews for certain movie (by its id)
  api/reviews/latest-review <- get newest review
  ```
   ```
  api/users/username/{username} <- get user by username
  api/users/user-with-most-reviews <- get user with most reviews posted
  api/users/inactive-users <- get inactive users
    ```
  ```
  api/database/raport <- get info about number of users, reviews, movies in the database and how many movies are for each genre
  ```
- in-memory H2 database support

## Examples
![obraz](https://github.com/user-attachments/assets/8784672c-0fec-432b-a1f0-d05bbe0616a0)
![obraz](https://github.com/user-attachments/assets/dcc85bfc-6877-41b8-b2bd-d6db1e70ab5b)


  
##
### helpful resources for understanding springboot:
- crud: https://www.youtube.com/watch?v=7nonQ2dYgiE
- crud: https://www.youtube.com/watch?v=ZZTYQIUd_uY
- query: https://www.youtube.com/watch?v=eSaXN7WqQKI
- query: https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html
- dto: https://www.youtube.com/watch?v=MAIkC9KctVw
- thymeleaf: https://www.youtube.com/watch?v=MUhDe_yZ5WQ

## 
(for me)
project requirements:
- ability to create, edit, delete and browse data related to
selected resources (e.g. books in the library, products in the store, tasks in
the project) ✔️
- ability to assign resources to users ✔️
- ability to search, filter and sort data ✔️
- use of the dependency injection mechanism ✔️
- statistics (sorting, filtring, best rated movies, newest reviews etc) ✔️
- raports !!!!! ✔️
----- 
to do (in the future maybe):
- DTO for creating users etc ✔️
- guide for this repo and postman ✔️
- link useful recources ✔️
- *photo files in movie class for pretty display
- external folder with explanation of spring attributes etc, everything ive learned via doing this project
- login page for users



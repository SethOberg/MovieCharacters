# MovieCharacters

#### Contributors
Milovan Glisovic & Seth Öberg 

### Description
The projects contains basic functionality for running a and managing requests for a database with Movies, characters and the movies respective franchises. The database was created with Postgresql and its contents can be modified and previewed through the Java application. 

### Database content
The database contains three different tables. These are: Movies, Characters and Franchises, each containing columns for basic information and relations to eachother. The relations can bee seen here: 
![MovieCharacters ERD](https://user-images.githubusercontent.com/122837001/223043918-8e1ad3b4-3c5b-4380-98f1-ddd6d2bb4296.png)

### Methods
The Java application cointains basic CRUD functionality for all the different tables. This meaning that the user can create, read, delete and update a specific movie, character or franchise. <br>
The generic methods for all tables are:<br>
Get all: http://localhost:8080/api/v1/"tables".<br>
Get by id: http://localhost:8080/api/v1/"tables"/"ID for object".<br>
Add "Post method": http://localhost:8080/api/v1/"tables", write the object you wish to create in the body.<br>
Delete movie by id "Delete method": http://localhost:8080/api/v1/"tables"/"ID for object".<br>

Other methods include:<br>
Get characters/movies from movie/franchise <br>
Get all characters from a franchise <br>
Add multiple characters/movies to a movie/franchise. <br>

## Techniques
* Java 17
* Lombok
* Postgresql
* Spring data jpa
* Mapstruct
* Swagger

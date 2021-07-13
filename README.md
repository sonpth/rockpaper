`Rock-Paper-Scissors` is a Spring Boot application

### How to run the application
The application comes with a light-weighted HSQLDB. To start the HSQLDB, please follow the below steps

```sh
# Go to HSQLDB folder
cd $APP_FOLDER/hsqldb
# Start the DB server in standalone mode
./runServerMode.sh # or ./runServerMode.bat if on Windows.

# Start the Spring application
java -jar target/rockpaper-0.0.1-SNAPSHOT.jar
```

If we want to run with different DB, please update the `application.properties` accordingly or utilize the Spring profile feature. For example

```sh 
# The configuration can be found in [application-mysql.properties]
java -jar -Dspring.profiles.active=mysql target/rockpaper-0.0.1-SNAPSHOT.jar
```

### Where is the API document
You can access to the Swagger UI via [http://localhost:8080]

### Authentication
All API end-points besides [/rest/user/register] required user to be authenticated in order to consume it. The out-of-the-box DB has default account `admin:123456`, but creating new accounts quite straight forward via the endpoint mentioned above.
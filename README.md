A set of instructions on how to compile and run my application is located below.

Task
1. Design a data model for a simplistic insurance domain. Here is its text representation:
Personal policy is a temporary contract for insuring the vehicles and property belonging to a person. Each insured object
(e.g. vehicle) can have at least two coverages options, e.g. collision coverage (similar to Russian “автогражданка”) and
comprehensive coverage (similar to Russian “автокаско”).
2. Implement spring boot application with at least two methods: to get existing policies and create a new one. For
data stores please use SQL or NoSQL database. docker-compose to run application and database in different
containers would be a big plus.
Make sure you follow the OOP principles and patterns while doing the testing task. As an output please provide a Maven
executable project and a set of instructions on how to compile and run your application.

To set up the application in docker, please run following commands in your terminal.
git clone https://github.com/IvanVarabei/eisTestingTask.git
cd eisTestingTask
mvn clean install
docker-compose up

There is a file called eis.postman_collection.json which contains http requests for the application. 
Just open up your postman -> In the lift top corner click "Import" -> click "Choose files".

Feel free to contact me any time you need.
Skype: live:.cid.8b752fb0eff6795
+375 29 732 45 95
varabeiivan@gamil.com
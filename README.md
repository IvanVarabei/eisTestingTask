1. Design a data model for a simplistic insurance domain. Here is its text representation:
Personal policy is a temporary contract for insuring the vehicles and property belonging to a person. Each insured object
(e.g. vehicle) can have at least two coverages options, e.g. collision coverage (similar to Russian “автогражданка”) and
comprehensive coverage (similar to Russian “автокаско”).
2. Implement spring boot application with at least two methods: to get existing policies and create a new one. For
data stores please use SQL or NoSQL database. docker-compose to run application and database in different
containers would be a big plus.

Make sure you follow the OOP principles and patterns while doing the testing task. As an output please provide a Maven
executable project and a set of instructions on how to compile and run your application.

git clone https://github.com/IvanVarabei/eisTestingTask.git
cd eisTestingTask
mvn clean
mvn install
docker-compose up
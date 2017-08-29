# Environment Simulator

Environment Simulation program predicts environment details such as weather conditions for different locations based on past data.


## Database setup
- Execute the scripts in the location "/src/main/resources/database/table_create_scripts.sql" to create database and tables in mysql database.</br>
- Execute all insert scripts in the location "/src/main/resources/database" to load historical data for default locations to database.</br>

	NOTE : Following excel ("/src/main/resources/database/create_historical_data.xlsx" ) will help to create insert
	scripts of historical data.

	Default Locations : Sydney, Melbourne, Adelaide.
	
- For adding new location download historical data and insert it to database.

## Project setup

###### Prerequisites

- JDK 1.7 or higher (JAVA_HOME and PATH set) for compile and execution
- Apache Maven 3.3 or higher (MVN_HOME and PATH set) for build


###### Build
Go to the project base directory, the directory where pom.xml for environment-simulator is present.

Execute the command to build jar :

```
	mvn clean install
```


The following outputs jar will be generated :  **environment-simulator-1.0.jar**

You can skip the unit test cases by executing below mentioned command :
```
	mvn clean install -Dmaven.test.skip=true
```


## Execution Procedure

This module works in 3 modes :

- **default-one week** (no input parameter)	=> Forecast environment details for one week from today.
- **date** (one input date parameter)		=> Forecast environment details for the specific date.
- **date-range** (two input date parameter)	=> Forecast environment details for the dates between queried start and end dates.

###### Sample commands to execute the program,
```
	java -jar environment-simulator-1.0.jar
	java -jar environment-simulator-1.0.jar <date>
	java -jar environment-simulator-1.0.jar <start-date> <end-date>
```

Note : The date format should be yyyy-MM-dd, for eg: 2017-01-01.

###### Main class

`com.cba.simulator.bootstrap.SimulatorBootstrap.java`

###### Dataset Considered

Historical weather data between 2009 and 2016 (both years inclusive) for 3 locations listed below :

- Sydney
- Melbourne
- Adelaide

	Link to source data : [historical data source](https://www.wunderground.com/history/).

###### Forecast Result

After execution of the program, the forecasted weather data will be generated as a text file in the output directory.

Format of data in the output file : 
- **location|latitude,longitude,elevation|date_time|conditions|temperature|pressure|humidity**

###### Sample output

-  **Sydney|-33.52,151.13,58|2017-08-30 10:23:27|Warm|16.8|53.2|1016**
-  **Melbourne|-37.49,144.58,25|2017-08-30 10:23:27|Rain|11.1|70.4|1018**
-  **Adelaide|-34.56,138.36,59|2017-08-30 10:23:27|Rain|16.7|61.3|1014**



## References
###### TestCases:

JUnit test cases are available in the following path within the project,

`"/src/test/java"`

which would run on build using Maven.



###### Documentation:

Documentations are available in the following path within the project,

`"/doc" => Auto-generated javadoc`


## Author / Contribution

Gills Antony

CREATE TABLE location
(
	location_id int NOT NULL PRIMARY KEY AUTO_INCREMENT,
	location_name varchar(100),
	location_code varchar(100),
	latitude decimal(8,2),
	longitude decimal(8,2),
	elevation int
);


CREATE TABLE weather_details
(
	id int NOT NULL PRIMARY KEY AUTO_INCREMENT,
	location_id int NOT NULL,
	date date,
	temperature decimal(8,2),
	pressure int,
	humidity int,
	conditions varchar(50),
	FOREIGN KEY (location_id) REFERENCES location(location_id)
);
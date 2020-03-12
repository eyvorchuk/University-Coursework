CREATE TABLE Officials (
	offid CHAR(8) PRIMARY KEY,
	name VARCHAR(30),
	address VARCHAR(40)
);

CREATE TABLE Complexes (
	cid CHAR(5) PRIMARY KEY,
	location VARCHAR(50),
	area FLOAT,
	chiefid CHAR(8),
	FOREIGN KEY (chiefid) REFERENCES Officials(offid) 
);

CREATE TABLE Sports (
	name VARCHAR(20)
);

CREATE TABLE DesignatedFor (
	compid CHAR(5),
	sportname VARCHAR(20),
	locIndicator VARCHAR(10),
	PRIMARY KEY (compid,sportname),
	FOREIGN KEY (compid) REFERENCES Complexes(cid),
	FOREIGN KEY (sportname) REFERENCES Sports(name) 
);

CREATE TABLE Events (
	eventid CHAR(7) PRIMARY KEY,
	description VARCHAR(100),
	plandate CHAR(6),
	duration INT,
	numParticipants INT,
	comp CHAR(5),
	FOREIGN KEY (comp) REFERENCES Complexes(cid)
);

CREATE TABLE Involved (
	event CHAR(7),
	official CHAR(8),
	PRIMARY KEY (event, official),
	FOREIGN KEY (event) REFERENCES Events(eventid),
	FOREIGN KEY (official) REFERENCES Officials(offid) 
);

CREATE TABLE Equipment (
	name VARCHAR(15),
	weight FLOAT,
	numStock INT,
	PRIMARY KEY (name, weight)
);

CREATE TABLE Needed (
	event CHAR(7),
	equipname VARCHAR(15),
	equipweight FLOAT,
	numNeeded INT,
	PRIMARY KEY (event, equipname, equipweight),
	FOREIGN KEY (event) REFERENCES Events(eventid),
	FOREIGN KEY (equipname, equipweight) REFERENCES Equipment(name, weight)
);
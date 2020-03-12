CREATE TABLE Doctors(
	id CHAR(10) PRIMARY KEY,
	name VARCHAR(25),
	specialty VARCHAR(20),
	experience INT
);

CREATE TABLE HospDoctors(
	id CHAR(10) PRIMARY KEY REFERENCES Doctors,
	hospname VARCHAR(20)
);

CREATE TABLE FamPhys(
	id CHAR(10) PRIMARY KEY REFERENCES Doctors,
	offaddress VARCHAR(20)
);

INSERT INTO Doctors VALUES('24601133742', 'Jimothy Halpert', 'Radiologist', 0);
INSERT INTO HospDoctors VALUES('24601133742', 'Scranton Hospital');
INSERT INTO FamPhys VALUES('24601133742', '1725 Slough Avenue');
	
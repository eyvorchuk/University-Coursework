CREATE TABLE Personnel(
	pid CHAR(8) PRIMARY KEY,
	name VARCHAR(30),
	DoB CHAR(6),
	PoB VARCHAR(40)
);

CREATE TABLE Teams(
	name VARCHAR(50),
	division INT,
	city VARCHAR(25),
	league VARCHAR(25)
);

CREATE TABLE Players(
	pid CHAR(8) PRIMARY KEY REFERENCES Personnel,
	orientation VARCHAR(15),
	BA FLOAT,
	teamname VARCHAR(50) REFERENCES Teams(name)
);

CREATE TABLE Coaches(
	pid CHAR(8) PRIMARY KEY REFERENCES Personnel,
	teamname VARCHAR(50) REFERENCES Teams(name)
);

CREATE TABLE Managers(
	pid CHAR(8) PRIMARY KEY REFERENCES Personnel,
	teamname VARCHAR(50) REFERENCES Teams(name)
);

CREATE Table Umpires(
	pid CHAR(8) PRIMARY KEY REFERENCES Personnel
);

CREATE TABLE Pitchers(
	pid CHAR(8) PRIMARY KEY REFERENCES Players,
	ERA FLOAT
);

CREATE TABLE Games(
	gid CHAR(5) PRIMARY KEY,
	playdate CHAR(6),
	winpitcher CHAR(8) REFERENCES Pitchers(pid),
	losepitcher CHAR(8) REFERENCES Pitchers(pid),
	savepitcher CHAR(8) REFERENCES Pitchers(pid),
	team1 VARCHAR(50) REFERENCES Teams(name),
	team2 VARCHAR(50) REFERENCES Teams(name)
);

CREATE TABLE Plays(
	gid Char(5) PRIMARY KEY REFERENCES Games,
	runs1 INT,
	hits1 INT,
	errors1 INT,
	runs2 INT,
	hits2 INT,
	errors2 INT
);

CREATE TABLE Hits(
	pid CHAR(8),
	gid CHAR(5),
	singles INT,
	doubles INT,
	triples INT,
	hruns INT,
	PRIMARY KEY(pid, gid),
	pid REFERENCES Players(pid),
	gid REFERENCES Games(gid)
);
	
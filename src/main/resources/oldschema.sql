CREATE TABLE developers(
    id INTEGER PRIMARY KEY,
    company VARCHAR(20) NOT NULL,
    department VARCHAR(20) NOT NULL);

CREATE TABLE games(
    id INTEGER PRIMARY KEY,
    title VARCHAR (20) NOT NULL,
    developer_id INTEGER NOT NULL,
    description VARCHAR (100),
    category VARCHAR (15),
    platform VARCHAR (10),
    releaseDate TIMESTAMP);


alter table games add constraint games_developer_id foreign key (developer_id) references developers;
DROP TABLE IF EXISTS client;
CREATE TABLE client (
                       id       INTEGER NOT NULL AUTO_INCREMENT,
                       firstName    VARCHAR(255),
                       lastName VARCHAR(255),
                       middleName VARCHAR(255),
                       inn INTEGER not null ,
                       PRIMARY KEY (id)
);

DROP TABLE IF EXISTS transaction;
CREATE TABLE transaction (
                          id       INTEGER NOT NULL AUTO_INCREMENT,
                          place VARCHAR(255),
                          amount BIGINT NOT NULL ,
                          currency VARCHAR(255) NOT NULL,
                          card    VARCHAR(255) NOT NULL ,
                          clientId INTEGER,
                          PRIMARY KEY (id),
                          FOREIGN KEY (clientId) references client (id)
);
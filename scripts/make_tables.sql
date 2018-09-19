CONNECT 'jdbc:derby:../data/tables;create=true';
CREATE TABLE regions (name VARCHAR(15) PRIMARY KEY,
                      taxRate REAL,
                      entryCost REAL,
                      logisticsCost INT,
                      marketingCost INT,
                      efficiencyCost INT);
CREATE TABLE events (name VARCHAR(15) PRIMARY KEY,
                     description VARCHAR(300),
                     alignment REAL,
                     logisticsEffect INT,
                     marketingEffect INT,
                     productEffect INT,
                     cashEffect REAL);
DISCONNECT;
EXIT;

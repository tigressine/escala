/* 
Part of Escala.
Written by Tiger Sachse.
*/

CONNECT 'jdbc:derby:../data/tables;create=true';

CREATE TABLE regions (
    name VARCHAR(15),
    taxRate REAL,
    entryCost INT,
    logisticsCost INT,
    marketingCost INT,
    efficiencyCost INT,
    worldShare REAL,
    centerX INT,
    centerY INT,
    PRIMARY KEY (name)
);
CREATE TABLE events (
    name VARCHAR(15),
    description VARCHAR(300),
    alignment REAL,
    cashEffect REAL,
    logisticsEffect INT,
    marketingEffect INT,
    efficiencyEffect INT,
    PRIMARY KEY (name)
);
CREATE TABLE skillNodes (
    treeID VARCHAR(100),
    nodeID INT,
    name VARCHAR(100),
    description VARCHAR(300),
    cost REAL,
    logisticsEffect INT,
    marketingEffect INT,
    efficiencyEffect INT,
    PRIMARY KEY (nodeID, treeID)
);
CREATE TABLE skillEdges (
    treeID VARCHAR(100),
    startNode INT,
    followNode INT,
    PRIMARY KEY (treeID, startNode, followNode),
    FOREIGN KEY (startNode, treeID)
    REFERENCES skillNodes,
    FOREIGN KEY (followNode, treeID)
    REFERENCES skillNodes
);

DISCONNECT;
EXIT;

/* 
Part of Escala.
Written by Tiger Sachse.
*/

CONNECT 'jdbc:derby:../data/tables;create=true';
CREATE TABLE regions (
    name VARCHAR(15) PRIMARY KEY,
    taxRate REAL,
    entryCost REAL,
    logisticsCost INT,
    marketingCost INT,
    efficiencyCost INT
);
CREATE TABLE events (
    name VARCHAR(15) PRIMARY KEY,
    description VARCHAR(300),
    alignment REAL,
    cashEffect REAL,
    logisticsEffect INT,
    marketingEffect INT,
    efficiencyEffect INT
);
CREATE TABLE skill_nodes (
    node_id INT,
    tree_id VARCHAR(100),
    name VARCHAR(100),
    description VARCHAR(300),
    cost REAL,
    logisticsEffect INT,
    marketingEffect INT,
    efficiencyEffect INT,
    PRIMARY KEY (node_id, tree_id)
);
CREATE TABLE skill_edges (
    tree_id VARCHAR(100),
    start_node INT,
    follow_node INT,
    PRIMARY KEY (tree_id, start_node, follow_node),
    FOREIGN KEY (start_node, tree_id)
    REFERENCES skill_nodes,
    FOREIGN KEY (follow_node, tree_id)
    REFERENCES skill_nodes
);
DISCONNECT;
EXIT;

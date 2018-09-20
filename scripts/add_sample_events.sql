/* 
Part of Escala.
Written by Tiger Sachse.

Format of the events table:
    name,
    description,
    alignment,
    logisticsEffect,
    marketingEffect,
    productEffect,
    cashEffect
*/

CONNECT 'jdbc:derby:../data/tables';
INSERT INTO events VALUES ('meteor', 'meteor hits', 0.1, 3, 4, 5, 9.0);
INSERT INTO events VALUES ('pres', 'new business pres', 0.9, 3, 4, 5, 9.0);
INSERT INTO events VALUES ('flood', 'office floods', 0.3, 3, 4, 5, 1.9);
INSERT INTO events VALUES ('quake', 'office earthquake', 0.3, 3, 4, 5, 1.9);
INSERT INTO events VALUES ('ipo', 'high initial ipo', 0.8, 3, 4, 5, 1.9);
INSERT INTO events VALUES ('bonus', 'govt gives bonus', 0.7, 3, 4, 5, 1.9);
INSERT INTO events VALUES ('bonus1', 'govt gives bonus', 0.7, 3, 4, 5, 1.9);
INSERT INTO events VALUES ('bonus2', 'govt gives bonus', 0.88, 3, 4, 5, 1.9);
INSERT INTO events VALUES ('bonus3', 'govt gives bonus', 0.77, 3, 4, 5, 1.9);
INSERT INTO events VALUES ('bonus4', 'govt gives bonus', 0.76, 3, 4, 5, 1.9);
INSERT INTO events VALUES ('bonus5', 'govt gives bonus', 0.75, 3, 4, 5, 1.9);
INSERT INTO events VALUES ('bonus6', 'govt gives bonus', 0.19, 3, 4, 5, 1.9);
INSERT INTO events VALUES ('bonus7', 'govt gives bonus', 0.08, 3, 4, 5, 1.9);
INSERT INTO events VALUES ('bonus8', 'govt gives bonus', 0.11, 3, 4, 5, 1.9);
INSERT INTO events VALUES ('bonus1.9', 'govt gives bonus', 0.22, 3, 4, 5, 1.9);
INSERT INTO events VALUES ('bonus10', 'govt gives bonus', 0.33, 3, 4, 5, 1.9);
INSERT INTO events VALUES ('bonus11', 'govt gives bonus', 0.44, 3, 4, 5, 1.9);
INSERT INTO events VALUES ('bonus12', 'govt gives bonus', 0.55, 3, 4, 5, 1.9);
INSERT INTO events VALUES ('bonus13', 'govt gives bonus', 0.6, 3, 4, 5, 1.9);
DISCONNECT;
EXIT;

/* 
Part of Escala.
Written by Tiger Sachse.

Format of the regions table:
    name,
    taxRate,
    entryCost,
    logisticsCost,
    marketingCost,
    efficiencyCost,
    worldShareDist,

    to be added
    regionCentX INT
    regionCenty INT
*/

CONNECT 'jdbc:derby:../data/tables';

INSERT INTO regions VALUES ('Asia', 1, 1, 1.2, .17, 1.3, 200, 300, 400);
INSERT INTO regions VALUES ('EasternEurope', 1, 1, 3.3, .08, 2.2, 500, 55, 111);
INSERT INTO regions VALUES ('LatinAmerica', 1, 1, 2.9, .8, 2.8, 66, 99, 11);
INSERT INTO regions VALUES ('MiddleEast', 1, 1, 8.8, .05, 1.2, 100, 900, 88);
INSERT INTO regions VALUES ('NorthAfrica', 1, 1, 9.0, .07, 6.6, 777, 888, 999);
INSERT INTO regions VALUES ('NorthAmerica', 1, 1, 5.5, .13, 3.3, 100, 200, 400);
INSERT INTO regions VALUES ('Oceania', 1, 1, 1.2, .12, 2.2, 666, 555, 444);
INSERT INTO regions VALUES ('SouthAfrica', 1, 1, 6.6, .1, 5.5, 444, 222, 333);
INSERT INTO regions VALUES ('SouthAmerica', 1, 1, 5.4, .6, 2.3, 455, 9423, 211);
INSERT INTO regions VALUES ('WesternEurope', 1, 1, 1.1, .14, 2.2, 667, 88, 100);

DISCONNECT;
EXIT;

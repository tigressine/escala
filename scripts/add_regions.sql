/* 
Part of Escala.
Written by Tiger Sachse.

Format of the regions table:
    name,
    taxRate,
    entryCost,
    logisticsCost,
    marketingCost,
    efficiencyCost
*/
CONNECT 'jdbc:derby:../data/tables';
INSERT INTO regions VALUES ('Asia', 1.2, 1.3, 200, 300, 400);
INSERT INTO regions VALUES ('EasternEurope', 1.8, 2.2, 500, 55, 111);
INSERT INTO regions VALUES ('LatinAmerica', 2.9, 2.8, 66, 99, 11);
INSERT INTO regions VALUES ('MiddleEast', 8.8, 1.2, 100, 900, 88);
INSERT INTO regions VALUES ('NorthAfrica', 9.0, 6.6, 777, 888, 999);
INSERT INTO regions VALUES ('NorthAmerica', 5.5, 3.3, 100, 200, 400);
INSERT INTO regions VALUES ('Oceania', 1.2, 2.2, 666, 555, 444);
INSERT INTO regions VALUES ('SouthAfrica', 6.6, 5.5, 444, 222, 333);
INSERT INTO regions VALUES ('SouthAmerica', 5.4, 2.3, 455, 9423, 211);
INSERT INTO regions VALUES ('WesternEurope', 1.1, 2.2, 667, 88, 100);
DISCONNECT;
EXIT;

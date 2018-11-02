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
    regionCentX,
    regionCenty
*/

CONNECT 'jdbc:derby:../data/tables';

INSERT INTO regions VALUES ('Asia', 1, 1, 1.2, .17, 1.3, 17, 867, 219);
INSERT INTO regions VALUES ('EasternEurope', 1, 1, 3.3, .08, 2.2, 8, 768, 94);
INSERT INTO regions VALUES ('LatinAmerica', 1, 1, 2.9, .8, 2.8, 8, 145, 265);
INSERT INTO regions VALUES ('MiddleEast', 1, 1, 8.8, .05, 1.2, 5, 703, 205);
INSERT INTO regions VALUES ('NorthAfrica', 1, 1, 9.0, .07, 6.6, 7, 571, 263);
INSERT INTO regions VALUES ('NorthAmerica', 1, 1, 5.5, .13, 3.3, 13, 169, 132);
INSERT INTO regions VALUES ('Oceania', 1, 1, 1.2, .12, 2.2, 12, 974, 444);
INSERT INTO regions VALUES ('SouthAfrica', 1, 1, 6.6, .1, 5.5, 10, 609, 400);
INSERT INTO regions VALUES ('SouthAmerica', 1, 1, 5.4, .6, 2.3, 6, 266, 397);
INSERT INTO regions VALUES ('WesternEurope', 1, 1, 1.1, .14, 2.2, 14, 528, 135);

DISCONNECT;
EXIT;

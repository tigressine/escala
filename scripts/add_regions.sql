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

INSERT INTO regions VALUES ('Asia', 1, 5000, 13, 11, 11, .17, 867, 219);
INSERT INTO regions VALUES ('EasternEurope', 1, 3000, 14, 7, 10, .08, 768, 94);
INSERT INTO regions VALUES ('LatinAmerica', 1, 2000, 8, 12, 7, .08, 145, 265);
INSERT INTO regions VALUES ('MiddleEast', 1, 2500, 7, 6, 8, .05, 703, 205);
INSERT INTO regions VALUES ('NorthAfrica', 1, 1500, 10, 13, 7, .07, 565, 263);
INSERT INTO regions VALUES ('NorthAmerica', 1, 4500, 10, 11, 15, .13, 179, 142);
INSERT INTO regions VALUES ('Oceania', 1, 4000, 9, 12, 6, .12, 974, 444);
INSERT INTO regions VALUES ('SouthAfrica', 1, 2500, 11, 7, 11, .10, 599, 400);
INSERT INTO regions VALUES ('SouthAmerica', 1, 2000, 12, 7, 10, .06, 284, 397);
INSERT INTO regions VALUES ('WesternEurope', 1, 2500, 6, 14, 15, .14, 528, 135);

DISCONNECT;
EXIT;

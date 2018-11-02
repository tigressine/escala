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

INSERT INTO regions VALUES ('Asia', 1, 5000, 22, 17, 13, .17, 867, 219);
INSERT INTO regions VALUES ('EasternEurope', 1, 3000, 33, 18, 22, .08, 768, 94);
INSERT INTO regions VALUES ('LatinAmerica', 1, 2000, 10, 18, 8, .08, 145, 265);
INSERT INTO regions VALUES ('MiddleEast', 1, 2500, 20, 15, 12, .05, 703, 205);
INSERT INTO regions VALUES ('NorthAfrica', 1, 1500, 13, 17, 8, .07, 565, 263);
INSERT INTO regions VALUES ('NorthAmerica', 1, 4500, 12, 13, 33, .13, 179, 142);
INSERT INTO regions VALUES ('Oceania', 1, 4000, 32, 12, 22, .12, 974, 444);
INSERT INTO regions VALUES ('SouthAfrica', 1, 2500, 16, 13, 15, .10, 599, 400);
INSERT INTO regions VALUES ('SouthAmerica', 1, 2000, 15, 16, 23, .06, 284, 397);
INSERT INTO regions VALUES ('WesternEurope', 1, 2500, 15, 14, 22, .14, 528, 135);

DISCONNECT;
EXIT;

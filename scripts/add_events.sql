/*
Part of Escala.
Written by Tiger Sachse.
*/

CONNECT 'jdbc:derby:../data/tables';
INSERT INTO events VALUES ('Good IPO', 'Your company had a successful initial public offering (IPO)!', .7, 1000.00, 0, 5, 1);
INSERT INTO events VALUES ('Meteor Strike', 'A massive meteor has struck the planet, resulting in catastrophic destruction!', .1, -1000.00, -10, -10, -10);
DISCONNECT;
Exit;

/*
Part of Escala.
Written by Tiger Sachse.
This script has been automatically generated.
*/

CONNECT 'jdbc:derby:../data/tables';

INSERT INTO skillNodes VALUES ('Sample2', 1, 'Node one', 'A description for node one sam 2.', 1.0, 1, 1, 1);
INSERT INTO skillNodes VALUES ('Sample2', 2, 'Node two', 'A description for node two sam 2.', 2.0, 2, 2, 2);
INSERT INTO skillNodes VALUES ('Sample2', 3, 'Node three', 'A description for node three sam 2.', 3.0, 3, 3, 3);
INSERT INTO skillNodes VALUES ('Sample2', 4, 'Node four', 'A description for node four sam 2.', 4.5, 4, 4, 4);
INSERT INTO skillNodes VALUES ('Sample2', 5, 'Node five', 'A description for node five sam 2.', 5.6, 5, 5, 5);
INSERT INTO skillNodes VALUES ('Sample2', 6, 'Node six', 'A description for node six sam 2.', 6.0, 6, 6, 6);
INSERT INTO skillNodes VALUES ('Sample2', 7, 'Node seven', 'A description for node seven sam 2.', 7.22, 7, 7, 7);
INSERT INTO skillNodes VALUES ('Sample2', 8, 'Node eight', 'A description for node eight sam 2.', 8.44, 8, 8, 8);
INSERT INTO skillNodes VALUES ('Sample2', 9, 'Node nine', 'A description for node nine sam 2.', 9.22, 9, 9, 9);
INSERT INTO skillNodes VALUES ('Sample2', 10, 'Node ten', 'A description for node ten sam 2.', 10.11, 10, 10, 10);
INSERT INTO skillNodes VALUES ('Sample2', 11, 'Node eleven', 'A description for node eleven sam 2.', 11.12, 11, 11, 11);
INSERT INTO skillNodes VALUES ('Sample2', 12, 'Node twelve', 'A description for node twelve sam 2.', 12.222, 12, 12, 12);
INSERT INTO skillNodes VALUES ('Sample2', 13, 'Node thirteen', 'A description for node thirteen sam 2.', 13.44, 13, 13, 13);
INSERT INTO skillNodes VALUES ('Sample2', 14, 'Node fourteen', 'A description for node fourteen sam 2.', 14.44, 14, 14, 14);
INSERT INTO skillNodes VALUES ('Sample2', 15, 'Node fifteen', 'A description for node fifteen sam 2.', 15.111, 15, 15, 15);

INSERT INTO skillEdges VALUES ('Sample2', 1, 3);
INSERT INTO skillEdges VALUES ('Sample2', 1, 4);
INSERT INTO skillEdges VALUES ('Sample2', 1, 5);
INSERT INTO skillEdges VALUES ('Sample2', 2, 4);
INSERT INTO skillEdges VALUES ('Sample2', 2, 5);
INSERT INTO skillEdges VALUES ('Sample2', 2, 6);
INSERT INTO skillEdges VALUES ('Sample2', 3, 7);
INSERT INTO skillEdges VALUES ('Sample2', 4, 11);
INSERT INTO skillEdges VALUES ('Sample2', 5, 8);
INSERT INTO skillEdges VALUES ('Sample2', 5, 9);
INSERT INTO skillEdges VALUES ('Sample2', 5, 10);
INSERT INTO skillEdges VALUES ('Sample2', 6, 10);
INSERT INTO skillEdges VALUES ('Sample2', 7, 11);
INSERT INTO skillEdges VALUES ('Sample2', 7, 12);
INSERT INTO skillEdges VALUES ('Sample2', 8, 12);
INSERT INTO skillEdges VALUES ('Sample2', 8, 13);
INSERT INTO skillEdges VALUES ('Sample2', 9, 12);
INSERT INTO skillEdges VALUES ('Sample2', 9, 13);
INSERT INTO skillEdges VALUES ('Sample2', 10, 14);
INSERT INTO skillEdges VALUES ('Sample2', 10, 15);
INSERT INTO skillEdges VALUES ('Sample2', 14, 15);

INSERT INTO skillNodes VALUES ('Sample', 1, 'Node one', 'A description for node one.', 1.0, 1, 1, 1);
INSERT INTO skillNodes VALUES ('Sample', 2, 'Node two', 'A description for node two.', 2.0, 2, 2, 2);
INSERT INTO skillNodes VALUES ('Sample', 3, 'Node three', 'A description for node three.', 3.0, 3, 3, 3);
INSERT INTO skillNodes VALUES ('Sample', 4, 'Node four', 'A description for node four.', 4.5, 4, 4, 4);
INSERT INTO skillNodes VALUES ('Sample', 5, 'Node five', 'A description for node five.', 5.6, 5, 5, 5);
INSERT INTO skillNodes VALUES ('Sample', 6, 'Node six', 'A description for node six.', 6.0, 6, 6, 6);
INSERT INTO skillNodes VALUES ('Sample', 7, 'Node seven', 'A description for node seven.', 7.22, 7, 7, 7);
INSERT INTO skillNodes VALUES ('Sample', 8, 'Node eight', 'A description for node eight.', 8.44, 8, 8, 8);
INSERT INTO skillNodes VALUES ('Sample', 9, 'Node nine', 'A description for node nine.', 9.22, 9, 9, 9);
INSERT INTO skillNodes VALUES ('Sample', 10, 'Node ten', 'A description for node ten.', 10.11, 10, 10, 10);
INSERT INTO skillNodes VALUES ('Sample', 11, 'Node eleven', 'A description for node eleven.', 11.12, 11, 11, 11);
INSERT INTO skillNodes VALUES ('Sample', 12, 'Node twelve', 'A description for node twelve.', 12.222, 12, 12, 12);
INSERT INTO skillNodes VALUES ('Sample', 13, 'Node thirteen', 'A description for node thirteen.', 13.44, 13, 13, 13);
INSERT INTO skillNodes VALUES ('Sample', 14, 'Node fourteen', 'A description for node fourteen.', 14.44, 14, 14, 14);
INSERT INTO skillNodes VALUES ('Sample', 15, 'Node fifteen', 'A description for node fifteen.', 15.111, 15, 15, 15);

INSERT INTO skillEdges VALUES ('Sample', 1, 3);
INSERT INTO skillEdges VALUES ('Sample', 1, 4);
INSERT INTO skillEdges VALUES ('Sample', 1, 5);
INSERT INTO skillEdges VALUES ('Sample', 2, 4);
INSERT INTO skillEdges VALUES ('Sample', 2, 5);
INSERT INTO skillEdges VALUES ('Sample', 2, 6);
INSERT INTO skillEdges VALUES ('Sample', 3, 7);
INSERT INTO skillEdges VALUES ('Sample', 4, 11);
INSERT INTO skillEdges VALUES ('Sample', 5, 8);
INSERT INTO skillEdges VALUES ('Sample', 5, 9);
INSERT INTO skillEdges VALUES ('Sample', 5, 10);
INSERT INTO skillEdges VALUES ('Sample', 6, 10);
INSERT INTO skillEdges VALUES ('Sample', 7, 11);
INSERT INTO skillEdges VALUES ('Sample', 7, 12);
INSERT INTO skillEdges VALUES ('Sample', 8, 12);
INSERT INTO skillEdges VALUES ('Sample', 8, 13);
INSERT INTO skillEdges VALUES ('Sample', 9, 12);
INSERT INTO skillEdges VALUES ('Sample', 9, 13);
INSERT INTO skillEdges VALUES ('Sample', 10, 14);
INSERT INTO skillEdges VALUES ('Sample', 10, 15);
INSERT INTO skillEdges VALUES ('Sample', 14, 15);

DISCONNECT;
Exit;

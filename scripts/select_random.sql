CONNECT 'jdbc:derby:../data/tables';
SELECT * FROM regions
    WHERE logisticsCost < 600
    AND logisticsCost > 100
    ORDER BY RANDOM()
    { LIMIT 3 };

SELECT * FROM regions;
DISCONNECT;
EXIT;

CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `opatravelagency`.`flight_complet_vw` AS
    SELECT 
        `flg`.`Id_flights` AS `Id_flights`,
        `flg`.`departureTime_flights` AS `departureTime_flights`,
        `cty`.`Id_cities` AS `Id_cities`,
        `cty`.`name_cities` AS `name_cities`
    FROM
        (`opatravelagency`.`flights` `flg`
        LEFT JOIN `opatravelagency`.`cities` `cty` ON ((`cty`.`Id_cities` = `flg`.`Id_cities_hotels`)))
    GROUP BY `flg`.`Id_flights`
CREATE 
	ALGORITHM=UNDEFINED 
	DEFINER=`root`@`localhost` 
    SQL SECURITY DEFINER 
VIEW `opatravelagency`.`land_trip_cities_vw` AS
    SELECT 
        `clt`.`id_landTrip_citieslandtrip` AS `id_landTrip_citieslandtrip`,
        `cty`.`Id_cities` AS `Id_cities`,
        `cty`.`name_cities` AS `name_cities`
    FROM
        (`opatravelagency`.`citieslandtrip` `clt`
        LEFT JOIN `opatravelagency`.`cities` `cty` ON ((`cty`.`Id_cities` = `clt`.`id_cities_citieslandtrip`)))
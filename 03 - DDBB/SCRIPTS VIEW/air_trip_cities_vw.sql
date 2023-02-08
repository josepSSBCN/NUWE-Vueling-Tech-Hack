CREATE 
	ALGORITHM=UNDEFINED 
    DEFINER=`root`@`localhost` 
    SQL SECURITY DEFINER 
VIEW `opatravelagency`.`air_trip_cities_vw` AS 
	select 
		`opatravelagency`.`citiesairtrip`.`id_airTrip_citiesairtrip` AS `id_airTrip_citiesairtrip`,
        `cty`.`Id_cities` AS `Id_cities`,
        `cty`.`name_cities` AS `name_cities` 
	from 
		(`opatravelagency`.`citiesairtrip` 
        left join `opatravelagency`.`cities` `cty` on((`cty`.`Id_cities` = `opatravelagency`.`citiesairtrip`.`id_cities_citiesairtrip`)))
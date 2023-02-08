CREATE 
	ALGORITHM=UNDEFINED 
    DEFINER=`root`@`localhost` 
    SQL SECURITY DEFINER 
VIEW `opatravelagency`.`air_trip_flights_vw` AS 
	select 
		`fat`.`id_airTrip_flightsairtrip` AS `id_airTrip_flightsairtrip`,
		`flg`.`Id_flights` AS `Id_flights`,
		`flg`.`departureTime_flights` AS `departureTime_flights`,
		`cty`.`Id_cities` AS `Id_cities`,
		`cty`.`name_cities` AS `name_cities` 
    from 
		((`opatravelagency`.`flightsairtrip` `fat` 
        left join `opatravelagency`.`flights` `flg` on((`flg`.`Id_flights` = `fat`.`id_flights_flightsairtrip`))) 
        left join `opatravelagency`.`cities` `cty` on((`cty`.`Id_cities` = `flg`.`Id_cities_flights`)))
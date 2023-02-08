CREATE 
	ALGORITHM=UNDEFINED 
	DEFINER=`root`@`localhost` 
    SQL SECURITY DEFINER 
VIEW `opatravelagency`.`flight_complet_vw` AS 
	select 
		`flg`.`Id_flights` AS `Id_flights`,
        `flg`.`departureTime_flights` AS `departureTime_flights`,
        `cty`.`Id_cities` AS `Id_cities`,
        `cty`.`name_cities` AS `name_cities` 
	from 
		(`opatravelagency`.`flights` `flg` 
        left join `opatravelagency`.`cities` `cty` on((`cty`.`Id_cities` = `flg`.`Id_cities_flights`))) 
	group by `flg`.`Id_flights`
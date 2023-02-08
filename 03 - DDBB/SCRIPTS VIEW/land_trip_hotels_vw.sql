CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `opatravelagency`.`land_trip_hotels_vw` AS
    SELECT 
        `hlt`.`id_landTrip_hotelslandtrip` AS `id_landTrip_hotelslandtrip`,
        `htl`.`Id_hotels` AS `Id_hotels`,
        `htl`.`name_hotels` AS `name_hotels`,
        `htl`.`idCategory_hotels` AS `idCategory_hotels`,
        `cty`.`Id_cities` AS `Id_cities`,
        `cty`.`name_cities` AS `name_cities`
    FROM
        ((`opatravelagency`.`hotelslandtrip` `hlt`
        LEFT JOIN `opatravelagency`.`hotels` `htl` ON ((`htl`.`Id_hotels` = `hlt`.`id_hotels_hotelslandtrip`)))
        LEFT JOIN `opatravelagency`.`cities` `cty` ON ((`cty`.`Id_cities` = `htl`.`Id_cities_hotels`)))
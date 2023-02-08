package org.hackVueling.model.classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
    //region ATTRIBUTES
    private final static String url = "jdbc:mysql://localhost:3306/opatravelagency";
    private final static String userBasic = "opabasic";
    private final static String userAdmin = "opaadministrador";
    private final static String passBasic = "OPABaUnoDosTres";
    private final static String passAdmin = "OPAAdCincoSeis";

    //endregion ATTRIBUTES


    //region METHODS
    public Connection connectionBasic() throws SQLException {
        Connection connect = null;

        connect = DriverManager.getConnection(url, userBasic, passBasic);

        return connect;
    }

    public Connection connectionAdmin() throws SQLException {
        Connection connect = null;

        connect = DriverManager.getConnection(url, userAdmin, passAdmin);

        return connect;
    }

    //endregion METHODS


}

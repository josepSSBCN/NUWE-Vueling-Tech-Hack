package org.hackVueling.model.classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe to manage the connections with SQL ddbb.
 */
public class Connect {
    //region ATTRIBUTES

    private static Connect instance;
    private static Connection connectionBasic;
    private static Connection connectionAdmin;

    private final static String url = "jdbc:mysql://localhost:3306/opatravelagency";
    private final static String userBasic = "opabasic";
    private final static String userAdmin = "opaadministrador";
    private final static String passBasic = "OPABaUnoDosTres";
    private final static String passAdmin = "OPAAdCincoSeis";

    //endregion ATTRIBUTES


    //region CONSTRUCTOR
    private Connect() {

    }

    public static Connect getInstance() {
        if (instance == null) {
            instance = new Connect();
        }
        return instance;
    }

    //endregion CONSTRUCTOR


    //region METHODS

    /**
     * Method to do a connection with basic user level, if that is null is created it.
     *
     * @return The connection.
     * @throws SQLException Throws if some problem with SQL connection.
     */
    public static Connection getConnectionBasic() throws SQLException {
        if (connectionBasic == null) {
            connectionBasic = DriverManager.getConnection(url, userBasic, passBasic);
        }
        return connectionBasic;
    }

    /**
     * Method to do a connection with admin user.
     *
     * @return The connection.
     * @throws SQLException Throws if some problem with SQL connection.
     */
    public static Connection getconnectionAdmin() throws SQLException {
        if (connectionAdmin == null) {
            connectionAdmin = DriverManager.getConnection(url, userAdmin, passAdmin);
        }
        return connectionAdmin;
    }

    //endregion METHODS


}

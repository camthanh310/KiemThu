/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author TTC
 */
public class Login {
    public static PreparedStatement startLogin(String username, String password) throws SQLException {
        PreparedStatement ps = ConnectingDB.getConnection().prepareStatement("select * "
                    + "from TaiKhoan "
                    + "where username = ? and password = ?");

        ps.setString(1, username);
        ps.setString(2, password);    
        return ps;
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author TTC
 */
public class Login {
    public static PreparedStatement startLogin(String username, String password) throws SQLException {
        username = username.trim();
        password = password.trim();
        PreparedStatement ps = ConnectingDB.getConnection().prepareStatement("select * "
                    + "from TaiKhoan "
                    + "where username = ? and password = ?");

        ps.setString(1, username);
        ps.setString(2, password);    
        return ps;
    }
    
    public static String layLoaiNguoiDung(String Username) throws SQLException {
        PreparedStatement ps = ConnectingDB.getConnection().prepareStatement("SELECT LoaiNguoiDung FROM TaiKhoan WHERE username=?");
        ps.setString(1, Username);
        ResultSet rs = ps.executeQuery();
        rs.next();
        String strLoaiNguoiDung = rs.getString("LoaiNguoiDung");
        return strLoaiNguoiDung;
    }
}

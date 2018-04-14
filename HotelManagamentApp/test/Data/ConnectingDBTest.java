/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import hotelmanagamentapp.LapDanhMucPhongController;
import hotelmanagamentapp.LoaiPhong;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Hau
 */
public class ConnectingDBTest {

    public ConnectingDBTest() {
    }
    private static Connection con;

    String queryHienThiLoaiPhong = "SELECT * FROM LOAIPHONG";
    String queryThemLoaiPhong = "INSERT INTO LOAIPHONG (MALOAIPHONG,TENLOAIPHONG,GIATIEN,SOGIUONG) VALUES (20, 'Phong1', 200000, 2)";
    String querySuaLoaiPhong = "UPDATE LOAIPHONG SET TENLOAIPHONG = 'Phong2' WHERE MALOAIPHONG = 20";
    String queryGiaTien = "SELECT GIATIEN FROM LOAIPHONG WHERE MALOAIPHONG = 20";

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {

    }

    @Before
    public void setUp() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url1 = "jdbc:sqlserver://localhost:1433;Instance=SQLEXPRESS;databaseName=KhachSan;integratedSecurity=true";
            con = DriverManager.getConnection(url1);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex.getMessage());
            con = null;
        }
    }

    @After
    public void tearDown() throws SQLException {
        if (con != null) {
            con.close();
        }
    }

    /**
     * Test of getConnectionUrl method, of class ConnectingDB.
     */
    @Test()
    public void testqueryShowCustomers() {
        try {
            // Get the contents of table from DB
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM LOAIPHONG");
            ResultSet res = stmt.executeQuery();

            long row = 0;
            // Print the all result
            while (res.next()) {
                row++;
            }
            assertEquals(row, 10);
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    @Test()
    public void testThemPhong() throws SQLException {
        LapDanhMucPhongController control = new LapDanhMucPhongController();
        int ma = 23;
        float gia = 120000;
        String ten = "Phong";
        Boolean result = control.luuThemLoaiPhong(ma, ten, gia, 2);
        assertTrue(result);

        String query = "SELECT * from LOAIPHONG WHERE MALOAIPHONG = 23";
        PreparedStatement stmt = con.prepareStatement(query);
        ResultSet res = stmt.executeQuery();
        
         LoaiPhong p = new LoaiPhong();

        while(res.next())
        {
                 p.setMaLoaiPhong(res.getInt(1));
                 p.setTenLoaiPhong(res.getString(2));
                 p.setGia(res.getFloat(3));
                 p.setSoGiuongNgu(res.getInt(4));
        }
        
        assertEquals(p.getMaLoaiPhong(), 22);
    }
}

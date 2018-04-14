/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelmanagamentapp;

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
public class FrmPhongTest {
    
    public FrmPhongTest() {
    }
    private static Connection con;
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

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test()
    public void testLayMaLoaiPhong() throws SQLException{
        FrmPhongController control = new FrmPhongController();
        String tenLoaiPhong = "Phòng Loại 3_*";
        int result = control.layMaLoaiPhong(tenLoaiPhong);
        assertEquals(result, 2);
    }
    @Test()
    public void testThemPhong() throws SQLException {
        FrmPhongController control = new FrmPhongController();
        int maPhong = 12;
        String tenPhong = "C202";
        String loaiPhong = "Phòng Loại 3_*";
        String tinhTrangPhong = "Đã Đặt";
        Boolean result = control.luuThemPhong(maPhong, tenPhong, loaiPhong, tinhTrangPhong);
        assertTrue(result);

        String query = "SELECT * from PHONG WHERE MAPHONG = 12";
        PreparedStatement stmt = con.prepareStatement(query);
        ResultSet res = stmt.executeQuery();
        
        Phong p = new Phong();

        while (res.next()) {
            p.setMaPhong(res.getInt(1));
        }      
        assertEquals(p.getMaPhong(), 12);
    }
    
     @Test()
    public void testSuaPhong() throws SQLException {
        FrmPhongController control = new FrmPhongController();
        int maPhong = 10;
        String tenPhong = "A302";
        String loaiPhong = "Phòng Loại 3_**";
        String tinhTrangPhong = "Đã Đặt";
        Boolean result = control.luuSuaPhong(maPhong, tenPhong, loaiPhong, tinhTrangPhong);
        assertTrue(result);

        String query = "SELECT * from PHONG WHERE MAPHONG = 10";
        PreparedStatement stmt = con.prepareStatement(query);
        ResultSet res = stmt.executeQuery();
        
        Phong p = new Phong();

         while (res.next()) {
             p.setMaPhong(res.getInt(1));
             p.setTenPhong(res.getString(2));
             p.setTenLoaiPhong(res.getString(3));
             p.setTinhTrangPhong(res.getString(4));
         }
        String kiemtraTenPhong = "A302";
        assertEquals(p.getTenPhong(), kiemtraTenPhong);
    }
    
    @Test()
    public void testXoaPhong() throws SQLException {
        FrmPhongController control = new FrmPhongController();
        int ma = 12;       
        Boolean result = control.luuXoaPhong(ma);
        assertTrue(result);

        String query = "SELECT * from PHONG WHERE MAPHONG = 12";
        PreparedStatement stmt = con.prepareStatement(query);
        ResultSet res = stmt.executeQuery();
    
        Phong p = new Phong();

        while (res.next()) {
            p.setMaPhong(res.getInt(1));    
        }      
        assertEquals(p.getMaPhong(), 0);
    }
}

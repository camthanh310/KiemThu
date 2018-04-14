/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelmanagamentapp;

import java.net.URL;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
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
public class LapDanhMucPhongControllerTest {
    
    public LapDanhMucPhongControllerTest() {
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

    /**
     * Test of initialize method, of class LapDanhMucPhongController.
     */
    @Test()
    public void testThemLoaiPhong() throws SQLException {
        LapDanhMucPhongController control = new LapDanhMucPhongController();
        int ma = 25;
        float gia = 120000;
        String ten = "Phòng Loại 3_*";
        Boolean result = control.luuThemLoaiPhong(ma, ten, gia, 2);
        assertTrue(result);

        String query = "SELECT * from LOAIPHONG WHERE MALOAIPHONG = 24";
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
        
        assertEquals(p.getMaLoaiPhong(), 26);
    }
    @Test()
    public void testSuaLoaiPhong() throws SQLException {
        LapDanhMucPhongController control = new LapDanhMucPhongController();
        int ma = 2;
        float gia = 120000;
        String ten = "Phòng Loại 3_*";
        Boolean result = control.luuSuaLoaiPhong(ma, ten, gia, 2);
        assertTrue(result);

        String query = "SELECT * from LOAIPHONG WHERE MALOAIPHONG = 2";
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
        String tenLoaiPhong = "Phòng Loại 3_*";
        assertEquals(p.getTenLoaiPhong(), tenLoaiPhong);
    }
    
    @Test()
    public void testXoaLoaiPhong() throws SQLException {
        LapDanhMucPhongController control = new LapDanhMucPhongController();
        int ma = 23;       
        Boolean result = control.luuXoaLoaiPhong(ma);
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
        assertEquals(p.getMaLoaiPhong(), 0);
    }
}

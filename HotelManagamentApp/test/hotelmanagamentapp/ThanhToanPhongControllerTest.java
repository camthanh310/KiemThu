/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelmanagamentapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import DAO.*;
import java.time.LocalDate;

/**
 *
 * @author TTC
 */
public class ThanhToanPhongControllerTest {
    public ThanhToanPhongControllerTest() {
        
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
    
    @Test()
    public void testThanhToanPhong() throws SQLException {
        frmThanhToanPhongController controller = new frmThanhToanPhongController();
        String item = "";
        int result = controller.testThanhToanPhong(item);
        assertEquals(result, 0);
    }
    
    @Test()
    public void testTinhKhoangCachHaiNgay() {
        LocalDate ldNgayTra = ConvertorDAO.LOCAL_DATE("15-04-2018")
                 , ldNgayDat = ConvertorDAO.LOCAL_DATE("13-04-2018");
        long soNgay = HoTroDAO.tinhKhoangCachHaiNgay(ldNgayDat, ldNgayTra);
        assertEquals(soNgay, 2);
    }
    
    @Test()
    public void testLayThongTinThanhToan() {
        frmThanhToanPhongController controller = new frmThanhToanPhongController();
        String strSoPhong = "A101";
        int n = controller.testLayThongTinThanhToan(strSoPhong);
        assertEquals(n, 1);
    }
}

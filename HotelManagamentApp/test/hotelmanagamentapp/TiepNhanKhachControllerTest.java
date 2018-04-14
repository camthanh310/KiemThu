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
import DTO.DatPhongDTO;
import DTO.HoaDonDTO;
import DTO.KhachHangDTO;
import DTO.PhongDTO;
/**
 *
 * @author TTC
 */
public class TiepNhanKhachControllerTest {
    public TiepNhanKhachControllerTest() {
        
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
    public void testLayDonGiaPhong() throws SQLException {
        String strTenLoaiPhong = "Phòng Loại 3_*";
        int dResult = (int)LoaiPhongDAO.layDonGiaLoaiPhong(strTenLoaiPhong);
        assertEquals(dResult, 120000);
    }
    
//    @Test()
//    public void testDatPhong() throws SQLException {
//        int iMaKH = 22; // môi lần test +1 mã khách hàng
//        KhachHangDTO khachhang = new KhachHangDTO();
//        khachhang.setiMaKhachHang(iMaKH); 
//        khachhang.setStrHoTen("test customer");
//        khachhang.setStrDiaChi("test address");
//        khachhang.setStrNgaySinh("2018-01-01");
//        khachhang.setStrSoCMND("a");
//        khachhang.setStrSoDienThoai("111");
//
//        HoaDonDTO hoadon = new HoaDonDTO();
//        int iMa_HD = 22; // môi lần test +1 mã hóa đơn
//        hoadon.setMaHoaDon(iMa_HD);
//        hoadon.setMaDatPhong(iMa_HD);
//        hoadon.setNgayThanhToan("2018-01-03");
//        hoadon.setTongTien(100000);
//        
//        DatPhongDTO phieudat = new DatPhongDTO();
//        int iMaDatPhong = 22; // mỗi lần test +1 iMaDatPhong
//        int iMaPhong = 4;
//        phieudat.setMaDatPhong(iMaDatPhong);
//        phieudat.setMaKhachHang(iMaKH);
//        phieudat.setNgayDatPhong("2018-01-01");
//        phieudat.setNgayTraPhong("2018-01-03");
//        phieudat.setMaPhong(iMaPhong);
//        
//        PhongDTO p = new PhongDTO();
//        p.setMaPhong(iMaPhong);
//        p.setSoPhong("A101");
//        
//        boolean dResult = HoTroDAO.datPhongForTesting(hoadon, khachhang, p, phieudat);
//        assertEquals(dResult, true);
//    }
    
    @Test()
    public void testKiemTraNhapSo() {
        frmTiepNhanKhachController controller = new frmTiepNhanKhachController();
        boolean result = controller.kiemTraLaSo("3");
        assertEquals(result, true);
    }
    
    @Test()
    public void testLayDanhSachSoPhong() {
        frmTiepNhanKhachController controller = new frmTiepNhanKhachController();
        boolean result = controller.testLayDanhSachSoPhong("3");
        assertEquals(result, false);
    }
    
    @Test()
    public void testDatPhong() throws SQLException{
        HoTroDAO control = new HoTroDAO();
        KhachHangDTO khachhang = new KhachHangDTO();
        khachhang.setiMaKhachHang(22);
        khachhang.setStrHoTen("Nguyen Van A");
        khachhang.setStrDiaChi("371 Nguyen Kiem");
        khachhang.setStrNgaySinh("1980-01-01");
        khachhang.setStrSoCMND("9223456789");
        khachhang.setStrSoDienThoai("123456789");

        HoaDonDTO hoadon = new HoaDonDTO();
        hoadon.setMaHoaDon(22);
        hoadon.setMaDatPhong(22);
       
        hoadon.setNgayThanhToan("2018-04-14");
        hoadon.setTongTien(2000000);
        
        DatPhongDTO phieudat = new DatPhongDTO();
        
        phieudat.setMaDatPhong(22);
        phieudat.setMaKhachHang(22);
        phieudat.setNgayDatPhong("2018-04-13");
        phieudat.setNgayTraPhong("2018-04-14");
        phieudat.setMaPhong(5);
        
        PhongDTO p = new PhongDTO();
        p.setMaPhong(5);
        Boolean result = control.datPhongTest(hoadon, khachhang, p, phieudat);
        
        assertTrue(result);
    }
}

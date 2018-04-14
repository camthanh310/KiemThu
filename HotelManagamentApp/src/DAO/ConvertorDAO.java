/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author TTC
 */
public class ConvertorDAO {
    public static final LocalDate LOCAL_DATE (String dateString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        return localDate;
    }
    
    public static final String ngayThang_Dto2DB(LocalDate date){
        DateTimeFormatter formatterUI = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return formatterUI.format(date);
    }
    
    public static final String ngayThang_DB2Dto(String strNgayThang) {
        DateTimeFormatter formatterUI = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDateTemp = LocalDate.parse(strNgayThang, formatterUI);
        formatterUI = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String a= formatterUI.format(localDateTemp);
        return a;
    }
    
    public static final String ngaySinh_Dto2DB(LocalDate date){
        DateTimeFormatter formatterUI = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return formatterUI.format(date);
    }
    
    public static final String currency_DB2Dto(double money) {
        String strMoney = String.format("%,.2f", money);
        if (strMoney.endsWith(".00")) {
            int centsIndex = strMoney.lastIndexOf(".00");
            if (centsIndex != -1) {
                strMoney = strMoney.substring(1, centsIndex);
            }
        }
          return strMoney;
//        NumberFormat formatter = NumberFormat.getCurrencyInstance();
//        String moneyString = formatter.format(money);
//        return moneyString;
//        float epsilon = 0.004f; // 4 tenths of a cent
//        if (Math.abs(Math.round(money) - money) < epsilon) {
//           return String.format("%10.0f", money); // sdb
//        } else {
//           return String.format("%10.2f", money); // dj_segfault
//        }
    }
}

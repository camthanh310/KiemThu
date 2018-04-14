/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validation;

import javafx.scene.control.Label;

/**
 *
 * @author Hau
 */
public class KiemTraGiaTriTrong {
    public static boolean kiemTraGiaTriTrongTextField(String tF){
        boolean bl = false;
        if(tF.length() != 0 || !tF.isEmpty()){
            bl = true;
        }
        return bl;
    }
    public static boolean kiemTraloiGiaTriTrongTextField(String tF, Label lB, String error){
        boolean bl = true;
        String msg = null;
        if(!kiemTraGiaTriTrongTextField(tF)){
            bl = false;
            msg = error;
        }
        lB.setText(msg);
        
        return bl;
    } 
}

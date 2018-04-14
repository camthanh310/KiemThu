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
public class KiemTraKyTuDacBiet {
    public static boolean kiemTraKyTuDacBiet(String tf){
        if(tf.matches("^[A-Za-z0-9]+")) 
           return false;
        return true;
    }
    
    public static boolean KiemTraKyTuDacBietTextField(String tF, Label lB, String error){
        boolean bl = true;
        String msg = null;
        if(kiemTraKyTuDacBiet(tF)){
            bl = false;
            msg = error;
        }
        lB.setText(msg);
        
        return bl;
    } 
}

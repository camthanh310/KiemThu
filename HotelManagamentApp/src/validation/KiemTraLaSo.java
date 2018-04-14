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
public class KiemTraLaSo {
    public static boolean kiemTraLaSo(String tf){
        boolean bL = false;
        if(tf.matches("([0-9]+(\\.[0-9]+)?)+"))
           bL = true;
        return bL;
    }
    
    public static boolean kiemTraLoiGiaTriLaSoTextField(String tF, Label lB, String error){
        boolean bl = true;
        String msg = null;
        if(!kiemTraLaSo(tF)){
            bl = false;
            msg = error;
        }
        lB.setText(msg);
        
        return bl;
    }
}

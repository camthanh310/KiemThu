/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validation;

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
}

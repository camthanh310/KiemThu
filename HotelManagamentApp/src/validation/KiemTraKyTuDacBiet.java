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
public class KiemTraKyTuDacBiet {
    public static boolean kiemTraKyTuDacBiet(String tf){
        if(tf.matches("^[A-Za-z0-9]+")) 
           return false;
        return true;
    }
}

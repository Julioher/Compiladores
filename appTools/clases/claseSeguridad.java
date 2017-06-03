
package clases;
import javax.swing.*;

public class claseSeguridad {
    public String EncriptarCadena(String cadena){
        try{
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(cadena.getBytes());
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i<array.length; ++i){
                sb.append(Integer.toHexString(array[i] & 0xFF | 0X100).substring(1,3));               
            }
            return sb.toString();
        }catch(java.security.NoSuchAlgorithmException e){
            JOptionPane.showMessageDialog(null, "Error "+e.getMessage());
        }
        return null;
    }
}

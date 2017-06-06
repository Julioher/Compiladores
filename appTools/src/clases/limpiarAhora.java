
package clases;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ComboBoxModel;

public class limpiarAhora {
    
    public void limpiarCampos(JPanel jPanel1){
        
        for(int i=0; jPanel1.getComponents().length>i; i++){
            
            if(jPanel1.getComponents()[i]instanceof JPasswordField){
                
                ((JPasswordField)jPanel1.getComponents()[i]).setText("");
                
            }
            else if(jPanel1.getComponents()[i]instanceof JTextField){
                
                ((JTextField)jPanel1.getComponents()[i]).setText("");
                
            }else if(jPanel1.getComponents()[i]instanceof JTextArea){
                
                ((JTextArea)jPanel1.getComponents()[i]).setText("");
                
            }
            
           
        }
    }
}


package clases;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class claseConsultar extends DefaultTableModel{
    public boolean isCellEditable(int row, int col){
        return false;
    }
    
    public void generarModelo(JTable laTabla, String consulta){
        try{
            claseConexion  claseConexion = new claseConexion();
            claseConexion.conectar();
            Statement elComando = claseConexion.objetoConexion.createStatement();
            ResultSet objResultado = elComando.executeQuery(consulta);
            this.addColumn("ID");
            this.addColumn("Primer Nombre");
            this.addColumn("Segundo Nombre");
            this.addColumn("Primer Apellido");
            this.addColumn("Segundo Apellido");
            this.addColumn("DUI");
            this.addColumn("NIT");
            this.addColumn("Direccion");
            this.addColumn("Telefono");
            this.addColumn("Estado");
            this.addColumn("fecha");
            
            while(objResultado.next()){
                Object[] objFila = new Object[11];
                objFila[0] = objResultado.getObject("idEmpleado").toString();
                objFila[1] = objResultado.getObject("pNombre").toString();
                objFila[2] = objResultado.getObject("sNombre").toString();
                objFila[3] = objResultado.getObject("pApellido").toString();
                objFila[4] = objResultado.getObject("sApellido").toString();
                objFila[5] = objResultado.getObject("dui").toString();
                objFila[6] = objResultado.getObject("nit").toString();
                objFila[7] = objResultado.getObject("direccion").toString();
                objFila[8] = objResultado.getObject("telefono").toString();
                objFila[9] = objResultado.getObject("estado").toString();
                objFila[10] = objResultado.getObject("fecha").toString();
                this.addRow(objFila);
            }
            laTabla.setModel(this);
            objResultado.close();
            claseConexion.desconectar();
        }catch(SQLException elError){
            JOptionPane.showMessageDialog(null, "Error: "+ elError.getMessage());
        }
    }
}


package clases;
import java.sql.*;
import javax.swing.*;

public class claseEditarEmpleado {
    
   
    public String pNombre;
    public String sNombre;
    public String pApellido;
    public String sApellido;
    public String dui;
    public String nit;
    public String direccion;
    public String telefono;
    public String fecha;
    public int estado;
    public int idEmpleado;
     
    public void cargarDatos(){
        try{
            claseConexion laConexion = new claseConexion();
            laConexion.conectar();
            String comando = "SELECT * FROM empleados WHERE idEmpleado=?";
            PreparedStatement elComando;
            elComando = laConexion.objetoConexion.prepareStatement(comando);
            elComando.setInt(1, this.idEmpleado);
            ResultSet resultado = elComando.executeQuery();
            if(resultado.next()){
                this.pNombre = resultado.getString("pNombre");
                this.sNombre = resultado.getString("sNombre");
                this.pApellido = resultado.getString("pApellido");
                this.sApellido = resultado.getString("sApellido");
                this.dui =  resultado.getString("dui");
                this.nit =  resultado.getString("nit");
                this.direccion =  resultado.getString("direccion");
                this.telefono =  resultado.getString("telefono");
                //this.estado =  resultado.getInt("estado");
                this.fecha =  resultado.getString("fecha");
                resultado.close();
            }
            laConexion.desconectar();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error"+ e.getMessage());
        }
    }
    
    public void modificarEmpleado(){
        try{
            claseConexion laConexion = new claseConexion();
            laConexion.conectar();
            String comando = "UPDATE empleados SET pNombre=?, sNombre=?,"
                    + "pApellido=?, sApellido=?, dui=?, nit=?, direccion=?,"
                    + "telefono=?, estado=?, fecha=? WHERE idEmpleado=?";
            PreparedStatement elComando;
            elComando = laConexion.objetoConexion.prepareStatement(comando);
            
            elComando.setString(1, this.pNombre);
            elComando.setString(2, this.sNombre);
            elComando.setString(3, this.pApellido);
            elComando.setString(4,this.sApellido);
            elComando.setString(5, this.dui);
            elComando.setString(6, this.nit);
            elComando.setString(7, this.direccion);
            elComando.setString(8, this.telefono);
            elComando.setInt(9,this.estado);
            elComando.setString(10, this.fecha);
            elComando.setInt(11, this.idEmpleado);
            elComando.executeUpdate();
            laConexion.desconectar();
        }catch(Exception e){
             JOptionPane.showMessageDialog(null, "Error"+ e.getMessage());
        }
    }
    
    public Boolean eliminarEmpleado(){
        try{
            claseConexion laConexion = new claseConexion();
            laConexion.conectar();
            String comando = "DELETE FROM empleados WHERE idEmpleado=?";
            PreparedStatement elComando;
            elComando = laConexion.objetoConexion.prepareStatement(comando);
            elComando.setInt(1, this.idEmpleado);
            elComando.executeUpdate();
            laConexion.desconectar();
            return true;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error"+ e.getMessage());
            return false;
        }
    }
    
}

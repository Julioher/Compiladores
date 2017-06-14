
package clases;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class claseCargos  extends DefaultTableModel{
    
     private final claseConexion laConexion = new claseConexion();
     public int idCargo;
     public String cargo;
     public String  fecha;
     public Boolean estadoComando = false;

   
       public claseCargos(int idCargo, String cargo, String fecha){
         this.idCargo = idCargo;
         this.cargo = cargo;
         this.fecha = fecha;
     }
       
         public claseCargos(){
       
     }
    
  
     
    public int getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(int idCargo) {
        this.idCargo = idCargo;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
     
     //Método para guardar registros en la tabla "cargos"
    public void agregarCargo(){
        try{
             String comandoSQL = "INSERT INTO cargos"
            + "(cargo, fecha) VALUES (?,?)";
             laConexion.conectar();
            PreparedStatement elComandoSQL;
            elComandoSQL = laConexion.objetoConexion.prepareStatement(comandoSQL);
            elComandoSQL.setString(1, cargo);
            elComandoSQL.setString(2, fecha);
            elComandoSQL.executeUpdate();
             this.estadoComando = true;
             laConexion.desconectar();
        }catch(SQLException elError){
            JOptionPane.showMessageDialog(null,  "Error SQL "+ elError.getMessage());
            this.estadoComando = false;
        }
    }
    
    public boolean isCellEditable(int row, int col){
        return false;
    }
    
    //método para llenar el JTable
   public void generarModelo(JTable laTabla, String consulta){
        try{
            claseConexion  claseConexion = new claseConexion();
            claseConexion.conectar();
            Statement elComando = claseConexion.objetoConexion.createStatement();
            ResultSet objResultado = elComando.executeQuery(consulta);
            this.addColumn("ID");
            this.addColumn("Cargo");
            this.addColumn("Fecha");      
            while(objResultado.next()){
                Object[] objFila = new Object[3];
                objFila[0] = objResultado.getObject("idCargo").toString();
                objFila[1] = objResultado.getObject("cargo").toString();
                objFila[2] = objResultado.getObject("fecha").toString();
                this.addRow(objFila);
            }
            laTabla.setModel(this);
            objResultado.close();
            claseConexion.desconectar();
        }catch(SQLException elError){
            JOptionPane.showMessageDialog(null, "Error: "+ elError.getMessage());
        }
    }
   
    //Método para modificar cualquier campo de la tabla cargos
    public void modificarCargo(){
         try{
             claseConexion laConexion = new claseConexion();
             laConexion.conectar();
            String comando = "UPDATE cargos SET cargo=?, fecha=?"
                 +" WHERE idCargo=?";
            PreparedStatement elComando;
            elComando = laConexion.objetoConexion.prepareStatement(comando);
            elComando.setString(1, this.cargo);
            elComando.setString(2, this.fecha);
            elComando.setInt(3, this.idCargo);
            elComando.executeUpdate();
            laConexion.desconectar();  
         }catch(Exception e){
             JOptionPane.showMessageDialog(null, "Error: "+ e.getMessage());
         }
    }
    
    //Método para eliminar cualquier registro de la tabla herramientas
    public Boolean eliminarCargo(){
        try{
            claseConexion laConexion = new claseConexion();
            laConexion.conectar();
            String comando = "DELETE FROM cargos WHERE idCargo=?";
            PreparedStatement elComando;
            elComando = laConexion.objetoConexion.prepareStatement(comando);
            elComando.setInt(1, this.idCargo);
            elComando.executeUpdate();
            laConexion.desconectar(); 
            return true;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error: "+ e.getMessage());
            return false;
        }
    }
    
    
    //Método que mostrará en el comboBox los el campo cargos de la tabla cargos
    
     Statement st;
    ResultSet rs;
    
     public void mostrarCargos(JComboBox<claseCargos> cmbCargo){
       
        try{
            claseConexion  claseConexion = new claseConexion();
            claseConexion.conectar();
            st = claseConexion.objetoConexion.createStatement();
            String consulta = "SELECT * FROM cargos";
            rs = st.executeQuery(consulta);
            while(rs.next()){
                cmbCargo.addItem(
                      
                       new claseCargos(
                               rs.getInt("idCargo"),
                               rs.getObject("Cargo").toString(),
                                rs.getObject("fecha").toString()
                       )
                        
                );
            }
        }catch(Exception ex){
           Logger.getLogger(claseCargos.class.getName()).log(Level.SEVERE, null, ex);
           
        }
       
   }
     
   public String toString(){
   
       return cargo;
   }
    
   
    
    
}

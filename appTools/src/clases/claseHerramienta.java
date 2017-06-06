
package clases;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class claseHerramienta extends DefaultTableModel{
    
    private final claseConexion laConexion = new claseConexion();
    public int idHerramienta;
    public int stock;
    public String herramienta;
    public String descripcion;
    public String fecha;
    public Boolean estadoComando = false;

    public int getIdHerramienta() {
        return idHerramienta;
    }

    public void setIdHerramienta(int idHerramienta) {
        this.idHerramienta = idHerramienta;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getHerramienta() {
        return herramienta;
    }

    public void setHerramienta(String herramienta) {
        this.herramienta = herramienta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    //Método para guardar registros en la base de datos
    public void agregarHerramienta(){
        try{
             String comandoSQL = "INSERT INTO herramientas"
            + "(herramienta, descripcion, stock, fecha) VALUES"
            + "(?,?,?,?)";
             laConexion.conectar();
            PreparedStatement elComandoSQL;
            elComandoSQL = laConexion.objetoConexion.prepareStatement(comandoSQL);
            elComandoSQL.setString(1, herramienta);
            elComandoSQL.setString(2, descripcion);
            elComandoSQL.setInt(3, stock);
            elComandoSQL.setString(4, fecha);
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
            this.addColumn("Herramienta");
            this.addColumn("Descripción");
            this.addColumn("Stock");
            this.addColumn("fecha");
            
            while(objResultado.next()){
                Object[] objFila = new Object[5];
                objFila[0] = objResultado.getObject("idHerramienta").toString();
                objFila[1] = objResultado.getObject("herramienta").toString();
                objFila[2] = objResultado.getObject("descripcion").toString();
                objFila[3] = objResultado.getObject("stock").toString();
                objFila[4] = objResultado.getObject("fecha").toString();
                this.addRow(objFila);
            }
            laTabla.setModel(this);
            objResultado.close();
            claseConexion.desconectar();
        }catch(SQLException elError){
            JOptionPane.showMessageDialog(null, "Error: "+ elError.getMessage());
        }
    }
    
   //Método para cargar los datos en las cajas de texto
    /*public void cargarDatos(){
        try{
            claseConexion laConexion = new claseConexion();
            laConexion.conectar();
            String comando = "SELECT * FROM herramientas WHERE idHerramienta=?";
            PreparedStatement elComando;
            elComando = laConexion.objetoConexion.prepareStatement(comando);
            elComando.setInt(1, this.idHerramienta);
            ResultSet resultado = elComando.executeQuery();
            
            if(resultado.next()){
                this.herramienta = resultado.getString("herramienta");
                this.descripcion = resultado.getString("descripcion");
                this.stock = resultado.getInt("stock");
                this.fecha = resultado.getString("fecha");
                resultado.close();
            }
            laConexion.desconectar();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error"+ e.getMessage());
        }
    }*/
    
    //Método para modificar cualquier campo de la tabla herramientas
    public void modificarHerramienta(){
         try{
             claseConexion laConexion = new claseConexion();
             laConexion.conectar();
            String comando = "UPDATE herramientas SET herramienta=?, descripcion=?,"
                 +"stock=?, fecha=? WHERE idHerramienta=?";
            PreparedStatement elComando;
            elComando = laConexion.objetoConexion.prepareStatement(comando);
            
            elComando.setString(1, this.herramienta);
            elComando.setString(2, this.descripcion);
            elComando.setInt(3, this.stock);
            elComando.setString(4, this.fecha);
            elComando.setInt(5, this.idHerramienta);
            
            elComando.executeUpdate();
            laConexion.desconectar();  
         }catch(Exception e){
             JOptionPane.showMessageDialog(null, "Error: "+ e.getMessage());
         }
    }
    
    //Método para eliminar cualquier registro de la tabla herramientas
    public Boolean eliminarHerramienta(){
        try{
            claseConexion laConexion = new claseConexion();
            laConexion.conectar();
            String comando = "DELETE FROM herramientas WHERE idHerramienta=?";
            PreparedStatement elComando;
            elComando = laConexion.objetoConexion.prepareStatement(comando);
            elComando.setInt(1, this.idHerramienta);
            elComando.executeUpdate();
            laConexion.desconectar(); 
            return true;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error: "+ e.getMessage());
            return false;
        }
    }
    
}

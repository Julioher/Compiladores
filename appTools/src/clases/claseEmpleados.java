
package clases;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class claseEmpleados extends DefaultTableModel{
    
    private final claseConexion laConexion = new claseConexion();
    public int idEmpleado;
    public int estado;
    public String pNombre;
    public String sNombre;
    public String pApellido;
    public String sApellido;
    public String dui;
    public String nit;
    public String direccion;
    public String telefono;
    public String fecha;
    public Boolean estadoComando = false;
    
    
    public claseEmpleados(int idEmpleado, String pNombre, String sNombre){
     
        this.idEmpleado = idEmpleado;
        this.estado = estado;
        this.pNombre = pNombre;
        this.sNombre = sNombre;
        this.pApellido = pApellido;
        this.sApellido = sApellido;
        this.dui = dui;
        this.nit = nit;
        this.direccion = direccion;
        this.telefono = telefono;
        this.fecha = fecha;
    }
    
    public claseEmpleados(){
    
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getpNombre() {
        return pNombre;
    }

    public void setpNombre(String pNombre) {
        this.pNombre = pNombre;
    }

    public String getsNombre() {
        return sNombre;
    }

    public void setsNombre(String sNombre) {
        this.sNombre = sNombre;
    }

    public String getpApellido() {
        return pApellido;
    }

    public void setpApellido(String pApellido) {
        this.pApellido = pApellido;
    }

    public String getsApellido() {
        return sApellido;
    }

    public void setsApellido(String sApellido) {
        this.sApellido = sApellido;
    }

    public String getDui() {
        return dui;
    }

    public void setDui(String dui) {
        this.dui = dui;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    //Método para agregar un registro en la base de datos
    public void agregarEmpleado(){
        try{
            String comandoSQL = "INSERT INTO empleados"
            + "(pNombre, sNombre, pApellido, sApellido, dui, nit,"
                    + "direccion, telefono, estado, fecha) VALUES"
            + "(?,?,?,?,?,?,?,?,?,?)";
            laConexion.conectar();
            PreparedStatement elComandoSQL;
            elComandoSQL = laConexion.objetoConexion.prepareStatement(comandoSQL);
            elComandoSQL.setString(1, pNombre);
            elComandoSQL.setString(2, sNombre);
            elComandoSQL.setString(3, pApellido);
            elComandoSQL.setString(4, sApellido);
            elComandoSQL.setString(5, dui);
            elComandoSQL.setString(6, nit);
            elComandoSQL.setString(7, direccion);
            elComandoSQL.setString(8, telefono);
           elComandoSQL.setInt(9, estado);
            elComandoSQL.setString(10, fecha);
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
    
    public String nombreEstado(int i){
        String estado="";
        switch (i) {
            case 1:
                estado = "Activo";
                break;
            case 2:
                estado = "Inactivo";
                break;
            default:
                estado = "";
                break;
        }
        return estado;
    }
    
    //método para llenar el JTable
 public void generarModelo(JTable laTabla, String consulta){
        try{
            claseConexion  claseConexion = new claseConexion();
            claseConexion.conectar();
            Statement elComando = claseConexion.objetoConexion.createStatement();
            ResultSet objResultado = elComando.executeQuery(consulta);
            this.addColumn("ID");
            this.addColumn("P. Nombre");
            this.addColumn("S. Nombre");
            this.addColumn("P. Apellido");
            this.addColumn("S. APellido");
            this.addColumn("DUI");
            this.addColumn("NIT");
            this.addColumn("Dirección");
            this.addColumn("Teléfono");
            this.addColumn("Estado");
            this.addColumn("Fecha");
            
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
                objFila[9] = this.nombreEstado(objResultado.getInt("estado"));
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
    
 
     
 
    //Método para modificar cualquier campo de la tabla empleados
    public void modificarEmpleados(){
         try{
             claseConexion laConexion = new claseConexion();
             laConexion.conectar();
            String comando = "UPDATE empleados SET pNombre=?, sNombre=?,"
                 +"pApellido=?, sApellido=?, dui=?, nit=?, direccion=?,"
                    + "telefono=?, estado=?, fecha=?  WHERE idEmpleado=?";
            PreparedStatement elComando;
            elComando = laConexion.objetoConexion.prepareStatement(comando);
            
            elComando.setString(1, this.pNombre);
            elComando.setString(2, this.sNombre);
            elComando.setString(3, this.pApellido);
            elComando.setString(4, this.sApellido);
            elComando.setString(5, this.dui);
            elComando.setString(6, this.nit);
            elComando.setString(7, this.direccion);
            elComando.setString(8, this.telefono);
            elComando.setInt(9, this.estado);
            elComando.setString(10, this.fecha);
            elComando.setInt(11, this.idEmpleado);
            
            elComando.executeUpdate();
            laConexion.desconectar();  
         }catch(Exception e){
             JOptionPane.showMessageDialog(null, "Error: "+ e.getMessage());
         }
    }
    
    //Método para eliminar cualquier registro de la tabla herramientas
    public Boolean eliminarEmpleados(){
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
            JOptionPane.showMessageDialog(null, "Error: "+ e.getMessage());
            return false;
        }
    }
    
    //Método que mostrará en el comboBox el campo pNombre y sNombre de la tabla empleados
    
     Statement st;
    ResultSet rs;
    
     public void mostrarEmpleados(JComboBox<claseEmpleados> cmbEmpleados){
       
        try{
            claseConexion  claseConexion = new claseConexion();
            claseConexion.conectar();
            st = claseConexion.objetoConexion.createStatement();
            String consulta = "SELECT idEmpleado, pNombre, sNombre FROM empleados";
            rs = st.executeQuery(consulta);
            while(rs.next()){
                cmbEmpleados.addItem(
                      
                       new claseEmpleados(
                               rs.getInt("idEmpleado"),
                               rs.getObject("pNombre").toString(),
                                rs.getObject("sNombre").toString()
                       )
                        
                );
            }
        }catch(Exception ex){
           Logger.getLogger(claseCargos.class.getName()).log(Level.SEVERE, null, ex);
           
        }
       
   }
      public String toString(){
   
       return pNombre + " "+sNombre;
   }
}


package clases;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class claseEmpleados extends DefaultTableModel{
    
    private final claseConexion laConexion = new claseConexion();
    public int idEmpleado;
    //public int estado;
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

    /*public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }*/

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
                    + "direccion, telefono, fecha) VALUES"
            + "(?,?,?,?,?,?,?,?,?)";
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
            elComandoSQL.setString(9, fecha);
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
            this.addColumn("P. Nombre");
            this.addColumn("S. Nombre");
            this.addColumn("P. Apellido");
            this.addColumn("S. APellido");
            this.addColumn("DUI");
            this.addColumn("NIT");
            this.addColumn("Dirección");
            this.addColumn("Teléfono");
            this.addColumn("Fecha");
            
            while(objResultado.next()){
                Object[] objFila = new Object[10];
                objFila[0] = objResultado.getObject("idEmpleado").toString();
                objFila[1] = objResultado.getObject("pNombre").toString();
                objFila[2] = objResultado.getObject("sNombre").toString();
                objFila[3] = objResultado.getObject("pApellido").toString();
                objFila[4] = objResultado.getObject("sApellido").toString();
                objFila[5] = objResultado.getObject("dui").toString();
                objFila[6] = objResultado.getObject("nit").toString();
                objFila[7] = objResultado.getObject("direccion").toString();
                objFila[8] = objResultado.getObject("telefono").toString();
                objFila[9] = objResultado.getObject("fecha").toString();
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
                this.dui = resultado.getString("dui");
                this.nit = resultado.getString("nit");
                this.direccion = resultado.getString("direccion");
                this.direccion = resultado.getString("telefono");
              
                this.fecha = resultado.getString("fecha");
                resultado.close();
            }
            laConexion.desconectar();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error"+ e.getMessage());
        }
    }*/
    
    //Método para modificar cualquier campo de la tabla empleados
    public void modificarEmpleados(){
         try{
             claseConexion laConexion = new claseConexion();
             laConexion.conectar();
            String comando = "UPDATE empleados SET pNombre=?, sNombre=?,"
                 +"pApellido=?, sApellido=?, dui=?, nit=?, direccion=?,"
                    + "telefono=?, fecha=?  WHERE idEmpleado=?";
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
            elComando.setString(9, this.fecha);
            elComando.setInt(10, this.idEmpleado);
            
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
}


package clases;
import java.sql.*;
import javax.swing.*;
import formularios.frmNuevoEmpleado;

public class claseEmpleados {
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
    
    public void agregarEmpleado(){
        try{
            String comandoSQL = "INSERT INTO empleados"
            + "(pNombre, sNombre, pApellido, sApellido, dui, nit,"
                    + "direccion, telefono,  fecha) VALUES"
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
            //elComandoSQL.setInt(9, estado);
            elComandoSQL.setString(9, fecha);
             elComandoSQL.executeUpdate();
             this.estadoComando = true;
             laConexion.desconectar();
        }catch(SQLException elError){
            JOptionPane.showMessageDialog(null,  "Error SQL "+ elError.getMessage());
            this.estadoComando = false;
        }
    }
}

package clases;
import java.sql.*;
import javax.swing.*;

public class claseConexion {
    private final String servidor;
    private final String usuario;
    private final String password;
    private final String baseDatos;
    public Connection objetoConexion;
    public Boolean estadoConexion;
    
    public claseConexion(){
        this.servidor = "127.0.0.1:3306";
        this.usuario = "root";
        this.password = "";
        this.baseDatos = "herramientas";
    }
    public void conectar(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            String server;
            server = "jdbc:mysql://" + this.servidor+"/"+ this.baseDatos;            
            objetoConexion = DriverManager.getConnection(server,this.usuario,this.password);
            this.estadoConexion = true;            
        }catch(SQLException elError){
            JOptionPane.showMessageDialog(null, "Error de SQL: " + elError.getMessage());
        } catch (ClassNotFoundException elError) {
            JOptionPane.showMessageDialog(null, "Error de Driver: " + elError.getMessage());
        }
    }
    public void desconectar(){        
        try{
            objetoConexion.close();
            this.estadoConexion = false;          
        }catch(SQLException elError){
            JOptionPane.showMessageDialog(null, "Error de SQL: " + elError.getMessage());
        }
    }
}

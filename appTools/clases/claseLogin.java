
package clases;
import java.sql.*;
import javax.swing.*;


public class claseLogin {
    public Boolean validarUsuario(String usuario, String password){
    Boolean estado;
    try{
        claseConexion laConexion = new claseConexion();
        laConexion.conectar();
        String comando = "SELECT * FROM usuarios WHERE usuario=? AND password=?";
        PreparedStatement elComando;
        elComando = laConexion.objetoConexion.prepareStatement(comando);
         claseSeguridad objSeguridad = new claseSeguridad();
        String elPassword = objSeguridad.EncriptarCadena(password);
        elComando.setString(1, usuario);
        elComando.setString(2, elPassword);
          try (ResultSet resultado = elComando.executeQuery()){
              estado = resultado.next();
          }
          laConexion.desconectar();
    }catch(Exception e){
        JOptionPane.showMessageDialog(null, "Error "+ e.getMessage());
        estado = false;
    }
        return estado;
    }
}

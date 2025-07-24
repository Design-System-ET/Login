package com.design_system.login.SQL;

import com.design_system.login.GUI.LoguinGUI;
import com.design_system.login.GUI.VentanaPrincipal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ValidarLogin {
    
    //establezco coneccion con la BD
    Conectar conn = new Conectar();
    
    //consulta sql
    String sql = "SELECT * FROM usuarios WHERE user = ? AND password = ?";
    
    public boolean validacion(String user, String password){
    
        //try-with-resources para cerrar recursos automaticamente
        try(
            //la conexion se almacena en la variable
            Connection conection = conn.conectar();
            //variable de peticion y seguridad
            //AYUDA A PREVENIR ATAQUES DE INYECCION SQL
            PreparedStatement preparedStatement = conection.prepareStatement(sql);){
         
            //Estableciendo valores
            // 1 y 2 significan las posiciones de los valores de como se
            //definieron en la variable sql con la sentencia del INSERT
            preparedStatement.setString(1, user);
            preparedStatement.setString(2, password);
            
            //ejecuto la consulta
            try(
                ResultSet resultSet = preparedStatement.executeQuery()){
                
                if(resultSet.next()){
                    
                    //llamo a la ventana principal
                    VentanaPrincipal init = new VentanaPrincipal(user);
                    init.setVisible(true);
                    
                    //retorno tru para cerrar ventana login
                    return true;
                    
                }else{
                    
                    JOptionPane.showMessageDialog(null, "Error en los datos ingresados");
                }
            }
             
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error - No se pudo validar los datos, cod: " + e);
        }
        
        return false;
        
    }

}

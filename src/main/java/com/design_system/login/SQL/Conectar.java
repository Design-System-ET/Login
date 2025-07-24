package com.design_system.login.SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conectar implements Resources{

    //implemento url, user y pass desde la interface resources 
    //en vez de definir variables aqui en la clase
    public Connection conectar() {
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASS);
            JOptionPane.showMessageDialog(null, "Conexion exitosa");
            return conn;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error de conexion: " + e.getMessage());
            return null;
        }
    }
}

package modelo;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {

    private Connection c;
    private Statement st;

    public Conexion (){
        try{
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "prueba1");
            st = c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        } catch (Exception e) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    public ResultSet CONSULTAR(String sql) throws SQLException{
        return st.executeQuery(sql);
    }
    public int GUARDAR(String sql) throws SQLException{
        return st.executeUpdate(sql);
    }
    public  void CERRAR(){
        try {
            c.close();
        }catch (SQLException e){
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
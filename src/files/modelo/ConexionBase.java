package files.modelo;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexionBase {
private Connection con =null;
private Statement st;
public ConexionBase(){
    iniciarC();
}

    private void iniciarC(){
        String urlDatabase =  "jdbc:postgresql://localhost:5432/ProyectoDS1";
        try {
            Class.forName("org.postgresql.Driver");
            this.con = DriverManager.getConnection(urlDatabase,  "postgres", "1830628");
            this.st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        } catch (Exception e) {
            System.out.println("Ocurrio un error : "+e.getMessage());
        }

    }
    public Connection getConect() {
        return con;
    }
    public ResultSet consultar(String sql) throws SQLException{
        return st.executeQuery(sql);
    }
    public int guardar(String sql) throws SQLException{
        return st.executeUpdate(sql);
    }
    public  void cerrar(){
        try {
            con.close();
        }catch (SQLException e){
            Logger.getLogger(ConexionBase.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}


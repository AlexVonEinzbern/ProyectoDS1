package sample;
import java.sql.*;

public class ConeccionBase  {
private Connection base=null;
public ConeccionBase(){
    iniciarC();
}

    private void iniciarC(){
        String urlDatabase =  "jdbc:postgresql://localhost:5432/ProyectoDS1";
        try {
            Class.forName("org.postgresql.Driver");
            this.base = DriverManager.getConnection(urlDatabase,  "postgres", "1830628");
            System.out.println("Conexión Exitosa");
        } catch (Exception e) {
            System.out.println("Ocurrio un error : "+e.getMessage());
        }

    }
    public Connection getConect() {
        return base;
    }

    public String consulta(String expresion,String colum) throws SQLException {

        PreparedStatement stmt = this.base.prepareStatement(expresion);
        ResultSet r = stmt.executeQuery();
        String aux = null;
        if (r.next()) {
            aux=  r.getString(colum);
            System.out.println(aux + " este es el resutado");
        }
        r.close();
        stmt.close();
return aux;
    }
    public boolean consultaBool(String expresion,String colum) throws SQLException {

        PreparedStatement stmt = this.base.prepareStatement(expresion);
        ResultSet r = stmt.executeQuery();
        boolean aux = false;
        if (r.next()) {
            aux=  r.getBoolean(colum);
            System.out.println(aux + " este es el resutado");
        }
        r.close();
        stmt.close();
        return aux;
    }
}


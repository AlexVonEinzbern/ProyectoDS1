package sample;
import java.sql.*;

public class ConeccionBase  {
private Connection base=null;
public ConeccionBase(){iniciarC();}

    private void iniciarC(){
        String urlDatabase =  "jdbc:postgresql://localhost:5432/postgres";
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

    public String consulta() throws SQLException {

        PreparedStatement stmt = this.base.prepareStatement("SELECT nombref FROM fabricante where codigof = 1");
        ResultSet r = stmt.executeQuery();
        String aux = "";
        if (r.next()) {
            aux=  r.getString("nombref");
            System.out.println(aux + " este es el resutado");
        }
        r.close();
        stmt.close();
        return aux;
    }

}


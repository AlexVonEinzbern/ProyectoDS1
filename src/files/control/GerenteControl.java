package files.control;

import files.modelo.ConexionBase;

import java.sql.Connection;

public class GerenteControl {
    private ConexionBase con;
    public GerenteControl(ConexionBase con){
        this.con = con;
    }

}

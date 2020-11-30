package files.control.admin;

import files.modelo.ConexionBase;

import java.io.IOException;

public class ConfigControl {
    ConexionBase con;

    public ConfigControl(ConexionBase con) throws IOException {
        this.con = con;
    }
}

package files.modelo;

import java.sql.Date;

public class Usuario {
 private String nombre ;
 private  int cedula;
 private String direccionU;
 private int telefono;
 private Date fecha;
 private String sucursal;
 private String rol;
 private boolean estado;
 private String password;
public Usuario(String nombre, int cedula, String direccionU,
               int telefono, Date fecha, String sucursal, String rol, boolean estado, String password){
    this.cedula =cedula;
    this.direccionU =direccionU;
    this.estado=estado;
    this.fecha=fecha;
    this.nombre=nombre;
    this.password=password;
    this.rol=rol;
    this.sucursal=sucursal;
    this.telefono=telefono;
}

    public String getNombre() {
        return nombre;
    }

    public int getCedula() {
        return cedula;
    }

    public String getDireccionU() {
        return direccionU;
    }

    public int getTelefono() {
        return telefono;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getSucursal() {
        return sucursal;
    }

    public String getRol() {
        return rol;
    }

    public boolean isEstado() {
        return estado;
    }

    public String getPassword() {
        return password;
    }
}

package files.modelo;

import java.sql.Date;

public class Usuario {
 private String nombre ;
 private  int cedula;
 private String direccion;
 private String email;
 private int telefono;
 private Date fecha;
 private String sucursal;
 private String rol;
 private boolean estado;
 private String password;
public Usuario(String nombre, int cedula, String direccionU,
               int telefono, Date fecha, String sucursal, String rol, boolean estado, String password){
    this.cedula =cedula;
    this.direccion =direccionU;
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

    public String getDireccion() {
        return direccion;
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

    public String getEmail() {
        return email;
    }

    public boolean isEstado() {
        return estado;
    }

    public String getPassword() {
        return password;
    }
}

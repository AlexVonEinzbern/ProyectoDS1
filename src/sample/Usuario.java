package sample;

public class Usuario {
 private String nombre ;
 private  int cedula;
 private String direccionU;
 private int telefono;
 private String fecha;
 private String sucursal;
 private String rol;
 private boolean estado;
 private String password;
public Usuario(String nombre,int cedula,String direccionU,
               int telefono,String fecha,String sucursal,String rol,boolean estado,String password){
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

}

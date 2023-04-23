package mx.uv.empleado;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Empleado {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String numeroTrabajador;
    private String nombre;
    private String apellidoP;
    private String apellidoM;
    public String getApellidoM() {
        return apellidoM;
    }
    public void setApellidoM(String apellidoM) {
        this.apellidoM = apellidoM;
    }
    private String telefono;
    public String getNumeroTrabajador() {
        return numeroTrabajador;
    }
    public void setNumeroTrabajador(String numeroTrabajador) {
        this.numeroTrabajador = numeroTrabajador;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellidoP() {
        return apellidoP;
    }
    public void setApellidoP(String apellidoP) {
        this.apellidoP = apellidoP;
    }
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

}

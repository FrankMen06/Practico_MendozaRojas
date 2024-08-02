package sv.edu.udb.www.beans;

import java.sql.Date;
import java.time.format.DateTimeFormatter;

public class Persona {
    private int id;
    private String identificacion;
    private String nombres;
    private String direccion;
    private String email;
    private Date fecha_nac;
    private String telefono;
    private String sexo;


    // Constructor vacio para crear nuevos objetos
    public Persona() {
    }

    // Constructor con valores
    public Persona(int id, String identificacion, String nombres, String direccion, String email, Date fecha_nac, String telefono, String sexo) {
        this.id = id;
        this.identificacion = identificacion;
        this.nombres = nombres;
        this.direccion = direccion;
        this.email = email;
        this.fecha_nac = fecha_nac;
        this.telefono = telefono;
        this.sexo = sexo;
    }


    // getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getFecha_nac() {
        return fecha_nac;
    }

    public void setFecha_nac(Date fecha_nac) {
        this.fecha_nac = fecha_nac;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }


}

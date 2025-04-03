package itson.sistemarestaurantedominio;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.*;

@Entity
@Table(name = "clientesFrecuentes")
public class ClienteFrecuente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idClienteFrecuente")
    private Long id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;
    
    @Column(name = "apellidoPaterno", nullable = false, length = 75)
    private String apellidoPaterno;
    
    @Column(name = "apellidoMaterno", length = 75)
    private String apellidoMaterno;
    
    @Column(name = "telefono", nullable = false, length = 10, unique = true)
    private String telefono;
    
    @Column(name = "correo", nullable = false, unique = true, length = 100)
    private String correo;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "fechaRegistro", nullable = false)
    private Calendar fechaRegistro;
    
    @Column(name = "puntosFidelidad", nullable = false)
    private int puntosFidelidad = 0;
    
    public ClienteFrecuente() {}
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Calendar getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Calendar fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
    
    public int getPuntosFidelidad(){
        return puntosFidelidad;
    }
    
    public void setPuntosFidelidad(int puntosFidelidad){
        this.puntosFidelidad = puntosFidelidad;
    }
    
    @Override
    public int hashCode() {
        return (id != null ? id.hashCode() : 0);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ClienteFrecuente other = (ClienteFrecuente) obj;
        return id != null && id.equals(other.id);
    }

    @Override
    public String toString() {
        return "ClienteFrecuente{id=" + id + ", nombre='" + nombre + "', apellidoPaterno='" + apellidoPaterno + "', apellidoMaterno='" + apellidoMaterno + "', telefono='" + telefono + "', correo='" + correo + "', fechaRegistro=" + fechaRegistro.getTime() + "}";
    }
}
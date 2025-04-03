package itson.sistemarestaurantedominio;

//@author SAUL ISAAC APODACA BALDENEGRO 00000252020

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


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
    
    @Column(name = "correo", nullable = true, unique = true, length = 100)
    private String correo;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "fechaRegistro", nullable = false)
    private Calendar fechaRegistro;
    
    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public Calendar getFechaRegistro() {
        return fechaRegistro;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setFechaRegistro(Calendar fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClienteFrecuente)) {
            return false;
        }
        ClienteFrecuente other = (ClienteFrecuente) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "itson.sistemarestaurantedominio.ClienteFrecuente[ id=" + id + " ]";
    }

}

package itson.sistemarestaurantedominio;

//@author SAUL ISAAC APODACA BALDENEGRO 00000252020

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "comandas")
public class Comanda implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idComanda")
    private Long id;

    @Column(name = "folio", nullable = false, unique = true, length = 15)
    private String folio;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "fechaCreacion", nullable = false)
    private Calendar fechaCreacion;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "estadoComanda", nullable = false)
    private EstadoComanda estadoComanda;
    
    @Column(name = "totalAcumulado", nullable = false)
    private float totalAcumulado;
    
    @ManyToOne
    @JoinColumn(name = "idClienteFrecuente", nullable = true)
    private ClienteFrecuente clienteFrecuente;
    
    @OneToMany(mappedBy = "comanda", cascade = CascadeType.ALL)
    private List<DetalleComanda> detalles;

    public Comanda() {
    }
    
    public Comanda(String folio, Calendar fechaCreacion, EstadoComanda estadoComanda, float totalAcumulado, ClienteFrecuente clienteFrecuente, List<DetalleComanda> detalles) {
        this.folio = folio;
        this.fechaCreacion = fechaCreacion;
        this.estadoComanda = estadoComanda;
        this.totalAcumulado = totalAcumulado;
        this.clienteFrecuente = clienteFrecuente;
        this.detalles = detalles;
    }
    
    public Comanda(String folio, Calendar fechaCreacion, EstadoComanda estadoComanda, float totalAcumulado, List<DetalleComanda> detalles) {
        this.folio = folio;
        this.fechaCreacion = fechaCreacion;
        this.estadoComanda = estadoComanda;
        this.totalAcumulado = totalAcumulado;
        this.detalles = detalles;
    }    
    
    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public Calendar getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Calendar fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public EstadoComanda getEstadoComanda() {
        return estadoComanda;
    }

    public void setEstadoComanda(EstadoComanda estadoComanda) {
        this.estadoComanda = estadoComanda;
    }

    public float getTotalAcumulado() {
        return totalAcumulado;
    }

    public void setTotalAcumulado(float totalAcumulado) {
        this.totalAcumulado = totalAcumulado;
    }

    public ClienteFrecuente getClienteFrecuente() {
        return clienteFrecuente;
    }

    public void setClienteFrecuente(ClienteFrecuente clienteFrecuente) {
        this.clienteFrecuente = clienteFrecuente;
    }

    public List<DetalleComanda> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleComanda> detalles) {
        this.detalles = detalles;
    } 
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof Comanda)) {
            return false;
        }
        Comanda other = (Comanda) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "itson.sistemarestaurantedominio.Comanda[ id=" + id + " ]";
    }

}

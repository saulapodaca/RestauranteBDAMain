package itson.sistemarestaurantedominio;

//@author SAUL ISAAC APODACA BALDENEGRO 00000252020

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "detallesComandas")
public class DetalleComanda implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDetallesComandas")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "folio")
    private Comanda comanda;
    
    @ManyToOne
    @JoinColumn(name = "idProducto")
    private Producto producto;
    
    @Column(name = "cantidad", nullable = false)
    private int cantidad;
    
    @Column(name = "precioUnitario", nullable = false)
    private float precioUnitario;
    
    @Column(name = "importeTotal", nullable = false)
    private float importeTotal;
    
    @Column(name = "nota", nullable = true, length = 255)
    private String nota;

    public DetalleComanda() {
    }

    public DetalleComanda(Comanda comanda, Producto producto, int cantidad, float precioUnitario, float importeTotal, String nota) {
        this.comanda = comanda;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.importeTotal = importeTotal;
        this.nota = nota;
    }

    
    
    public Comanda getComanda() {
        return comanda;
    }

    public void setComanda(Comanda comanda) {
        this.comanda = comanda;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(float precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public float getImporteTotal() {
        return importeTotal;
    }

    public void setImporteTotal(float importeTotal) {
        this.importeTotal = importeTotal;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
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
        if (!(object instanceof DetalleComanda)) {
            return false;
        }
        DetalleComanda other = (DetalleComanda) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "itson.sistemarestaurantedominio.DetalleComanda[ id=" + id + " ]";
    }

}

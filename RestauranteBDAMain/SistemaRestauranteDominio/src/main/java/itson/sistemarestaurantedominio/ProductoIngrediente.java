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
@Table(name = "productosIngredientes")
public class ProductoIngrediente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProductoIngrediente")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idProducto")
    private Producto producto;
    
    @ManyToOne
    @JoinColumn(name = "idIngrediente")
    private Ingrediente ingrediente;
    
    @Column(name = "cantidad", nullable = false)
    
    
    public Long getId() {
        return id;
    }

    public ProductoIngrediente(Long id, Producto producto, Ingrediente ingrediente) {
        this.id = id;
        this.producto = producto;
        this.ingrediente = ingrediente;
    }

    public ProductoIngrediente() {
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Ingrediente getIngrediente() {
        return ingrediente;
    }

    public void setIngrediente(Ingrediente ingrediente) {
        this.ingrediente = ingrediente;
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
        if (!(object instanceof ProductoIngrediente)) {
            return false;
        }
        ProductoIngrediente other = (ProductoIngrediente) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "itson.sistemarestaurantedominio.ProductoIngrediente[ id=" + id + " ]";
    }

}

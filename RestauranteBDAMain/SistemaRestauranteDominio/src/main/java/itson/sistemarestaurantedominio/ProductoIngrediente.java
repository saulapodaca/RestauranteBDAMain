package itson.sistemarestaurantedominio;

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
    @JoinColumn(name = "idProducto", nullable = false)
    private Producto producto;
    
    @ManyToOne
    @JoinColumn(name = "idIngrediente", nullable = false)
    private Ingrediente ingrediente;
    
    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;
    
    public ProductoIngrediente() {
    }
    
    public ProductoIngrediente(Long id, Producto producto, Ingrediente ingrediente, Integer cantidad) {
        this.producto = producto;
        this.ingrediente = ingrediente;
        this.cantidad = cantidad;
    }

    public Long getId() {
        return id;
    }

    public Producto getProducto() {
        return producto;
    }

    public Ingrediente getIngrediente() {
        return ingrediente;
    }
    
    public Integer getCantidad(){
        return cantidad;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public void setIngrediente(Ingrediente ingrediente) {
        this.ingrediente = ingrediente;
    }
    
    public void setCantidad(Integer cantidad){
        this.cantidad = cantidad;
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

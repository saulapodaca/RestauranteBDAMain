package itson.sistemarestaurantepersistencia.implementaciones;

import itson.sistemarestaurantedominio.EstadoMesa;
import itson.sistemarestaurantedominio.Mesa;
import itson.sistemarestaurantedominio.dtos.MesaRegistradaDTO;
import itson.sistemarestaurantedominio.dtos.NuevaMesaDTO;
import itson.sistemarestaurantepersistencia.IMesasDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class MesasDAO implements IMesasDAO {

    @Override
    public Mesa registrar(NuevaMesaDTO nuevaMesa){
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        entityManager.getTransaction().begin();
        
        Mesa mesa = new Mesa();
        mesa.setNumeroMesa(nuevaMesa.getNumeroMesa());
        mesa.setEstadoMesa(EstadoMesa.DISPONIBLE);
        entityManager.persist(mesa);
        entityManager.getTransaction().commit();
        
        return mesa;
    }
    
    @Override
    public Mesa cambiarEstado(MesaRegistradaDTO mesaRegistrada, String estadoNuevo){
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        entityManager.getTransaction().begin();
        Mesa mesaExistente = entityManager
                .find(Mesa.class, mesaRegistrada.getId());
        mesaExistente.setEstadoMesa(EstadoMesa.valueOf(estadoNuevo));
        entityManager.getTransaction().commit();
        return mesaExistente;
    }
    
    @Override
    public MesaRegistradaDTO obtenerMesa(Integer numMesa){
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        String jpql = """
                      SELECT
                      new itson.sistemarestaurantedominio.dtos.MesaRegistradaDTO(
                      m.id, m.numeroMesa, m.estadoMesa)
                      FROM Mesa m 
                      WHERE m.numeroMesa = :numMesa
                      """;
        MesaRegistradaDTO mesa = entityManager.createQuery(jpql, 
                MesaRegistradaDTO.class)
                .setParameter("numMesa", numMesa)
                .getSingleResult();
        return mesa;
    }
    
    @Override
    public boolean existeMesa(Integer numMesa){
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Mesa> mesa = cq.from(Mesa.class);
        cq.select(cb.count(mesa));
        Predicate numMesaPredicate = cb.equal(
                mesa.get("numeroMesa"), numMesa);
        cq.where(numMesaPredicate);

        Long count = entityManager.createQuery(cq).getSingleResult();
        return count > 0;
    }

    @Override
    public void insertarMesasIniciales() {
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        entityManager.getTransaction().begin();
        for (int i = 1; i <= 20; i++) 
            entityManager.persist(new Mesa(i, EstadoMesa.DISPONIBLE));        

        entityManager.getTransaction().commit();
    }
    
    @Override
    public List<MesaRegistradaDTO> obtenerMesas(){
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        String jpql = """
                      SELECT
                      new itson.sistemarestaurantedominio.dtos.MesaRegistradaDTO(
                      m.numeroMesa, m.estadoMesa)
                      FROM Mesa m
                      """;
        return entityManager.createQuery(jpql, 
                MesaRegistradaDTO.class).getResultList();
                
    }
}

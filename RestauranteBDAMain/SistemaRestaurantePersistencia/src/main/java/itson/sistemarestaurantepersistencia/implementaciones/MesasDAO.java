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

/**
 * Implementación de la interfaz IMesasDAO para el manejo de la persistencia de
 * entidades Mesa en la base de datos.
 */
public class MesasDAO implements IMesasDAO {

    /**
     * Registra una nueva mesa con estado disponible por defecto.
     *
     * @param nuevaMesa DTO que contiene el número de la nueva mesa.
     * @return La entidad Mesa persistida.
     */
    @Override
    public Mesa registrar(NuevaMesaDTO nuevaMesa) {
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        entityManager.getTransaction().begin();

        Mesa mesa = new Mesa();
        mesa.setNumeroMesa(nuevaMesa.getNumeroMesa());
        mesa.setEstadoMesa(EstadoMesa.DISPONIBLE);
        entityManager.persist(mesa);
        entityManager.getTransaction().commit();

        return mesa;
    }

    /**
     * Cambia el estado de una mesa existente.
     *
     * @param mesaRegistrada DTO que contiene el ID de la mesa a modificar.
     * @param estadoNuevo Nuevo estado de la mesa.
     * @return La entidad Mesa actualizada.
     */
    @Override
    public Mesa cambiarEstado(MesaRegistradaDTO mesaRegistrada, String estadoNuevo) {
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        entityManager.getTransaction().begin();
        Mesa mesaExistente = entityManager
                .find(Mesa.class, mesaRegistrada.getId());
        mesaExistente.setEstadoMesa(EstadoMesa.valueOf(estadoNuevo));
        entityManager.getTransaction().commit();
        return mesaExistente;
    }

    /**
     * Obtiene una mesa a partir de su número.
     *
     * @param numMesa Número identificador de la mesa.
     * @return Un MesaRegistradaDTO con los datos de la mesa encontrada.
     */
    @Override
    public MesaRegistradaDTO obtenerMesa(Integer numMesa) {
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

    /**
     * Verifica si existe una mesa con el número dado.
     *
     * @param numMesa Número de la mesa a verificar.
     * @return true si existe, false si no.
     */
    @Override
    public boolean existeMesa(Integer numMesa) {
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

    /**
     * Inserta automáticamente 20 mesas numeradas del 1 al 20, con estado
     * DISPONIBLE. Este método puede utilizarse para inicializar el sistema.
     */
    @Override
    public void insertarMesasIniciales() {
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        entityManager.getTransaction().begin();
        for (int i = 1; i <= 20; i++) {
            entityManager.persist(new Mesa(i, EstadoMesa.DISPONIBLE));
        }

        entityManager.getTransaction().commit();
    }

    /**
     * Obtiene todas las mesas registradas en la base de datos.
     *
     * @return Una lista de objetos MesaRegistradaDTO con la información de
     * todas las mesas.
     */
    @Override
    public List<MesaRegistradaDTO> obtenerMesas() {
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

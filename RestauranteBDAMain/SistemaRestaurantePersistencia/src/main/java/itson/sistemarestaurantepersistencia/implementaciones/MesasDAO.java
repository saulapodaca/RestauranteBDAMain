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
                      m.id, m.numeroMesa, m.estadoMesa)
                      FROM Mesa m
                      """;
        return entityManager.createQuery(jpql,
                MesaRegistradaDTO.class).getResultList();

    }
}

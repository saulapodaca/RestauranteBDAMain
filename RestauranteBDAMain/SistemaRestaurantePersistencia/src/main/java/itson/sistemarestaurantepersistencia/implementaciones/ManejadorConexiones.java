package itson.sistemarestaurantepersistencia.implementaciones;

/**
 *
 * @author rauln
 */
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ManejadorConexiones {

    public static boolean isTestMode = false;

    public static EntityManager getEntityManager() {
        //SOLICITAMOS UNA FABRICA DE ENTITY MANAGERS
        EntityManagerFactory emFactory;

        if (isTestMode) {
            emFactory = Persistence.createEntityManagerFactory("itson_SistemaRestaurantePersistencia_jar_1.0PU_TEST");
        } else {
            emFactory = Persistence.createEntityManagerFactory("itson_SistemaRestaurantePersistencia_jar_1.0PU");
        }
        //LA FABRICA CREA UN entityManager = base de datos
        EntityManager entityManager = emFactory.createEntityManager();
        return entityManager;
    }
}

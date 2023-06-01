package api;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Estudiante
 */
@ApplicationPath("/api")
public class RestApplication extends Application {


    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();
        classes.add(CorsFilter.class);
        classes.add(CarroResource.class);
        // Agrega aquí tus clases de recursos (endpoints) adicionales
        classes.add(ClienteResource.class);
        classes.add(AgenciaResource.class);
        classes.add(ReservaResource.class);
        return classes;
    }


}

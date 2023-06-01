package Model.Entity;
/**
 *
 * @author Omar Villamizar
 */
public class Agencia {
    private String id;
    private String nombre;

    public Agencia(String id) {
        this.id = id;
    }

    public Agencia(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Agencia{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}

package Model.Entity;
/**
 *
 * @author Omar Villamizar
 */
public class Reserva {
    private String id;
    private String fechaInicio;
    private String fechaFin;

    Cliente cliente;
    Carro carro;

    public Reserva(String id) {

        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Carro getCarro() {
        return carro;
    }

    public void setCarro(Carro carro) {
        this.carro = carro;
    }

    public Reserva(String id, String fechaInicio, String fechaFin, Cliente cliente, Carro carro) {
        this.id = id;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.cliente = cliente;
        this.carro = carro;
    }


    public String getId() {

        return id;
    }

    public void setId(String id) {

        this.id = id;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {

        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {

        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {

        this.fechaFin = fechaFin;
    }


    @Override
    public String toString() {
        return "Reserva{" +
                "id='" + id + '\'' +
                ", fechaInicio='" + fechaInicio + '\'' +
                ", fechaFin='" + fechaFin + '\'' +
                ", cliente=" + cliente +
                ", carro=" + carro +"\n";
    }
}

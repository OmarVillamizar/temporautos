package Model.Entity;
/**
 *
 * @author Omar Villamizar
 */
public class Carro {
    private String id;
    private String modelo;
    private String marca;
    private String tipoDeCaja;
    Agencia agencia;

    public Carro(String id) {
        this.id = id;

    }

    public Carro(String id, String modelo, String marca, String tipoDeCaja, Agencia agencia) {
        this.id = id;
        this.modelo = modelo;
        this.marca = marca;
        this.tipoDeCaja = tipoDeCaja;
        this.agencia = agencia;
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {

        this.id = id;
    }

    public String getModelo() {

        return modelo;
    }

    public void setModelo(String modelo) {

        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getTipoDeCaja() {

        return tipoDeCaja;
    }

    public void setTipoDeCaja(String tipoDeCaja) {

        this.tipoDeCaja = tipoDeCaja;
    }

    public Agencia getAgencia() {
        return agencia;
    }

    public void setAgencia(Agencia agencia) {
        this.agencia = agencia;
    }

    @Override
    public String toString() {
        return "Carro{" +
                "id='" + id + '\'' +
                ", modelo='" + modelo + '\'' +
                ", marca='" + marca + '\'' +
                ", tipoDeCaja='" + tipoDeCaja + '\'' +
                ", PERTENECE A = " + agencia + "\n";
    }
}

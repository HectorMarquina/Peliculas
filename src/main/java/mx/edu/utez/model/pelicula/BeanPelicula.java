package mx.edu.utez.model.pelicula;

public class BeanPelicula {

    private long id;
    private  String name;
    private String description;
    private String estreno;
    private Double recaudacion;
    private int status;

    public BeanPelicula(long id, String name, String description, String estreno, Double recaudacion, int status) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.estreno = estreno;
    this.recaudacion = recaudacion;
    this.status = status;
    }

    public BeanPelicula() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEstreno() {
        return estreno;
    }

    public void setEstreno(String estreno) {
        this.estreno = estreno;
    }

    public Double getRecaudacion() {
        return recaudacion;
    }

    public void setRecaudacion(Double recaudacion) {
        this.recaudacion = recaudacion;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
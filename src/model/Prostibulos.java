package model;

public class Prostibulos {
    //Atributos de classe Prostibulos:
    private int idProstibulo;
    private String nomProstibulo;
    private String direccionProstibulo;
    private int capacidadChicas;
    private OrigenEnum origen;
    private boolean travestit;
    private boolean drogas;
    private boolean armas;
    private int precio;

    //Constructor vacio:
    public Prostibulos(){}

    //Constructor completo:
    public Prostibulos(int id, String nomProstibulo, String direccionProstibulo, int capacidadChicas,
                       OrigenEnum origen, boolean travestit, boolean drogas, boolean armas, int precio) {
        this.idProstibulo = id;
        this.nomProstibulo = nomProstibulo;
        this.direccionProstibulo = direccionProstibulo;
        this.capacidadChicas = capacidadChicas;
        this.origen = origen;
        this.travestit = travestit;
        this.drogas = drogas;
        this.armas = armas;
        this.precio = precio;
    }

    //GETTERS:
    public int getIdProstibulo() {
        return idProstibulo;
    }

    public String getNomProstibulo() {
        return nomProstibulo;
    }

    public String getDireccionProstibulo() {
        return direccionProstibulo;
    }

    public int getCapacidadChicas() {
        return capacidadChicas;
    }

    public OrigenEnum getOrigen() {
        return origen;
    }

    public boolean isTravestit() {
        return travestit;
    }

    public boolean isDrogas() {
        return drogas;
    }

    public boolean isArmas() {
        return armas;
    }

    public int getPrecio() {
        return precio;
    }




    //SETTERS:
    public void setId(int id) {
        this.idProstibulo = id;
    }
    public void setNomProstibulo(String nomProstibulo) {
        this.nomProstibulo = nomProstibulo;
    }
    public void setDireccionProstibulo(String direccionProstibulo) {
        this.direccionProstibulo = direccionProstibulo;
    }
    public void setCapacidadChicas(int capacidadChicas) {
        this.capacidadChicas = capacidadChicas;
    }
    public void setOrigen(OrigenEnum origen) {
        this.origen = origen;
    }
    public void setTravestit(boolean travestit) {
        this.travestit = travestit;
    }
    public void setDrogas(boolean drogas) {
        this.drogas = drogas;
    }
    public void setArmas(boolean armas) {
        this.armas = armas;
    }
    public void setPrecio(int precio) {
        this.precio = precio;
    }


    //Enum:
    public enum OrigenEnum{
        RUS, ESP, LAT, CHINA, NEGRAS
    }
}

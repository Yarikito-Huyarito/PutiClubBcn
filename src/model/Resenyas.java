package model;

public class Resenyas {
    //Atributos de classe Resenya:
    private int idResenya;
    private int idProstibulo;
    private String nomClientResenya;
    private int puntuacionResenya;
    private String comentarioResenya;

    //Constructor vacio:
    public Resenyas(){}

    //Constructor completo:
    public Resenyas(int idResenya, int idProstibulo, String nomClientResenya, int puntuacionResenya, String comentarioResenya) {
        this.idResenya = idResenya;
        this.idProstibulo = idProstibulo;
        this.nomClientResenya = nomClientResenya;
        this.puntuacionResenya = puntuacionResenya;
        this.comentarioResenya = comentarioResenya;
    }


    //GETTERS:
    public int getIdResenya() {
        return idResenya;
    }

    public int getIdProstibulo() {
        return idProstibulo;
    }

    public String getNomClientResenya() {
        return nomClientResenya;
    }

    public int getPuntuacionResenya() {
        return puntuacionResenya;
    }

    public String getComentarioResenya() {
        return comentarioResenya;
    }



    //SETTERS:
    public void setIdResenya(int idResenya) {
        this.idResenya = idResenya;
    }
    public void setIdProstibulo(int idProstibulo) {
        this.idProstibulo = idProstibulo;
    }
    public void setNomClientResenya(String nomClientResenya) {
        this.nomClientResenya = nomClientResenya;
    }
    public void setPuntuacionResenya(int puntuacionResenya) {
        this.puntuacionResenya = puntuacionResenya;
    }
    public void setComentarioResenya(String comentarioResenya) {
        this.comentarioResenya = comentarioResenya;
    }
}

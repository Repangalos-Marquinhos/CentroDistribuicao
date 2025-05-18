package model.entities;

import java.util.Objects;

public class Estado {

    private int id_estado;
    private String Estado;
    private String sigla;

    public Estado() {}

    public Estado(int id_estado, String estado, String sigla) {
        this.id_estado = id_estado;
        Estado = estado;
        this.sigla = sigla;
    }

    public int getId_estado() {
        return id_estado;
    }

    public void setId_estado(int id_estado) {
        this.id_estado = id_estado;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    @Override
    public String toString() {
        return "Estado{" +
                "Estado='" + Estado + '\'' +
                ", id_estado=" + id_estado +
                ", sigla='" + sigla + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Estado estado = (Estado) o;
        return id_estado == estado.id_estado && Objects.equals(Estado, estado.Estado) && Objects.equals(sigla, estado.sigla);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_estado, Estado, sigla);
    }
}

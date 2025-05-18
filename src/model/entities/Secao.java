package model.entities;

import java.util.Objects;

public class Secao {

    private int id_secao;
    private String descricao;

    public Secao() {}

    public Secao(int id_secao, String descricao) {
        this.id_secao = id_secao;
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getId_secao() {
        return id_secao;
    }

    public void setId_secao(int id_secao) {
        this.id_secao = id_secao;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Secao secao = (Secao) o;
        return id_secao == secao.id_secao && Objects.equals(descricao, secao.descricao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_secao, descricao);
    }
}

package model.entities;

import java.util.Date;
import java.util.Objects;

public class Produto {

    private int id_produto;
    private String descriçao;
    private Secao secao;
    private Date data_armazenamento;

    public Produto(int id_produto, String descriçao, Secao secao, Date data_armazenamento) {
        this.id_produto = id_produto;
        this.descriçao = descriçao;
        this.secao = secao;
        this.data_armazenamento = data_armazenamento;
    }

    public Produto() {}

    public Date getData_armazenamento() {
        return data_armazenamento;
    }

    public void setData_armazenamento(Date data_armazenamento) {
        this.data_armazenamento = data_armazenamento;
    }

    public String getDescricao() {
        return descriçao;
    }

    public void setDescricao(String descriçao) {
        this.descriçao = descriçao;
    }

    public int getId_produto() {
        return id_produto;
    }

    public void setId_produto(int id_produto) {
        this.id_produto = id_produto;
    }

    public Secao getSecao() {
        return secao;
    }

    public void setSecao(Secao secao) {
        this.secao = secao;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "data_armazenamento=" + data_armazenamento +
                ", id_produto=" + id_produto +
                ", descriçao='" + descriçao + '\'' +
                ", secao=" + secao +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return id_produto == produto.id_produto && Objects.equals(descriçao, produto.descriçao) && Objects.equals(secao, produto.secao) && Objects.equals(data_armazenamento, produto.data_armazenamento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_produto, descriçao, secao, data_armazenamento);
    }
}
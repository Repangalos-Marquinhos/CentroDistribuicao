package model.entities;

import java.util.Objects;

public class Lote {

    private Produto produto;
    private String destino;
    private Cidade cidade;
    private Estado estado;
    private String cep;
    private Pedido pedido;

    public Lote(Produto produto, String destino, Cidade cidade, Estado estado, String cep, Pedido pedido) {
        this.produto = produto;
        this.destino = destino;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
        this.pedido = pedido;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    @Override
    public String toString() {
        return "Lote{" +
                "cep='" + cep + '\'' +
                ", produto=" + produto +
                ", destino='" + destino + '\'' +
                ", cidade=" + cidade +
                ", estado=" + estado +
                ", pedido=" + pedido +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Lote lote = (Lote) o;
        return Objects.equals(produto, lote.produto) && Objects.equals(destino, lote.destino) && Objects.equals(cidade, lote.cidade) && Objects.equals(estado, lote.estado) && Objects.equals(cep, lote.cep) && Objects.equals(pedido, lote.pedido);
    }

    @Override
    public int hashCode() {
        return Objects.hash(produto, destino, cidade, estado, cep, pedido);
    }
}

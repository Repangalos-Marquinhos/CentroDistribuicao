package model.entities;
import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import model.entities.Usuario;

public class Pedido {

    private int id;
    private Date data_pedido;
    private  Usuario id_usuario;//new Usuario("12345", 54)

    public Pedido(Date data_pedido, int id, Usuario id_usuario) {
        this.data_pedido = data_pedido;
        this.id = id;
        this.id_usuario = id_usuario;
    }

    public Date getData_pedido() {
        return data_pedido;
    }

    public void setData_pedido(Date data_pedido) {
        this.data_pedido = data_pedido;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getId_usuario() {
        return id_usuario;
    }


    public void setId_usuario(Usuario id_usuario) {
        this.id_usuario = id_usuario;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "data_pedido=" + data_pedido +
                ", id=" + id +
                ", id_usuario=" + id_usuario +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Pedido pedido = (Pedido) o;
        return id == pedido.id && Objects.equals(data_pedido, pedido.data_pedido) && Objects.equals(id_usuario, pedido.id_usuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, data_pedido, id_usuario);
    }
}

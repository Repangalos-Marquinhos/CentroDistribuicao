package model.entities;

import java.util.Objects;

public class Usuario {

    private int id_user;
    private String senha;
    private String permissao;

    public Usuario() {}

    public Usuario(String senha, int id_user, String permissao) {
        this.id_user = id_user;
        this.senha = senha;
        this.permissao = permissao;

    }



    public int getId() {
        return id_user;
    }

    public void setId(int id) {
        this.id_user = id;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getUsuario() {
        return id_user;
    }

    public void setUsuario(int usuario) {
        this.id_user = usuario;
    }

    public String getPermissao() {
        return permissao;
    }

    public void setPermissao(String permissao) {
        this.permissao = permissao;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "usuario='" + id_user + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }

    /*  @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario1 = (Usuario) o;
        return id == usuario1.id && Objects.equals(usuario, usuario1.usuario) && Objects.equals(senha, usuario1.senha);
    }


}*/
}

package model.dao.impl;

import db.DB;
import model.dao.CidadeDao;
import model.dao.PedidoDao;
import model.dao.ProdutoDao;
import model.dao.UsuarioDao;
import model.dao.impl.UsuarioDaoJDBC;
import model.entities.Cidade;

public class DaoFactory {


    public static UsuarioDao createUsuarioDao() {
        return new UsuarioDaoJDBC(DB.getConnection());
    }

    public static CidadeDao createCidadeDao() {
        return new CidadeDaoJDBC(DB.getConnection());
    }

    public static PedidoDao createPedidoDao() {
        return new PedidoDaoJDBC(DB.getConnection());
    }

    public static ProdutoDao createProdutoDao() {
        return new ProdutoDaoJDBC(DB.getConnection());
    }

}



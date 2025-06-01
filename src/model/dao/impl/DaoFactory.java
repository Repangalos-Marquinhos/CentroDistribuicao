package model.dao.impl;

import db.DB;
import model.dao.*;
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

    public static LoteDao createLoteDao() {
        return new LoteDaoJDBC(DB.getConnection());
    }
}



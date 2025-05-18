package model.dao.impl;

import db.DB;
import model.dao.CidadeDao;
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
}

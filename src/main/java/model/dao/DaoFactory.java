package model.dao;

import model.dao.model.dao.impl.DepartmentDaoJDBC;
import model.dao.model.dao.impl.SellerDaoJDBC;
import model.db.ConnectionDb;
import model.entities.Department;

//classe auxiliar responsavel por instanciar os DAOs
public class DaoFactory {

    //macete - desta forma a minha classe vai expor um metodo que retorna o tipo da interface SellerDao
    //mas internamente vai instanciar uma implementacao e desta forma não expoe a implementacao
    public static SellerDao createSellerDao(){

        return new SellerDaoJDBC(ConnectionDb.getConnection());
    }
    public static DepartmentDao createDepartmentDao(){

        return new DepartmentDaoJDBC(ConnectionDb.getConnection());
    }
}

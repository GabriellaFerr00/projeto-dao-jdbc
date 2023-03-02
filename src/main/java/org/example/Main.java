package org.example;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Seller;

public class Main {
    public static void main(String[] args) {

//        Departament obj = new Departament(1, "Books");
//        Seller seller = new Seller(2, "Gabi", "gabi@gmail.com", new Date(), 3000.0, obj);

        SellerDao sellerDao = DaoFactory.createSellerDao();

        Seller seller = sellerDao.findById(3);

        System.out.print(seller);

//        Connection conn = ConnectionDb.getConnection();
//        ConnectionDb.closeConnection();
    }
}
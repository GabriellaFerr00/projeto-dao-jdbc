package org.example;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        SellerDao sellerDao = DaoFactory.createSellerDao();

        System.out.println("************ TEST 1: Seller findById ************\n");
        Seller seller = sellerDao.findById(3);
        System.out.print(seller);

        System.out.println("\n************ TEST 2: Seller findByDepartment ************");
        Department department = new Department(2, null);
        List<Seller> sellerList = sellerDao.findByDepartment(department);
        for(Seller obj : sellerList){
            System.out.println(obj);
        }

        System.out.println("\n************ TEST 3: Seller findByAll ************");
        List<Seller> sellerList2 = sellerDao.findAll();
        for(Seller obj : sellerList2){
            System.out.println(obj);
        }

        System.out.println("\n************ TEST 4: Seller insert ************");
        Seller sellerInsert = new Seller(null, "Amanda", "amanda@gmail.com", new Date(), 3500.0, department);
        sellerDao.insert(sellerInsert);
        System.out.println("Inserted - New id: " + sellerInsert.getId());

    }
}
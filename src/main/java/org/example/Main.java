package org.example;

import model.entities.Departament;
import model.entities.Seller;

import java.util.Date;

public class Main {
    public static void main(String[] args) {

        Departament obj = new Departament(1, "Books");
        Seller seller = new Seller(2, "Gabi", "gabi@gmail.com", new Date(), 3000.0, obj);

        System.out.print(seller);
    }
}
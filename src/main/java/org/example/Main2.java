package org.example;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        DepartmentDao departmentDao = DaoFactory.createDepartmentDao();

        System.out.println("\n************ TEST 1: Departament insert ************");
        Department department = new Department(5, "Finance");
        departmentDao.insert(department);
        System.out.println("Inserted - New id: " + department.getId());

        sc.close();
    }
}
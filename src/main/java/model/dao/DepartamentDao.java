package model.dao;

import model.entities.Departament;

import java.util.List;

public interface DepartamentDao {
    void insert(Departament dpt);
    void update(Departament dpt);
    void deleteById(Integer id);
    Departament findById(Integer id);
    List<Departament> findAll();
}

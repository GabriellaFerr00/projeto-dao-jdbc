package model.dao.model.dao.impl;

import model.dao.DepartmentDao;
import model.db.ConnectionDb;
import model.db.DbException;
import model.entities.Department;
import model.entities.Seller;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DepartmentDaoJDBC implements DepartmentDao {
    private Connection conn;

    public DepartmentDaoJDBC(Connection conn){
        this.conn = conn;
    }

    @Override
    public void insert(Department dpt) {
        PreparedStatement st = null;
        try{
            st = conn.prepareStatement(
                    "INSERT INTO department "
                            + "(Name) "
                            + "VALUES (?) ",
                    Statement.RETURN_GENERATED_KEYS
            );

            st.setString(1, dpt.getName());

            int rowsAffected = st.executeUpdate();

            if(rowsAffected > 0){
                ResultSet rs = st.getGeneratedKeys();
                if(rs.next()){
                    int id = rs.getInt(1);
                    dpt.setId(id);
                }
                ConnectionDb.closeResultSet(rs);
            } else {
                throw new DbException("Unexpected error! No rows affected!");
            }
        } catch (SQLException e){
            throw new DbException(e.getMessage());
        } finally {
            ConnectionDb.closeStatement(st);
        }
    }

    @Override
    public void update(Department dpt) {
        PreparedStatement st = null;
        try{
            st = conn.prepareStatement(
                    "UPDATE department "
                            + "SET Name = ? "
                            + "WHERE id = ? ",
                    Statement.RETURN_GENERATED_KEYS
            );

            st.setString(1, dpt.getName());
            st.setInt(2, dpt.getId());

            st.executeUpdate();

        } catch (SQLException e){
            throw new DbException(e.getMessage());
        } finally {
            ConnectionDb.closeStatement(st);
        }

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Department findById(Integer id) {
        PreparedStatement st = null; //executa query sql
        ResultSet rs = null; //inicia na posicao 0 - sem registro

        try {
            st = conn.prepareStatement(
                    "SELECT * FROM department WHERE id = ? "
            );
            st.setInt(1, id);
            rs = st.executeQuery();

            if(rs.next()){
                Department department = new Department();
                department.setId(rs.getInt("Id"));
                department.setName(rs.getString("Name"));

                return department;

            }
            return null;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            ConnectionDb.closeStatement(st);
            ConnectionDb.closeResultSet(rs);
        }
    }

    @Override
    public List<Department> findAll() {
        PreparedStatement st = null; //executa query sql
        ResultSet rs = null; //inicia na posicao 0 - sem registro

        try {
            st = conn.prepareStatement(
                    "SELECT * FROM department ORDER BY Name "
            );

            rs = st.executeQuery();

            List<Department> departmentsList = new ArrayList<>();

            while(rs.next()){
                Department department = new Department();
                department.setId(rs.getInt("Id"));
                department.setName(rs.getString("Name"));

                departmentsList.add(department);
                }
            return departmentsList;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            ConnectionDb.closeStatement(st);
            ConnectionDb.closeResultSet(rs);
        }
    }
}

package model.dao.model.dao.impl;

import model.dao.SellerDao;
import model.db.ConnectionDb;
import model.db.DbException;
import model.entities.Department;
import model.entities.Seller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SellerDaoJDBC implements SellerDao {

    private Connection conn;

    public SellerDaoJDBC(Connection conn){
        this.conn = conn;
    }

    @Override
    public void insert(Seller seller) {

    }

    @Override
    public void update(Seller seller) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Seller findById(Integer id) {
        PreparedStatement st = null; //executa query sql
        ResultSet rs = null; //inicia na posicao 0 - sem registro

        try {
            st = conn.prepareStatement(
                    "SELECT seller.*, department.Name as DepName "
                    + "FROM seller INNER JOIN department "
                    + "ON seller.DepartmentId = department.Id "
                    + "WHERE seller.Id = ? "
            );
            st.setInt(1, id);
            rs = st.executeQuery();

            if(rs.next()){
                Department department = instantiateDepartment(rs);

                Seller seller = instantiateSeller(rs, department);
                return  seller;
            }
            return null;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            ConnectionDb.closeStatement(st);
            ConnectionDb.closeResultSet(rs);
        }
    }

    private Department instantiateDepartment(ResultSet rs) throws SQLException {
        Department department = new Department();
        department.setId(rs.getInt("DepartmentId"));
        department.setName(rs.getString("DepName"));

        return department;
    }

    private Seller instantiateSeller(ResultSet rs, Department department) throws SQLException{
        Seller seller = new Seller();
        seller.setId(rs.getInt("Id"));
        seller.setName(rs.getString("Name"));
        seller.setEmail(rs.getString("Email"));
        seller.setBaseSalary(rs.getDouble("BaseSalary"));
        seller.setBirthDate(rs.getDate("BirthDate"));
        seller.setDepartment(department);

        return seller;
    }

    @Override
    public List<Seller> findAll() {

        PreparedStatement st = null; //executa query sql
        ResultSet rs = null; //inicia na posicao 0 - sem registro

        try {
            st = conn.prepareStatement(
                    "SELECT seller.*, department.Name as DepName "
                            + "FROM seller INNER JOIN department "
                            + "ON seller.DepartmentId = department.Id "
                            + "ORDER BY Name"
            );

            rs = st.executeQuery();

            List<Seller> sellerList = new ArrayList<>();

            Map<Integer, Department> map = new HashMap<>();

            while(rs.next()){
                Department dept = map.get(rs.getInt("DepartmentId"));
                if(dept == null){
                    dept = instantiateDepartment(rs);
                    map.put(rs.getInt("DepartmentId"), dept);
                }

                Seller seller = instantiateSeller(rs, dept);
                sellerList.add(seller);
            }
            return sellerList;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            ConnectionDb.closeStatement(st);
            ConnectionDb.closeResultSet(rs);
        }
    }

    @Override
    public List<Seller> findByDepartment(Department department) {
        PreparedStatement st = null; //executa query sql
        ResultSet rs = null; //inicia na posicao 0 - sem registro

        try {
            st = conn.prepareStatement(
                    "SELECT seller.*, department.Name as DepName "
                            + "FROM seller INNER JOIN department "
                            + "ON seller.DepartmentId = department.Id "
                            + "WHERE Department.Id = ? "
                            + "ORDER BY Name"
            );
            st.setInt(1, department.getId());
            rs = st.executeQuery();

            List<Seller> sellerList = new ArrayList<>();

            Map<Integer, Department> map = new HashMap<>();

            while(rs.next()){
                Department dept = map.get(rs.getInt("DepartmentId"));
                if(dept == null){
                    dept = instantiateDepartment(rs);
                    map.put(rs.getInt("DepartmentId"), dept);
                }

                Seller seller = instantiateSeller(rs, dept);
                sellerList.add(seller);
            }
            return sellerList;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            ConnectionDb.closeStatement(st);
            ConnectionDb.closeResultSet(rs);
        }
    }
}

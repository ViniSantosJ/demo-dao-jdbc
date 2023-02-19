package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.List;

public class DepartmentDaoJDBC implements DepartmentDao {

    private Connection conn;
    public DepartmentDaoJDBC(){}

    public DepartmentDaoJDBC(Connection conn) {
        this.conn = conn;
    }


    @Override
    public void insert(Department obj) {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement(
                    "  INSERT INTO DEPARTMENT (NAME)  " +
                    "  VALUES (?) ",
                    Statement.RETURN_GENERATED_KEYS);

            st.setString(1, obj.getName());

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0){
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    obj.setId(id);
                }
                DB.closeResultSet(rs);
            } else {
                throw  new DbException("Unexpected error! No rows affected!");
            }

        } catch (SQLException e) {
           throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void update(Department obj) {

    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement(
                    "delete from department " +
                            " where id = ?");
            st.setInt(1, id);

            int rowsDeleted = st.executeUpdate();

            if (rowsDeleted == 0) {
                System.out.println("ID not found!");
            } else {
                System.out.println("Delete complete!");
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public Department findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement(
                    "  SELECT id, name   " +
                            "  FROM department " +
                            "  WHERE ID = ?");
            st.setInt(1, id);
            rs = st.executeQuery();
            if (rs.next()) {
                Department dep = instantiateDepartment(rs);
                return dep;
            }
            return null;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    private Department instantiateDepartment(ResultSet rs) throws SQLException {
        Department dep = new Department();
        dep.setId(rs.getInt("Id"));
        dep.setName(rs.getString("Name"));
        return dep;
    }

    @Override
    public List<Department> findAll() {
        return null;
    }
}

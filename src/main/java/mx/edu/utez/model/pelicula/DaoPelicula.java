package mx.edu.utez.model.pelicula;

import jdk.nashorn.internal.runtime.Context;
import mx.edu.utez.service.ConnectionMySQL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoPelicula {
    Connection con;
    CallableStatement cstm;
    ResultSet rs;
    Logger logger = LoggerFactory.getLogger(DaoPelicula.class);

    public List<BeanPelicula> findAll(){
        List<BeanPelicula> listaPelicula = new ArrayList<>();
        try {
            // SELECT * FROM pelicula;
            con = ConnectionMySQL.getConnection();
            cstm = con.prepareCall("{call sp_findAll}");
            rs = cstm.executeQuery();

            while(rs.next()){
                BeanPelicula pelicula = new BeanPelicula();

                pelicula.setId(rs.getInt("id"));
                pelicula.setName(rs.getString("name"));
                pelicula.setDescription(rs.getString("description"));
                pelicula.setEstreno(rs.getString("Estreno"));
                pelicula.setRecaudacion(rs.getDouble("recaudacion"));
                pelicula.setStatus(rs.getInt("status"));

                listaPelicula.add(pelicula);
            }
        }catch (SQLException e){
            logger.error("Ha ocurrido un error: " + e.getMessage());
        } finally {
            ConnectionMySQL.closeConnections(con, cstm, rs);
        }
        return listaPelicula;
    }

    public BeanPelicula findById(long id){
        BeanPelicula pelicula = null;
        try {
            // SELECT * FROM pelicula WHERE id = ?;
            con = ConnectionMySQL.getConnection();
            cstm = con.prepareCall("SELECT * FROM pelicula WHERE id = ?");
            cstm.setLong(1, id);
            rs = cstm.executeQuery();

            if(rs.next()){
                pelicula = new BeanPelicula();

                pelicula.setId(rs.getInt("id"));
                pelicula.setName(rs.getString("name"));
                pelicula.setDescription(rs.getString("description"));
                pelicula.setEstreno(rs.getString("Estreno"));
                pelicula.setRecaudacion(rs.getDouble("recaudacion"));
                pelicula.setStatus(rs.getInt("status"));

            }
        }catch (SQLException e){
            logger.error("Ha ocurrido un error: " + e.getMessage());
        } finally {
            ConnectionMySQL.closeConnections(con, cstm, rs);
        }
        return pelicula;
    }

    public boolean create(BeanPelicula pelicula){
        boolean flag = false;
        try{
            con = ConnectionMySQL.getConnection();
            cstm = con.prepareCall("{call sp_create(?,?,?,?)}");

            cstm.setString(1, pelicula.getName());
            cstm.setString(2, pelicula.getDescription());
            cstm.setString(3, pelicula.getEstreno());
            cstm.setDouble(4, pelicula.getRecaudacion());
            cstm.execute();
            flag = true;

        }catch(SQLException e){
            logger.error("Ha ocurrido un error: " + e.getMessage());
        } finally {
            ConnectionMySQL.closeConnections(con, cstm);
        }
        return flag;
    }

    public boolean update(BeanPelicula pelicula){
        boolean flag = false;
        try{
            con = ConnectionMySQL.getConnection();
            cstm = con.prepareCall("{call sp_update(?,?,?,?,?)}");

            cstm.setInt(1, (int) pelicula.getId());
            cstm.setString(2, pelicula.getName());
            cstm.setString(3, pelicula.getDescription());
            cstm.setString(4, pelicula.getEstreno());
            cstm.setDouble(5, pelicula.getRecaudacion());

            flag = cstm.execute();
        }catch(SQLException e){
            logger.error("Ha ocurrido un error: " + e.getMessage());
        }finally{
            ConnectionMySQL.closeConnections(con, cstm);
        }
        return flag;
    }

    public boolean delete(long id){
        boolean flag = false;
        try{
            con = ConnectionMySQL.getConnection();
            cstm = con.prepareCall("{call sp_delete2(?)}");
            cstm.setLong(1, id);

            flag = cstm.execute();
        }catch(SQLException e){
            logger.error("Ha ocurrido un error: " + e.getMessage());
        }finally{
            ConnectionMySQL.closeConnections(con, cstm);
        }
        return flag;
    }
}

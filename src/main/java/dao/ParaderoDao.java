package dao;

import business.Chofer;
import business.Moto;
import business.Paradero;
import util.conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ParaderoDao {

    public static Paradero create(Paradero paradero) {
        try (Connection connection = conexion.getConnection()) {
            final String sql = "INSERT INTO paradero (idchofer, idmoto, nombre, direccion) values (?,?,?,?) ";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, paradero.getIdchofer());
                statement.setInt(2, paradero.getIdmoto());
                statement.setString(3, paradero.getNombre());
                statement.setString(4, paradero.getDireccion());
                statement.executeUpdate();
                int idparadero = statement.executeUpdate();
                paradero.setIdparadero(idparadero);
                return paradero;
            }
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    public  static void update(Paradero paradero, String direccion) {
        try (Connection connection = conexion.getConnection()) {
            final String sql = "UPDATE paradero SET direccion=?  WHERE idparadero=?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, direccion );
                statement.setInt   (2, paradero.getIdparadero());
                statement.executeUpdate();
            }
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }
    public static  void delete(Paradero paradero) {
        try (Connection connection = conexion.getConnection()) {
            final String sql = "DELETE FROM paradero  WHERE idparadero=?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1,  paradero.getIdparadero());
                statement.executeUpdate();
            }
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }


    public static List<Paradero> findAll() {
        List<Paradero> paraderos = new ArrayList<>();
        try (Connection connection = conexion.getConnection()) {
            final String sql = "SELECT * FROM paradero";
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(sql)) {
                    while (resultSet.next()) {
                        Paradero paradero = getParadero(resultSet);
                        paraderos.add(paradero);
                    }
                }
            }
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
        return paraderos ;
    }

    public static Paradero findById(Integer id) {
        Paradero paraderos = null;
        try (Connection connection = conexion.getConnection()) {
            final String sql = "SELECT * FROM paradero where idparadero=?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        paraderos = new Paradero(
                                resultSet.getInt("idparadero"),
                                resultSet.getInt("idchofer"),
                                resultSet.getInt("idmoto"),
                                resultSet.getString("nombre"),
                                resultSet.getString("direccion") ) ;
                    }
                }
            }
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }

        return paraderos;
    }

    private static Paradero getParadero(ResultSet resultSet)  throws SQLException {
        Paradero paradero = new Paradero(
                resultSet.getInt("idparadero"),
                resultSet.getInt("idchofer"),
                resultSet.getInt("idmoto"),
                resultSet.getString("nombre"),
                resultSet.getString("direccion"));
        return paradero;
    }
}

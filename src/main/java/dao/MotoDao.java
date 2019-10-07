package dao;

import business.Chofer;
import business.Moto;
import util.conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MotoDao {

    public static Moto create(Moto moto) {
        try (Connection connection = conexion.getConnection()) {
            final String sql = "INSERT INTO motos (placa, modelo, color, motor, año_modelo) values (?,?,?,?,?) ";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, moto.getPlaca());
                statement.setString(2, moto.getModelo());
                statement.setString(3, moto.getColor());
                statement.setString(4, moto.getMotor());
                statement.setString(5,moto.getAño_modelo());
                statement.executeUpdate();
                int idmoto = statement.executeUpdate();
                moto.setIdmotos(idmoto);
                return moto;
            }
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    public  static void update(Moto moto , String placa) {
        try (Connection connection = conexion.getConnection()) {
            final String sql = "UPDATE motos SET Placa=?  WHERE idmotos=?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1,placa );
                statement.setInt   (2, moto.getIdmotos());
                statement.executeUpdate();
            }
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }
    public static  void delete(Moto moto) {
        try (Connection connection = conexion.getConnection()) {
            final String sql = "DELETE FROM motos  WHERE idmotos=?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, moto.getIdmotos());
                statement.executeUpdate();
            }
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }


    public static List<Moto> findAll() {
        List<Moto> motores = new ArrayList<>();
        try (Connection connection = conexion.getConnection()) {
            final String sql = "SELECT * FROM motos";
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(sql)) {
                    while (resultSet.next()) {
                        Moto chofer = getMoto(resultSet);
                        motores.add(chofer);
                    }
                }
            }
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
        return motores ;
    }

    public static Moto findById(Integer idMotos) {
        Moto motores = null;
        try (Connection connection = conexion.getConnection()) {
            final String sql = "SELECT * FROM motos where idmotos=?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, idMotos);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        motores =  new Moto(
                                resultSet.getInt("idmotos"),
                                resultSet.getString("Placa"),
                                resultSet.getString("Modelo"),
                                resultSet.getString("color"),
                                resultSet.getString("Motor"),
                                resultSet.getString("año_modelo")) ;
                    }
                }
            }
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }

        return motores;
    }
    private static Moto getMoto(ResultSet resultSet)  throws SQLException {
        Moto motos = new Moto(
                resultSet.getInt("idmotos"),
                resultSet.getString("Placa"),
                resultSet.getString("Modelo"),
                resultSet.getString("color"),
                resultSet.getString("Motor"),
                resultSet.getString("año_modelo"));
        return motos;
    }
}

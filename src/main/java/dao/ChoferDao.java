package dao;

import business.Chofer;
import util.conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChoferDao {

    public static Chofer create(Chofer chofer) {
        try (Connection connection = conexion.getConnection()) {
            final String sql = "INSERT INTO chofer (nombre, apellido,dni,direccion,fechanacimiento) values (?,?,?,?, ?) ";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, chofer.getNombre());
                statement.setString(2, chofer.getApellido());
                statement.setString(3, chofer.getDni());
                statement.setString(4, chofer.getDireccion());
                statement.setDate( 5,   chofer.getFechanacimiento());
                statement.executeUpdate();
                int idchofer = statement.executeUpdate();
                chofer.setIdChofer(idchofer);
                return chofer;
            }
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    public  static void update(Chofer chofer , String nombre) {
        try (Connection connection = conexion.getConnection()) {
            final String sql = "UPDATE chofer SET nombre=?  WHERE idChofer=?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1,nombre );
                statement.setInt   (2, chofer.getIdChofer());
                statement.executeUpdate();
            }
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }
    public static  void delete(Chofer chofer) {
        try (Connection connection = conexion.getConnection()) {
            final String sql = "DELETE FROM chofer  WHERE idChofer=?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1,  chofer.getIdChofer());
                statement.executeUpdate();
            }
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }


    public static List<Chofer> findAll() {
        List<Chofer> choferes = new ArrayList<>();
        try (Connection connection = conexion.getConnection()) {
            final String sql = "SELECT * FROM chofer";
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(sql)) {
                    while (resultSet.next()) {
                       Chofer chofer = getChofer(resultSet);
                        choferes.add(chofer);
                    }
                }
            }
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
        return choferes ;
    }

    public static Chofer findById(Integer id) {
        Chofer choferes = null;
        try (Connection connection = conexion.getConnection()) {
            final String sql = "SELECT * FROM chofer where idChofer=?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        choferes = new Chofer(
                               resultSet.getInt("idChofer"),
                                resultSet.getString("nombre"),
                                resultSet.getString("apellido"),
                                resultSet.getString("dni"),
                                resultSet.getString("direccion"),
                                resultSet.getDate("fechanacimiento") ) ;
                    }
                }
            }
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }

        return choferes;
    }

    private static Chofer getChofer(ResultSet resultSet)  throws  SQLException{
        Chofer chofer = new Chofer(
          resultSet.getInt("idChofer"),
          resultSet.getString("nombre"),
          resultSet.getString("apellido"),
          resultSet.getString("dni"),
          resultSet.getString("direccion"),
          resultSet.getDate("fechanacimiento"));
        return chofer;
    }
}







   /* public static Chofer isValidLogin(String nombre, String apellido){
        try (Connection connection = conexion.getConnection()) {
            String sql = "SELECT * FROM chofer WHERE nombre=? and apellido=?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, nombre);
                statement.setString(2, apellido);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        Chofer user = getChofer(resultSet);
                        return user;
                    } else {
                        return null;
                    }
                }
              }
            } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }*/
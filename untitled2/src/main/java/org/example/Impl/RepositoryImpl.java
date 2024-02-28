package org.example.Impl;
import org.example.Conexion.ConexionBD;
import org.example.Models.Producto;
import org.example.Repositorio.Repository;
import java.sql.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
public class RepositoryImpl implements Repository<Producto> {
    private Connection getConnection() throws SQLException {
        return ConexionBD.getInstance();
    }
    private Producto createProduct(ResultSet resultSet) throws SQLException {
        Producto product = new Producto();
        product.setId(resultSet.getLong("id"));
        product.setNombre(resultSet.getString("nombre"));
        product.setPrecio(resultSet.getDouble("precio"));
        java.sql.Timestamp registerTimestamp = resultSet.getTimestamp("fecha_registro");
        if (registerTimestamp != null) {
            product.setFecha_registro(registerTimestamp.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        } else {
            product.setFecha_registro(null);}
        return product;}
    @Override
    public List<Producto> list() {
        List<Producto> productList = new ArrayList<>();
        try (Statement statement = getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * from new_table")) {
            while (resultSet.next()) {
                Producto product = createProduct(resultSet);
                productList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }

    @Override
    public Producto byId(Long id) {
        Producto product = null;
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement("SELECT * from new_table WHERE id = ?")) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                product = createProduct(resultSet);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
       return product;
    }

    @Override
    public void save(Producto producto) {

    }

    @Override
    public void delete(Long id) {

    }
}
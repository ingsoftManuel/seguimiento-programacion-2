package org.example.Application;
import org.example.Conexion.ConexionBD;
import org.example.Impl.RepositoryImpl;
import org.example.Models.Producto;
import org.example.Repositorio.Repository;
import java.sql.Connection;
import java.sql.SQLException;
public class Main2 {
    public static void main(String[] args){
        try (Connection conn = ConexionBD.getInstance()){
            Repository <Producto> repository = new RepositoryImpl();
            System.out.println("***** List products from database");
            repository.list().forEach(System.out::println);
            System.out.println("***** Get by Id: 1");
            System.out.println(repository.byId(1L).toString());
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}

package Clases;

import ConecBD.BDconexion;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class Compra {

    private static int id;
    private Cliente cliente;
    private Vehiculo vehiculo;
    private double precioCompra;

    public Compra() {
    }

    public Compra(Cliente cliente, Vehiculo vehiculo, double precioCompra) {
        this.cliente = cliente;
        this.vehiculo = vehiculo;
        this.precioCompra = precioCompra;
    }

    public static int getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(double precioCompra) {
        this.precioCompra = precioCompra;
    }

    public void registrarCompra(Scanner escaner, int cliente, int vehiculo) throws SQLException {

        String sql = "INSERT INTO `concesionaria`.`compras` (`idCliente`,`idVehiculo`) VALUES ("+"'"+cliente+"'"+","+"'"+vehiculo+"')";
        Statement stmt = BDconexion.getConexion().createStatement();
        stmt.executeUpdate (sql);
        BDconexion.CloseConnection();
        System.out.println("Se registro la compra");

    }

    public void listarCompras() throws SQLException {
        ArrayList<String[]> compras = new ArrayList<>();
        String sql = "SELECT compras.id, vehiculos.marca, vehiculos.modelo, vehiculos.precio, clientes.nombre, clientes.apellido FROM compras,vehiculos,clientes WHERE compras.idCliente = clientes.id AND compras.idVehiculo = vehiculos.id;";

        Statement stmt = BDconexion.getConexion().createStatement();
        ResultSet resultSet = stmt.executeQuery(sql);

        while (resultSet.next()) {
            String id = Integer.toString(resultSet.getInt("id"));
            String marca = resultSet.getString("marca");
            String modelo = resultSet.getString("modelo");
            BigDecimal precio = resultSet.getBigDecimal("precio");
            String precioS = precio.toString();
            String nombre = resultSet.getString("nombre");
            String apellido = resultSet.getString("apellido");

            String[] compras1 = {id,marca,modelo,precioS,nombre,apellido};
            compras.add(compras1);
        }

        System.out.print(String.format("| %-4s | %-15s | %-15s | %-12s | %-12s | %-15s |\n",
                "ID", "Marca", "Modelo", "precio", "Nombre", "Apellido"));

        System.out.println("----------------------------------------------------------------------------------------------------------------");

        for (String[] compra : compras) {
            System.out.print(String.format("| %-4s | %-15s | %-15s | %-12s | %-12s | %-15s |\n",
                    compra[0],  compra[1],  compra[2],  compra[3],  compra[4],  compra[5]));
        }
        BDconexion.CloseConnection();
    }
}

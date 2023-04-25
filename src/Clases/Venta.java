package Clases;

import ConecBD.BDconexion;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class Venta {

    private static int id;
    private Cliente cliente;
    private Vehiculo vehiculo;
    private Vendedor vendedor;
    private double precioVenta;

    public Venta() {
    }

    public Venta(Cliente cliente, Vehiculo vehiculo, Vendedor vendedor, double precioVenta) {
        this.id = ++id;
        this.cliente = cliente;
        this.vehiculo = vehiculo;
        this.vendedor = vendedor;
        this.precioVenta = precioVenta;
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

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public void registrarVenta (Scanner escaner,int idVehiculo, int idVendedor, int idCliente) throws SQLException {

        String sql = "INSERT INTO `concesionaria`.`ventas` (`idCliente`,`idVehiculo`,`idVendedor`) VALUES ("+"'"+idCliente+"'"+","+"'"+idVehiculo+"'"+","+"'"+idVendedor+"')";
        Statement stmt = BDconexion.getConexion().createStatement();
        stmt.executeUpdate (sql);
        BDconexion.CloseConnection();
        System.out.println("Se registro la venta");

    }

    public void listarVentas() throws SQLException {
        ArrayList<String[]> ventas = new ArrayList<>();
        String sql = "SELECT ventas.id, vehiculos.marca, vehiculos.modelo, vehiculos.precio, clientes.nombre, clientes.apellido FROM ventas,vehiculos,clientes WHERE ventas.idCliente = clientes.id AND ventas.idVehiculo = vehiculos.id;";

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

            String[] ventas1 = {id,marca,modelo,precioS,nombre,apellido};
            ventas.add(ventas1);
        }

        System.out.print(String.format("| %-4s | %-15s | %-15s | %-12s | %-12s | %-15s |\n",
                "ID", "Marca", "Modelo", "precio", "Nombre", "Apellido"));

        System.out.println("----------------------------------------------------------------------------------------------------------------");

        for (String[] venta : ventas) {
            System.out.print(String.format("| %-4s | %-15s | %-15s | %-12s | %-12s | %-15s |\n",
                    venta[0],  venta[1],  venta[2],  venta[3],  venta[4],  venta[5]));
        }
        BDconexion.CloseConnection();
    }
}

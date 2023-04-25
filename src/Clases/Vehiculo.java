package Clases;

import ConecBD.BDconexion;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class Vehiculo {

    private int id;
    private String marca;
    private String modelo;
    private String descripcion;
    private String anio;
    private double precio;
    private int disponible;

    public Vehiculo() {
    }

    public Vehiculo(String marca, String modelo, String descripcion, String anio, double precio, int disponible) {
        this.marca = marca;
        this.modelo = modelo;
        this.descripcion = descripcion;
        this.anio = anio;
        this.precio = precio;
        this.disponible = disponible;
    }

    public Vehiculo(int id, String marca, String modelo, String descripcion, String anio, double precio) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.descripcion = descripcion;
        this.anio = anio;
        this.precio = precio;
    }

    public  int getId() {
        return id;
    }

    public  void setId(int id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getDisponible() {
        return disponible;
    }

    public void setDisponible(int disponible) {
        this.disponible = disponible;
    }

    public void agregarVehiculo(Scanner escaner, Vehiculo vehiculo) throws SQLException {

        System.out.print("Ingrese la marca: ");
        vehiculo.setMarca(escaner.next());

        System.out.print("Ingrese el modelo: ");
        vehiculo.setModelo(escaner.next());

        System.out.print("Ingrese el año: ");
        vehiculo.setAnio(escaner.next());

        System.out.print("Ingrese una breve descripcion: ");
        vehiculo.setDescripcion(escaner.next());

        while(true) {
            System.out.print("Ingrese el precio: ");
            double precio;
            if(escaner.hasNextDouble()) {
                precio = escaner.nextDouble();
                vehiculo.setPrecio(precio);
                break;
            } else {
                System.out.println("Entrada inválida. Por favor, ingrese un número válido.");
                escaner.next();
            }
        }

        String sql = "INSERT INTO `concesionaria`.`vehiculos` (`marca`,`modelo`,`descripcion`,`anio`,`precio`,`disponible`) VALUES ("+"'"+vehiculo.getMarca()+"'"+","+"'"+vehiculo.getModelo()+"'"+","+"'"+vehiculo.getDescripcion()+"'"+","+"'"+vehiculo.getAnio()+"'"+","+"'"+vehiculo.getPrecio()+"'"+",+1)";

        Statement stmt = BDconexion.getConexion().createStatement();
        stmt.executeUpdate (sql);
        BDconexion.CloseConnection();
        System.out.println("\nSe guardo el vehiculo correctamente");

    }

    public void listarVehiculos(boolean disponible) throws SQLException {

        ArrayList<Vehiculo> vehiculos = new ArrayList<>();
        String sql;

        if (disponible == true){
            sql = "SELECT * FROM vehiculos WHERE disponible = 1";
        } else{
            sql = "SELECT * FROM vehiculos WHERE disponible = 0";
        }

        Statement stmt = BDconexion.getConexion().createStatement();
        ResultSet resultSet = stmt.executeQuery(sql);

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String marca = resultSet.getString("marca");
            String modelo = resultSet.getString("modelo");
            String descripcion = resultSet.getString("descripcion");
            String anio = resultSet.getString("anio");
            double precio = resultSet.getDouble("precio");

            Vehiculo vehiculo1 = new Vehiculo(id,marca, modelo, descripcion, anio, precio);
            vehiculos.add(vehiculo1);
        }

        System.out.print(String.format("| %-4s | %-15s | %-15s | %-30s | %-8s | %-12s |\n",
                "id", "Marca", "Modelo", "Descripcion", "Año", "Precio"));
        System.out.println("-----------------------------------------------------------------------------------------------------------------");

        for (Vehiculo vehiculo1 : vehiculos) {
            System.out.println(vehiculo1);
        }
        BDconexion.CloseConnection();
    }

    public int obtenerIDultimoVehiculo() throws SQLException {
        String sql = "SELECT MAX(id) FROM vehiculos;";
        Statement stmt = BDconexion.getConexion().createStatement();
        ResultSet resultSet = stmt.executeQuery(sql);
        resultSet.next();
        return resultSet.getInt(1);
    }

    @Override

    public String toString() {
        return String.format("| %-4s | %-15s | %-15s | %-30s | %-8s | %-12s |",
                id, marca, modelo, descripcion, anio, precio);
    }

}

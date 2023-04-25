package Clases;

import ConecBD.BDconexion;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class Vendedor {

    private int id;
    private String nombre;
    private String apellido;
    private String fechaNacimiento;
    private String dni;

    public Vendedor() {
    }

    public Vendedor(String nombre, String apellido, String fechaNacimiento, String dni) {
        this.id = ++id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.dni = dni;
    }

    public Vendedor(int id, String nombre, String apellido, String fechaNacimiento, String dni) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.dni = dni;
    }

    public  int getId() {
        return id;
    }

    public  void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void agregarVendedor(Scanner escaner, Vendedor vendedor) throws SQLException {

        System.out.println("Ingrese el nombre: ");
        vendedor.setNombre(escaner.next());

        System.out.println("Ingrese el apellido: ");
        vendedor.setApellido(escaner.next());

        System.out.println("Ingrese la fecha de nacimiento (dd/mm/yyyy): ");
        vendedor.setFechaNacimiento(escaner.next());

        System.out.println("Ingrese el Dni");
        vendedor.setDni(escaner.next());

        String sql = "INSERT INTO `concesionaria`.`vendedores` (`nombre`,`apellido`,`fecha_nacimiento`,`dni`) VALUES ("+"'"+vendedor.getNombre()+"'"+","+"'"+vendedor.getApellido()+"'"+","+"'"+vendedor.getFechaNacimiento()+"'"+","+"'"+vendedor.getDni()+"'"+")";
        Statement stmt = BDconexion.getConexion().createStatement();
        stmt.executeUpdate (sql);
        BDconexion.CloseConnection();
        System.out.println("\nSe guardo el vendedor correctamente");
    }

    public void listarVendedores() throws SQLException {

        ArrayList<Vendedor> vendedores = new ArrayList<>();
        String sql = "SELECT * FROM vendedores";

        Statement stmt = BDconexion.getConexion().createStatement();
        ResultSet resultSet = stmt.executeQuery(sql);

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String nombre = resultSet.getString("nombre");
            String apellido = resultSet.getString("apellido");
            String fechaNacimiento = resultSet.getString("fecha_nacimiento");
            String dni = resultSet.getString("dni");

            Vendedor vendedor1 = new Vendedor(id, nombre, apellido, fechaNacimiento,dni);

            vendedor1.setId(resultSet.getInt("id"));
            vendedores.add(vendedor1);
        }

        System.out.print(String.format("| %-4s | %-15s | %-15s | %-12s | %-10s |\n",
                "id", "nombre", "apellido", "fecha Naci.", "dni"));
        System.out.println("----------------------------------------------------------------------");

        for (Vendedor vendedor1 : vendedores) {
            System.out.println(vendedor1);
        }
        BDconexion.CloseConnection();
    }

    @Override

    public String toString() {
        return String.format("| %-4s | %-15s | %-15s | %-12s | %-10s |",
                id, nombre, apellido, fechaNacimiento, dni);
    }

}

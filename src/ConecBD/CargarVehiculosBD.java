package ConecBD;

import Clases.Vehiculo;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class CargarVehiculosBD {
    public CargarVehiculosBD() {

        File archivo1 = new File("src/ConecBD/Vehiculos.txt");
        Scanner entrada = null;


        guardarVehiculosenBD(entrada,archivo1);
    }

    public static void guardarVehiculosenBD(Scanner entrada,File archivo){

        ArrayList<Vehiculo> vehiculos = new ArrayList<>();

        try {
            entrada = new Scanner(archivo);
            while (entrada.hasNext()){
                String cadena = entrada.nextLine();
                Vehiculo vehiculo =  cargarVehiculosEnVector(cadena,vehiculos);
            }

            for(Vehiculo vehiculo: vehiculos){

                String sql = "INSERT INTO `concesionaria`.`vehiculos` (`marca`,`modelo`,`descripcion`,`anio`,`precio`,`disponible`) VALUES ("+"'"+vehiculo.getMarca()+"'"+","+"'"+vehiculo.getModelo()+"'"+","+"'"+vehiculo.getDescripcion()+"'"+","+"'"+vehiculo.getAnio()+"'"+","+"'"+vehiculo.getPrecio()+"'"+","+vehiculo.getDisponible()+")";

                Statement stmt = BDconexion.getConexion().createStatement();
                stmt.executeUpdate (sql);
                stmt.close();
                BDconexion.CloseConnection();
            }


            FileWriter txt1 = new FileWriter("src/ConecBD/Vehiculos.txt");
            txt1.write("");
            txt1.close();


        } catch (FileNotFoundException e){
            System.out.println("Error con el archivo, no existe o no esta habilitado");
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (entrada !=null)
                entrada.close();
        }
    }

    public static Vehiculo cargarVehiculosEnVector(String cadena,  ArrayList<Vehiculo> vehiculos){

        String[] items = cadena.split(",");
        String marca = items[0];
        String modelo = items[1];
        String descripcion = items[2];
        String anio = items[3];
        double precio = Double.parseDouble(items[4]);
        int disponible = Integer.parseInt(items[5]);

        Vehiculo vehiculo = new Vehiculo(marca,modelo,descripcion,anio,precio,disponible);
        vehiculos.add(vehiculo);

        return vehiculo;
    }
}

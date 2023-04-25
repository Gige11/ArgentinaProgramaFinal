package Menu;

import Clases.*;
import ConecBD.CargarVehiculosBD;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {


        CargarVehiculosBD cargarVehiculosBD = new CargarVehiculosBD();

        Scanner escaner = new Scanner(System.in);
        int opcion;

       do {

           System.out.println("  Concesionaria");
           System.out.println("-----------------");
           System.out.println("0- Salir");
           System.out.println("1- Registrar cliente");
           System.out.println("2- Registrar vendedor");
           System.out.println("3- Registrar vehiculo");
           System.out.println("4- Mostrar clientes");
           System.out.println("5- Mostrar vendedores");
           System.out.println("6- Mostrar vehiculos disponibles");
           System.out.println("7- Mostrar vehiculos vendidos");
           System.out.println("8- Registrar compra");
           System.out.println("9- Registrar venta");
           System.out.println("10- Mostrar compras realizadas");
           System.out.println("11- Mostrar ventas realizadas");
           System.out.print("\nIngrese la opcion: ");

           opcion = escaner.nextInt();

           if (opcion <0 || opcion >11){
               System.out.println("\nTiene que ingresar una opcion valida. El programa se cerrara");
               opcion = 0;
           }

           System.out.println("\n");

           switch (opcion){

               case 1:

                   Cliente cliente = new Cliente();
                   cliente.agregarCliente(escaner,cliente);
                   opcion = Continuar(escaner,opcion);
                   break;

               case 2:

                   Vendedor vendedor = new Vendedor();
                   vendedor.agregarVendedor(escaner,vendedor);
                   opcion = Continuar(escaner,opcion);
                   break;

               case 3:

                   Vehiculo vehiculo = new Vehiculo();
                   vehiculo.agregarVehiculo(escaner,vehiculo);
                   opcion = Continuar(escaner,opcion);
                   break;

               case 4:

                   Cliente cliente1 = new Cliente();
                   cliente1.listarClientes();
                   opcion = Continuar(escaner,opcion);
                   break;

               case 5:

                   Vendedor vendedor1 = new Vendedor();
                   vendedor1.listarVendedores();
                   opcion = Continuar(escaner,opcion);
                   break;

               case 6:

                   Vehiculo vehiculo1 = new Vehiculo();
                   vehiculo1.listarVehiculos(true);
                   opcion = Continuar(escaner,opcion);
                   break;

               case 7:

                   Vehiculo vehiculo2 = new Vehiculo();
                   vehiculo2.listarVehiculos(false);
                   opcion = Continuar(escaner,opcion);
                   break;

               case 8:

                   Compra compra = new Compra();

                   System.out.println("DATOS DEL VEHICULO A COMPRAR");
                   Vehiculo vehiculo3 = new Vehiculo();
                   vehiculo3.agregarVehiculo(escaner,vehiculo3);
                   int vehic3 = vehiculo3.obtenerIDultimoVehiculo();
                   System.out.println("\n");

                   //*************************************************************************************************//

                   Cliente cliente2 = new Cliente();

                   System.out.println("DATOS DEL DUEÑO DEL VEHICULO");
                   System.out.println("\n");
                   System.out.println("1- usar datos existente | 2- guardar nuevo datos");

                   int eleccion = escaner.nextInt();

                   int client2 = 0;

                   if(eleccion == 1){
                       cliente2.listarClientes();
                       System.out.println("\n");
                       System.out.print("Ingrese el numero correspondiente al vendedor : ");
                       client2 = escaner.nextInt();
                       System.out.println("\n");
                   } else if (eleccion == 2) {
                       cliente2.agregarCliente(escaner,cliente2);
                       client2 = cliente2.obtenerIDultimoCliente();
                   }else{
                       System.out.println("El numero ingresado no es correcto, el programa se cerrara");
                       opcion = 0;
                   }

                   //*************************************************************************************************//

                   compra.registrarCompra(escaner,client2,vehic3);
                   System.out.println("\n");

                   opcion = Continuar(escaner,opcion);

                   break;

               case 9:

                   Venta venta = new Venta();

                   //*************************************************************************************************//

                   Vehiculo vehiculo4 = new Vehiculo();
                   vehiculo4.listarVehiculos(true);
                   System.out.println("\n");
                   System.out.print("Ingrese el numero correspondiente al vehiculo a vender: ");
                   int vehic2 = escaner.nextInt();
                   System.out.println("\n");

                   //*************************************************************************************************//

                   Cliente cliente3 = new Cliente();

                   System.out.println("1- usar cliente existente | 2- guardar nuevo cliente");
                   int eleccion2 = escaner.nextInt();
                   int client3 = 0;

                   if(eleccion2 == 1){
                       cliente3.listarClientes();
                       System.out.println("\n");
                       System.out.print("Ingrese el numero correspondiente al cliente elegido: ");
                       client3 = escaner.nextInt();
                       System.out.println("\n");
                   } else if (eleccion2 == 2) {
                       cliente3.agregarCliente(escaner,cliente3);
                       client3 = cliente3.obtenerIDultimoCliente();
                   }else{
                       System.out.println("El numero ingresado no es correcto, el programa se cerrara");
                       opcion = 0;
                   }

                   //*************************************************************************************************//

                   Vendedor vendedor2 = new Vendedor();
                   System.out.println("\n");
                   vendedor2.listarVendedores();
                   System.out.println("\n");
                   System.out.print("Ingrese el numero correspondiente al vendedor: ");
                   int vended = escaner.nextInt();
                   System.out.println("\n");

                   venta.registrarVenta(escaner,vehic2,vended,client3);
                   System.out.println("\n");

                   opcion = Continuar(escaner,opcion);

                   break;

               case 10:

                   Compra compra2 = new Compra();
                   compra2.listarCompras();
                   opcion = Continuar(escaner,opcion);
                   break;

               case 11:

                   Venta venta1 = new Venta();
                   venta1.listarVentas();
                   opcion = Continuar(escaner,opcion);
                   break;
           }

       }while (opcion != 0);
    }

    public static int Continuar(Scanner escaner, int opcion){
        System.out.println("\n Desea realizar otra operacion? y/n");
        String seguir = escaner.next();
        if(seguir.equals("n")){
            opcion = 0;
        }
        return  opcion;
    }
}

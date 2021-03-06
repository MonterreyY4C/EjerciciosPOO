import java.util.Scanner;
/**
 * Clase que implementa el menú para operar el arreglo de clientes.
 * @author Diego Puebla Aldama
 * @version 2.0
 */
public class AplicacionCliente {

    /**
     * Método principal
     */
    public static void main(String[] args) {
        
        //Definición del arreglo de clientes.
        ArregloCliente oArreglo; 
        Scanner oScanner = new Scanner(System.in);
        int eIndice, eSeleccion, eTamanio, eOpcion = 0;
        String sNombre, sRFC, sDomicilio, sParametro, sConfirmacion;

        //Inicio de la interacción.
        System.out.println("Aplicación de arreglo de clientes: ");
        System.out.println("Cuántos clientes será el máximo a registrar: ");
        eTamanio = oScanner.nextInt();
        //Crea el arreglo del tamaño que el usuario indicó.
        oArreglo = new ArregloCliente(eTamanio);
        System.out.println("Arreglo de Clientes creado.");

        //Menú
        do {

            System.out.println("Menú\n1)Agregar Cliente\n2)Borrar Cliente\n3)Modificar Cliente\n4)Consultar Cliente\n5)Listar Clientes\n6)Salir");
            eOpcion = oScanner.nextInt();

            switch (eOpcion) {
                case 1: //Agregar
                    if (oArreglo.arregloLleno()) {
                        System.out.println("Error. No se pueden agregar más clientes, overflow.");
                    } else {
                        oScanner.nextLine();
                        System.out.println("Ingrese el nombre de la persona: ");
                        sNombre = oScanner.nextLine();
                        System.out.println("Ingrese el rfc de la persona: ");
                        sRFC = oScanner.nextLine();
                        System.out.println("Ingrese el domicilio de la persona: ");
                        sDomicilio = oScanner.nextLine();
                        oArreglo.agregarCliente(new Cliente(sNombre, sRFC, sDomicilio));
                        System.out.println("Se ha agregado el cliente " + sNombre + " con éxito.");
                    }  
                    break;

                case 2: //Borrar
                    oScanner.nextLine();
                    System.out.println("Ingrese el nombre del cliente a borrar: ");
                    sNombre = oScanner.nextLine();
                    eIndice = oArreglo.buscarBinario(sNombre);
                    if (eIndice == -1) {
                        System.out.println("Error. El cliente " + sNombre + " no existe.");
                    } else {
                        System.out.println("¿Está seguro de querer borrar al cliente " + sNombre + "?");
                        sConfirmacion = oScanner.nextLine();
                        if (sConfirmacion.toLowerCase().compareTo("si") == 0) {
                            oArreglo.borrarCliente(eIndice);
                            System.out.println("Se ha borrado el cliente " + sNombre + " con éxito.");
                        }
                    }
                    break;

                case 3: //Modificar por nombre
                    oScanner.nextLine();
                    System.out.println("Ingrese el nombre del cliente a modificar: ");
                    sNombre = oScanner.nextLine();
                    eIndice = oArreglo.buscarBinario(sNombre);
                    if (eIndice == -1) {
                        System.out.println("Error. El cliente " + sNombre + " no existe.");
                    } else {
                        System.out.println("Elige el atrubuto a modificar:\n1) Nombre\n2) RFC\n3) Domicilio");
                        eSeleccion = oScanner.nextInt();
                        oScanner.nextLine();
                        System.out.println("Ingresa el nuevo valor del atributo: ");
                        sParametro = oScanner.nextLine();
                        oArreglo.modificarCliente(sNombre, eSeleccion, sParametro);
                    }
                    break;

                case 4: //Consultar
                    oScanner.nextLine();
                    System.out.println("Ingrese el nombre del cliente: ");
                    sNombre = oScanner.nextLine();
                    eIndice = oArreglo.buscarBinario(sNombre);
                    if (eIndice == -1) {
                        System.out.println("Error. El cliente " + sNombre + " no existe.");
                    } else {
                        sNombre = oScanner.nextLine();
                        System.out.println(oArreglo.consultarCliente(sNombre));
                    }
                    break;

                case 5: //Listar
                    oArreglo.listarCliente();
                    break;
            
                default:
                    System.out.println("Hasta luego usuario!");
                    break;
            }

        } while (eOpcion != 6);

        oScanner.close();
    }
}

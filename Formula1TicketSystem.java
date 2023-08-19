import java.util.*;

class Localidad {
    private String nombre;
    private int precio;
    private int capacidad;
    private int boletosVendidos;

    public Localidad(String nombre, int precio, int capacidad) {
        this.nombre = nombre;
        this.precio = precio;
        this.capacidad = capacidad;
        this.boletosVendidos = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPrecio() {
        return precio;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public int getBoletosVendidos() {
        return boletosVendidos;
    }

    public void venderBoletos(int cantidad) {
        boletosVendidos += cantidad;
    }
}

class Comprador {
    private String nombre;
    private String dpi;
    private int boletosDeseados;
    private int presupuestoMaximo;

    public Comprador(String nombre, String dpi, int boletosDeseados, int presupuestoMaximo) {
        this.nombre = nombre;
        this.dpi = dpi;
        this.boletosDeseados = boletosDeseados;
        this.presupuestoMaximo = presupuestoMaximo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDpi() {
        return dpi;
    }

    public int getBoletosDeseados() {
        return boletosDeseados;
    }

    public int getPresupuestoMaximo() {
        return presupuestoMaximo;
    }
}

public class Formula1TicketSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Localidad localidad1 = new Localidad("Localidad 1", 300, 20);
        Localidad localidad5 = new Localidad("Localidad 5", 565, 20);
        Localidad localidad10 = new Localidad("Localidad 10", 1495, 20);

        Comprador compradorActual = null;

        while (true) {
            System.out.println("Bienvenido al sistema de compra de boletos de Fórmula 1");
            System.out.println("1. Nuevo comprador");
            System.out.println("2. Nueva solicitud de boletos");
            System.out.println("3. Consultar disponibilidad total");
            System.out.println("4. Consultar disponibilidad individual");
            System.out.println("5. Reporte de caja");
            System.out.println("6. Salir");

            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese nombre del comprador: ");
                    String nombre = scanner.next();
                    System.out.print("Ingrese DPI del comprador: ");
                    String dpi = scanner.next();
                    System.out.print("Ingrese cantidad de boletos deseados: ");
                    int boletosDeseados = scanner.nextInt();
                    System.out.print("Ingrese presupuesto máximo: ");
                    int presupuestoMaximo = scanner.nextInt();
                    compradorActual = new Comprador(nombre, dpi, boletosDeseados, presupuestoMaximo);
                    break;
                case 2:
                    if (compradorActual == null) {
                        System.out.println("Primero debe ingresar los detalles del comprador.");
                    } else {
                        int ticket = (int) (Math.random() * 28000) + 1;
                        int a = (int) (Math.random() * 15000) + 1;
                        int b = (int) (Math.random() * 15000) + 1;
                        
                        if ((ticket + a + b) % 2 == 1) {
                            Localidad localidadElegida = null;
                            int localidadAleatoria = (int) (Math.random() * 3) + 1;
                            
                            switch (localidadAleatoria) {
                                case 1:
                                    localidadElegida = localidad1;
                                    break;
                                case 2:
                                    localidadElegida = localidad5;
                                    break;
                                case 3:
                                    localidadElegida = localidad10;
                                    break;
                            }
                            
                            if (localidadElegida != null) {
                                int boletosAVender = Math.min(localidadElegida.getCapacidad() - localidadElegida.getBoletosVendidos(), compradorActual.getBoletosDeseados());
                                
                                if (boletosAVender > 0) {
                                    int costoTotal = boletosAVender * localidadElegida.getPrecio();
                                    if (costoTotal <= compradorActual.getPresupuestoMaximo()) {
                                        localidadElegida.venderBoletos(boletosAVender);
                                        System.out.println("¡Boletos vendidos exitosamente!");
                                    } else {
                                        System.out.println("El costo total de los boletos excede el presupuesto máximo del comprador.");
                                    }
                                } else {
                                    System.out.println("No hay suficientes boletos disponibles en la localidad elegida.");
                                }
                            }
                        } else {
                            System.out.println("El ticket no es apto para comprar boletos.");
                        }
                    }
                    break;
                case 3:
                System.out.println("Disponibilidad total:");
                System.out.println(localidad1.getNombre() + ": Vendidos - " + localidad1.getBoletosVendidos() + ", Disponibles - " + (localidad1.getCapacidad() - localidad1.getBoletosVendidos()));
                System.out.println(localidad5.getNombre() + ": Vendidos - " + localidad5.getBoletosVendidos() + ", Disponibles - " + (localidad5.getCapacidad() - localidad5.getBoletosVendidos()));
                System.out.println(localidad10.getNombre() + ": Vendidos - " + localidad10.getBoletosVendidos() + ", Disponibles - " + (localidad10.getCapacidad() - localidad10.getBoletosVendidos()));
                break;
                
                case 4:
                System.out.println("Ingrese el número de la localidad (1, 5 o 10):");
                int numeroLocalidad = scanner.nextInt();
                Localidad localidadSeleccionada = null;
                
                switch (numeroLocalidad) {
                    case 1:
                        localidadSeleccionada = localidad1;
                        break;
                    case 5:
                        localidadSeleccionada = localidad5;
                        break;
                    case 10:
                        localidadSeleccionada = localidad10;
                        break;
                    default:
                        System.out.println("Localidad no válida.");
                        break;
                }
                case 5:
                    int totalRecaudado = (localidad1.getBoletosVendidos() * localidad1.getPrecio()) +
                         (localidad5.getBoletosVendidos() * localidad5.getPrecio()) +
                         (localidad10.getBoletosVendidos() * localidad10.getPrecio());
                    System.out.println("Total recaudado: $" + totalRecaudado);
                    break;
                    
                case 6:
                    System.out.println("Gracias por usar el sistema. ¡Hasta luego!");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, ingrese una opción válida.");
            }
        }
    }
}

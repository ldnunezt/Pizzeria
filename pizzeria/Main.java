import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Orden orden = new Orden();
        Cocina cocina = new Cocina();

        System.out.print("¿Cuántas pizzas desea ordenar? (1 a 10): ");
        int cantidad = Integer.parseInt(scanner.nextLine());

        if (cantidad < 1 || cantidad > 10) {
            System.out.println("Debe ingresar una cantidad entre 1 y 10.");
            return;
        }
        for (int i = 0; i < cantidad; i++) {
            System.out.println("\nPizza " + (i + 1));
            orden.crearOrden();
        }
        Pizza[] pizzasDeLaOrden = new Pizza[orden.getCantidadPizzas()];

        for (int i = 0; i < orden.getCantidadPizzas(); i++) {
            pizzasDeLaOrden[i] = orden.getPizza(i);
        }

        cocina.recibirOrden(pizzasDeLaOrden);
        cocina.cocina();

        int pizzasRecibidas = Math.min(orden.getCantidadPizzas(), 5);

        for (int i = 0; i < pizzasRecibidas; i++) {
            cocina.DarPizza(orden.getPizza(i));
        }
    }
}
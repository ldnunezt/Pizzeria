import java.util.Scanner;

public class Orden {
    private Pizza[] pizzas = new Pizza[10];
    private int cantidadPizzas = 0;
    private Scanner scanner = new Scanner(System.in);

    public Orden() {
    }

    public Orden(Pizza[] pizzas) {
        this.pizzas = pizzas;
        this.cantidadPizzas = pizzas.length;
    }

    public void crearOrden() {
        if (cantidadPizzas >= pizzas.length) {
            System.out.println("La orden ya tiene el máximo de 10 pizzas.");
            return;
        }

        System.out.println("Ingrese su pizza deseada");

        System.out.print("Base: ");
        String base = scanner.nextLine();

        System.out.print("Salsa: ");
        String salsa = scanner.nextLine();

        System.out.print("¿Orilla rellena? Responda con si o no por favor: ");
        boolean orillaRellena = scanner.nextLine().trim().equalsIgnoreCase("si");

        Ingredientes[] ingredientes = new Ingredientes[10];

        Pizza pizza = new Pizza();
        pizza.ensamblarPizza(ingredientes, base, salsa, orillaRellena);

        pizzas[cantidadPizzas] = pizza;
        cantidadPizzas++;

        System.out.println("Pizza agregada a la orden. Total: " + cantidadPizzas);
    }
    public int getCantidadPizzas() {
    return cantidadPizzas;
}

public Pizza getPizza(int posicion) {
    return pizzas[posicion];
}
}

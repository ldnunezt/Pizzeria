public class Cocina {
    String cocineros;
    Pizza[] pizzascreadas = new Pizza[5];
    Orden ordenesRec;

    private int cantidadPedidos = 0;

    public Cocina() {
    }

    public void cocina() {
        for (int i = 0; i < cantidadPedidos; i++) {
            pizzascreadas[i].cocinarPizza();
        }
    }

    public void recibirOrden(Pizza[] pizzas) {
        for (Pizza pizza : pizzas) {
            if (pizza == null) {
                continue;
            }

            if (cantidadPedidos >= 5) {
                System.out.println("mas de 5 ordenes, no es posible recibir");
                break;
            }

            pizzascreadas[cantidadPedidos] = pizza;
            cantidadPedidos++;
        }
    }

    public Pizza DarPizza(Pizza pizza) {
        for (int i = 0; i < cantidadPedidos; i++) {
            if (pizzascreadas[i] == pizza) {
                pizza.servirPizza();

                pizzascreadas[i] = pizzascreadas[cantidadPedidos - 1];
                pizzascreadas[cantidadPedidos - 1] = null;
                cantidadPedidos--;

                return pizza;
            }
        }

    System.out.println("No se encontró esa pizza.");
    return null;
}
}

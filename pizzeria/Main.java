import java.awt.*;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> crearVentana());
    }

    public static void crearVentana() {
        JFrame frame = new JFrame("Pizzería");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(10, 10));

        Pizza[] pizzasPendientes = new Pizza[10];
        int[] cantidadPizzas = {0};

        JPanel panelIngredientes = new JPanel(new GridLayout(2, 4, 5, 5));
        panelIngredientes.setBorder(
            BorderFactory.createTitledBorder("Elige los ingredientes")
        );

        Ingredientes[] opciones = Ingredientes.values();
        JToggleButton[] botonesIngredientes = new JToggleButton[opciones.length];

        for (int i = 0; i < opciones.length; i++) {
            botonesIngredientes[i] = new JToggleButton(opciones[i].name());
            panelIngredientes.add(botonesIngredientes[i]);
        }

        frame.add(panelIngredientes, BorderLayout.NORTH);

        JTextArea resumen = new JTextArea(12, 40);
        resumen.setEditable(false);
        resumen.setText("Arma tu pizza y presiona «Agregar pizza».\n");
        frame.add(new JScrollPane(resumen), BorderLayout.CENTER);

        JPanel formulario = new JPanel(new GridLayout(4, 2, 8, 8));
        formulario.setBorder(
            BorderFactory.createTitledBorder("Configura tu pizza")
        );

        JTextField campoMasa = new JTextField();
        JTextField campoSalsa = new JTextField();

        JRadioButton orillaNormal = new JRadioButton("Normal", true);
        JRadioButton orillaRellena = new JRadioButton("Rellena");

        ButtonGroup grupoOrilla = new ButtonGroup();
        grupoOrilla.add(orillaNormal);
        grupoOrilla.add(orillaRellena);

        JPanel panelOrilla = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelOrilla.add(orillaNormal);
        panelOrilla.add(orillaRellena);

        JButton agregarPizza = new JButton("Agregar pizza");
        JButton cocinarYEntregar = new JButton("Cocinar y entregar");

        formulario.add(new JLabel("Tipo de masa:"));
        formulario.add(campoMasa);

        formulario.add(new JLabel("Tipo de salsa:"));
        formulario.add(campoSalsa);

        formulario.add(new JLabel("Tipo de orilla:"));
        formulario.add(panelOrilla);

        formulario.add(agregarPizza);
        formulario.add(cocinarYEntregar);

        frame.add(formulario, BorderLayout.SOUTH);

        agregarPizza.addActionListener(e -> {
            if (cantidadPizzas[0] >= 10) {
                JOptionPane.showMessageDialog(
                    frame, "La orden solo puede tener 10 pizzas."
                );
                return;
            }

            String masa = campoMasa.getText().trim();
            String salsa = campoSalsa.getText().trim();

            if (masa.isEmpty() || salsa.isEmpty()) {
                JOptionPane.showMessageDialog(
                    frame, "Escribe el tipo de masa y de salsa."
                );
                return;
            }

            Ingredientes[] ingredientesElegidos = new Ingredientes[10];
            int cantidadIngredientes = 0;
            StringBuilder nombresIngredientes = new StringBuilder();

            for (int i = 0; i < botonesIngredientes.length; i++) {
                if (botonesIngredientes[i].isSelected()) {
                    ingredientesElegidos[cantidadIngredientes] = opciones[i];
                    cantidadIngredientes++;

                    if (nombresIngredientes.length() > 0) {
                        nombresIngredientes.append(", ");
                    }
                    nombresIngredientes.append(opciones[i].name());
                }
            }

            if (cantidadIngredientes == 0) {
                JOptionPane.showMessageDialog(
                    frame, "Selecciona al menos un ingrediente."
                );
                return;
            }

            Pizza pizza = new Pizza(
                ingredientesElegidos,
                masa,
                salsa,
                orillaRellena.isSelected()
            );

            pizzasPendientes[cantidadPizzas[0]] = pizza;
            cantidadPizzas[0]++;

            resumen.append(
                "\nPizza " + cantidadPizzas[0]
                + ": ingredientes [" + nombresIngredientes + "]"
                + ", masa " + masa
                + ", salsa " + salsa
                + ", orilla "
                + (orillaRellena.isSelected() ? "rellena" : "normal")
            );

            campoMasa.setText("");
            campoSalsa.setText("");
            orillaNormal.setSelected(true);

            for (JToggleButton boton : botonesIngredientes) {
                boton.setSelected(false);
            }
        });

        cocinarYEntregar.addActionListener(e -> {
            if (cantidadPizzas[0] == 0) {
                JOptionPane.showMessageDialog(
                    frame, "Primero agrega una pizza a la orden."
                );
                return;
            }

            Pizza[] pizzasDeLaOrden = new Pizza[cantidadPizzas[0]];

            for (int i = 0; i < cantidadPizzas[0]; i++) {
                pizzasDeLaOrden[i] = pizzasPendientes[i];
            }

            Orden orden = new Orden(pizzasDeLaOrden);
            Cocina cocina = new Cocina();

            resumen.append("\n\nProcesando orden de "
                + orden.getCantidadPizzas() + " pizza(s):\n");

            for (int inicio = 0;
                 inicio < orden.getCantidadPizzas();
                 inicio += 5) {

                int cantidadDelGrupo = Math.min(
                    5, orden.getCantidadPizzas() - inicio
                );

                Pizza[] grupo = new Pizza[cantidadDelGrupo];

                for (int i = 0; i < cantidadDelGrupo; i++) {
                    grupo[i] = orden.getPizza(inicio + i);
                }

                cocina.recibirOrden(grupo);
                cocina.cocina();

                for (Pizza pizza : grupo) {
                    Pizza pizzaEntregada = cocina.DarPizza(pizza);

                    if (pizzaEntregada != null) {
                        resumen.append("Pizza cocinada y entregada.\n");
                    }
                }
            }

            resumen.append("Orden terminada.\n");

            for (int i = 0; i < cantidadPizzas[0]; i++) {
                pizzasPendientes[i] = null;
            }
            cantidadPizzas[0] = 0;
        });

        frame.setSize(650, 550);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
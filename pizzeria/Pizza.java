public class Pizza {
    Ingredientes[] ingredientes = new Ingredientes[10];
    String base;
    String salsa;
    boolean orillaRellena;

    public Pizza() {
    }
    
    public Pizza(Ingredientes[] ingredientes, String base, String salsa, boolean orillaRellena) {
        ensamblarPizza(ingredientes, base, salsa, orillaRellena);
    }

    public void ensamblarPizza(Ingredientes[] ingredientes, String base, String salsa, boolean orillaRellena){
        this.ingredientes = ingredientes;
        this.base = base;
        this.salsa = salsa;
        this.orillaRellena = orillaRellena;
        System.out.println("se esta ensamblando");
    }



    public void cocinarPizza(){
        System.out.println("se está cocinando la pizza");
    }

    public void servirPizza(){
        System.out.println("Se está sirviendo la pizza");
    }


    
}

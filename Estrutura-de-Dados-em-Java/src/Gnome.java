public class Gnome {
    // Variaveis de instancia
    public String name;
    public int age;
    public Gnome gnomeBuddy;
    private boolean magical = false;
    protected double height = 2.6;
    public static final int MAX_HEIGHT = 3; // altura maxima
    
    // Construtores
    Gnome (String nm, int ag, Gnome bud, double hgt) { // totalmente parametrizado
        name = nm;
        age = ag;
        gnomeBuddy = bud;
        height = hgt;
    }
    Gnome () { // Constructor default
        name = "Rumple";
        age = 204;
        gnomeBuddy = null;
        height = 2.1;
    }
    
    // Metodos
    public static void makeKing (Gnome h) {
        h.name = "King " + h.getRealName();
        h.magical = true; // Apenas a classe Gnome pode referenciar este campo
    }
    public void makeMeKing () {
        name = "King " + getRealName();
        magical = true;
    }
    public boolean isMagical () {
        return magical;
    }
    public void setHeight (int newHeight) {
        height = newHeight;
    }
    public String getName () {
        return "I won't tell!";
    }
    public String getRealName () {
        return name;
    }
    public void renameGnome (String s) {
        name = s;
    }
}

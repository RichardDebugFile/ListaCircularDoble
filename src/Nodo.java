public class Nodo {
    int dato;
    Nodo siguiente;
    Nodo anterior;

    public Nodo(int dato) {
        this.dato = dato;
        this.siguiente = this.anterior = null;
    }
}

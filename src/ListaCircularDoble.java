import java.util.ArrayList;
import java.util.Collections;

public class ListaCircularDoble {
    private Nodo inicio;
    private Nodo fin;

    public ListaCircularDoble() {
        inicio = null;
        fin = null;
    }

    public void insertar(int dato) {
        Nodo nuevo = new Nodo(dato);
        if (inicio == null) {
            inicio = fin = nuevo;
            inicio.siguiente = inicio.anterior = inicio;
        } else {
            nuevo.anterior = fin;
            nuevo.siguiente = inicio;
            fin.siguiente = nuevo;
            inicio.anterior = nuevo;
            fin = nuevo;
        }
    }

    public boolean eliminar(int dato) {
        if (inicio == null) return false;

        Nodo actual = inicio;
        do {
            if (actual.dato == dato) {
                if (actual == inicio && actual == fin) {
                    inicio = fin = null;
                } else {
                    actual.anterior.siguiente = actual.siguiente;
                    actual.siguiente.anterior = actual.anterior;
                    if (actual == inicio) inicio = actual.siguiente;
                    if (actual == fin) fin = actual.anterior;
                }
                return true;
            }
            actual = actual.siguiente;
        } while (actual != inicio);
        return false;
    }

    public boolean buscar(int dato) {
        if (inicio == null) return false;

        Nodo actual = inicio;
        do {
            if (actual.dato == dato) return true;
            actual = actual.siguiente;
        } while (actual != inicio);
        return false;
    }

    public ArrayList<Integer> obtenerElementosOrdenados(boolean ascendente) {
        ArrayList<Integer> elementos = new ArrayList<>();
        if (inicio == null) return elementos;

        Nodo actual = inicio;
        do {
            elementos.add(actual.dato);
            actual = actual.siguiente;
        } while (actual != inicio);

        if (ascendente) {
            Collections.sort(elementos);
        } else {
            elementos.sort(Collections.reverseOrder());
        }
        return elementos;
    }
}

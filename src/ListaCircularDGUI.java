import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ListaCircularDGUI {
    private JPanel pGeneral;
    private JButton btnInsertar;
    private JButton btnEliminar;
    private JButton btnBuscar;
    private JButton btnOrdenarASC;
    private JTable tbDatos;
    private JTextField txtIngresar;
    private JButton btnOrdenarDES;

    private ListaCircularDoble lista; // Lista circular doble para manejar los datos
    private DefaultTableModel tableModel; // Modelo de la tabla

    public static void main(String[] args) {
        JFrame frame = new JFrame("ListaCircularDGUI");
        frame.setContentPane(new ListaCircularDGUI().pGeneral);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(1200, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public ListaCircularDGUI() {
        lista = new ListaCircularDoble(); // Inicializamos la lista circular doble
        inicializarTabla(); // Configuración inicial de la tabla

        // Acción para insertar un elemento
        btnInsertar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int dato = Integer.parseInt(txtIngresar.getText());
                    lista.insertar(dato);
                    actualizarTabla(lista.obtenerElementosOrdenados(true)); // Actualiza en orden ascendente
                    txtIngresar.setText(""); // Limpia el campo de texto
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Ingrese un número válido.");
                }
            }
        });

        // Acción para eliminar un elemento
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int dato = Integer.parseInt(txtIngresar.getText());
                    if (lista.eliminar(dato)) {
                        actualizarTabla(lista.obtenerElementosOrdenados(true));
                        txtIngresar.setText("");
                    } else {
                        JOptionPane.showMessageDialog(null, "El dato no existe en la lista.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Ingrese un número válido.");
                }
            }
        });

        // Acción para buscar un elemento
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int dato = Integer.parseInt(txtIngresar.getText());
                    if (lista.buscar(dato)) {
                        JOptionPane.showMessageDialog(null, "El dato existe en la lista.");
                    } else {
                        JOptionPane.showMessageDialog(null, "El dato no existe en la lista.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Ingrese un número válido.");
                }
            }
        });

        // Acción para ordenar en forma ascendente
        btnOrdenarASC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarTabla(lista.obtenerElementosOrdenados(true)); // Actualiza en orden ascendente
            }
        });

        // Acción para ordenar en forma descendente
        btnOrdenarDES.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarTabla(lista.obtenerElementosOrdenados(false)); // Actualiza en orden descendente
            }
        });
    }

    // Metodo para inicializar la tabla con un modelo predeterminado
    private void inicializarTabla() {
        tableModel = new DefaultTableModel(new Object[]{"Elementos"}, 0); // Una columna llamada "Elementos"
        tbDatos.setModel(tableModel); // Asigna el modelo a la tabla
    }

    // Metodo para actualizar los datos en la tabla
    private void actualizarTabla(ArrayList<Integer> elementos) {
        tableModel.setRowCount(0); // Limpia la tabla
        for (int elemento : elementos) {
            tableModel.addRow(new Object[]{elemento}); // Agrega cada elemento al modelo
        }
    }
}

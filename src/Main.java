
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

// Clase principal del videojuego Peces.
public class Main {

    public static void main(String[] args) {

        // Inicia la aplicación.
        SwingUtilities.invokeLater(() -> {

            // Crea la ventana principal.
            JFrame ventana = new JFrame("Peces");

            // Crea el nivel del videojuego.
            Nivel nivel = new Nivel();

            ventana.add(nivel);
            ventana.setSize(800, 500);
            ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            ventana.setLocationRelativeTo(null);
            ventana.setVisible(true);
        });
    }
}

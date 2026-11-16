
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import niveles.Nivel;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame ventana = new JFrame("Peces");
            ventana.add(new Nivel());
            ventana.setSize(800, 500);
            ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            ventana.setLocationRelativeTo(null);
            ventana.setVisible(true);
        });
    }
}

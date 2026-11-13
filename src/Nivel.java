
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

// Controla el escenario y el funcionamiento del nivel.
public class Nivel extends JPanel {

    private Pez rayo;
    private Pez globo;
    private Pez saltin;
    private Pez seleccionado;

    private Pulpo pulpo;
    private boolean pulpoDerrotado = false;

    // Constructor del nivel.
    public Nivel() {

        setBackground(new Color(30, 130, 190));
        setFocusable(true);

        crearPersonajes();

        pulpo = new Pulpo(550, 250);

        // Temporizador que actualiza el movimiento.
        Timer reloj = new Timer(16, e -> actualizar());
        reloj.start();

        // Selección de personajes mediante el teclado.
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {

                switch (e.getKeyCode()) {
                    case KeyEvent.VK_1:
                        crearPersonajes();
                        seleccionado = rayo;
                        break;

                    case KeyEvent.VK_2:
                        crearPersonajes();
                        seleccionado = globo;
                        break;

                    case KeyEvent.VK_3:
                        crearPersonajes();
                        seleccionado = saltin;
                        break;
                }

                repaint();
            }
        });

        // Disparo mediante un clic del ratón.
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if (!seleccionado.lanzado) {

                    double fuerzaX = (e.getX() - seleccionado.x) * 0.04;
                    double fuerzaY = (e.getY() - seleccionado.y) * 0.04;

                    seleccionado.lanzar(fuerzaX, fuerzaY);
                }
            }
        });
    }

    // Crea los tres personajes en la posición del cañón.
    private void crearPersonajes() {

        rayo = new Pez("Rayo", 130, 260, 8, 100, Color.YELLOW);
        globo = new Pez("Globo", 130, 260, 4, 100, Color.ORANGE);
        saltin = new Pez("Saltin", 130, 260, 6, 100, Color.GREEN);

        seleccionado = rayo;
    }

    // Actualiza el movimiento y comprueba los impactos.
    private void actualizar() {

        seleccionado.mover();

        // Comprueba si el pez golpeó al pulpo.
        if (!pulpoDerrotado
                && seleccionado.x > 520
                && seleccionado.x < 610
                && seleccionado.y > 210
                && seleccionado.y < 310) {

            pulpoDerrotado = true;
            seleccionado.lanzado = false;
        }

        // Detiene el pez al llegar al suelo.
        if (seleccionado.y > 410) {
            seleccionado.lanzado = false;
        }

        repaint();
    }

    // Dibuja el escenario y sus personajes.
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Suelo marino.
        g.setColor(new Color(190, 170, 100));
        g.fillRect(0, 440, getWidth(), 60);

        // Cañón submarino.
        g.setColor(Color.DARK_GRAY);
        g.fillRect(65, 250, 65, 25);
        g.fillOval(55, 245, 30, 35);

        // Personaje seleccionado.
        seleccionado.dibujar(g);

        // Pulpo inmóvil.
        if (!pulpoDerrotado) {
            pulpo.dibujar(g);
        }

        // Información del juego.
        g.setColor(Color.WHITE);
        g.drawString("PECES - Nivel submarino", 20, 25);
        g.drawString("1: Rayo   2: Globo   3: Saltin", 20, 50);
        g.drawString("Haz clic para disparar", 20, 75);

        if (pulpoDerrotado) {
            g.drawString("¡Pulpo derrotado!", 330, 100);
        }
    }
}


import java.awt.Color;
import java.awt.Graphics;

// Clase que representa a los tres peces del videojuego.
public class Pez {

    protected String nombre;
    protected double x, y;
    protected double velocidadX, velocidadY;
    protected int resistencia;
    protected Color color;
    protected boolean lanzado;

    private final int PIXEL = 5;

    // Constructor del personaje.
    public Pez(String nombre, int x, int y,
               int velocidad, int resistencia, Color color) {

        this.nombre = nombre;
        this.x = x;
        this.y = y;
        this.velocidadX = velocidad;
        this.velocidadY = 0;
        this.resistencia = resistencia;
        this.color = color;
        this.lanzado = false;
    }

    // Dibuja al personaje utilizando pixeles cuadrados.
    public void dibujar(Graphics g) {

        String[] sprite;

        switch (nombre) {

            case "Globo":
                sprite = new String[] {
                    "...S.S.S...",
                    "..SBBBBBS..",
                    ".SBBBBBBBS.",
                    "SBBBBBBBBBS",
                    "BBBBBBBBBBE",
                    "SBBBBBBBBBS",
                    ".SBBBBBBBS.",
                    "..SBBBBBS..",
                    "...S.S.S..."
                };
                break;

            case "Saltin":
                sprite = new String[] {
                    "...FFF......",
                    "..BBBBB.....",
                    "FBBBBBBBE...",
                    "..BBBBBB....",
                    "...BBBB.....",
                    "....FFF....."
                };
                break;

            default: // Rayo
                sprite = new String[] {
                    "...FFF......",
                    "..BBBBBB....",
                    "FBBBBBBBWE..",
                    "..BBBBBBBB..",
                    "...BBBB.....",
                    "....FFF....."
                };
                break;
        }

        for (int fila = 0; fila < sprite.length; fila++) {

            for (int columna = 0;
                 columna < sprite[fila].length(); columna++) {

                char pixel = sprite[fila].charAt(columna);

                switch (pixel) {

                    case 'B':
                        g.setColor(color);
                        break;

                    case 'F':
                        g.setColor(color.darker());
                        break;

                    case 'S':
                        g.setColor(Color.ORANGE);
                        break;

                    case 'W':
                        g.setColor(Color.WHITE);
                        break;

                    case 'E':
                        g.setColor(Color.BLACK);
                        break;

                    default:
                        continue;
                }

                g.fillRect(
                    (int)x + columna * PIXEL,
                    (int)y + fila * PIXEL,
                    PIXEL,
                    PIXEL
                );
            }
        }
    }

    // Lanza al personaje.
    public void lanzar(double fuerzaX, double fuerzaY) {
        velocidadX = fuerzaX;
        velocidadY = fuerzaY;
        lanzado = true;
    }

    // Actualiza la posición del personaje.
    public void mover() {
        if (lanzado) {
            velocidadY += 0.25;
            x += velocidadX;
            y += velocidadY;
        }
    }

    // Devuelve el nombre del personaje.
    public String getNombre() {
        return nombre;
    }
}

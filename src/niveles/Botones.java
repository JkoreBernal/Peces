
package niveles;

import java.awt.Color;
import java.awt.Graphics2D;

public class Botones {

    private final int ancho = 105, alto = 32, y = 12;
    private int repetirX, siguienteX;

    public void actualizarPosiciones(int anchoPanel) {
        repetirX = anchoPanel - 230;
        siguienteX = anchoPanel - 115;
    }

    private void dibujar(Graphics2D g, int x, String texto, Color color) {
        g.setColor(color);
        g.fillRoundRect(x, y, ancho, alto, 12, 12);
        g.setColor(Color.WHITE);
        g.drawRoundRect(x, y, ancho, alto, 12, 12);
        g.drawString(texto, x + 13, y + 21);
    }

    public void dibujar(Graphics2D g, int anchoPanel) {
        actualizarPosiciones(anchoPanel);
        dibujar(g, repetirX, "Repetir", new Color(35, 110, 170));
        dibujar(g, siguienteX, "Siguiente", new Color(30, 145, 85));
    }

    public int detectarClic(int mx, int my) {
        if (dentro(mx, my, repetirX)) return 1;
        if (dentro(mx, my, siguienteX)) return 2;
        return 0;
    }

    private boolean dentro(int mx, int my, int x) {
        return mx >= x && mx <= x + ancho
            && my >= y && my <= y + alto;
    }
}

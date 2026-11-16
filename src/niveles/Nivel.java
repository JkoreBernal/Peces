
package niveles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import peces.*;
import enemigos.Pulpo;

/**
 * Clase principal encargada de administrar la lógica del nivel.
 *
 * Coordina los personajes, enemigos, escenario, controles,
 * colisiones, habilidades y actualización gráfica del videojuego.
 *
 * También demuestra el uso de herencia y polimorfismo al trabajar
 * con objetos de las clases derivadas de Pez.
 */
public class Nivel extends JPanel {

    // Componentes encargados de administrar los elementos del escenario.
    private final Burbujas burbujas = new Burbujas();
    private final Algas algas = new Algas();
    private final Paredes paredes = new Paredes();
    private final Escenario escenario = new Escenario();
    private final Botones botones = new Botones();
    private final Camara camara = new Camara();

    // Instancias de las clases derivadas de Pez.
    private Anguila anguila;
    private Globo globo;
    private Betta betta;

    /**
     * Referencia de tipo Pez que permite trabajar con diferentes
     * objetos derivados de la clase base.
     *
     * Se utiliza polimorfismo para seleccionar y controlar
     * cualquiera de los personajes desde una misma referencia.
     */
    private Pez seleccionado;

    private Pulpo pulpo;
    private int nivel = 1, tiempo, fotogramas, rebote;
    private final int inicioX = 145, inicioY = 260, suelo = 410;
    private boolean derrotado, perdido, tiempoBala, ventana, habilidad;
    private String mensaje = "Selecciona un pez";

    public Nivel() {
        setFocusable(true);

        /**
         * Instanciación de las clases derivadas.
         *
         * Cada personaje se crea mediante su propio constructor,
         * heredando los atributos y comportamientos definidos en Pez.
         */
        anguila = new Anguila(inicioX, inicioY);
        globo = new Globo(inicioX, inicioY);
        betta = new Betta(inicioX, inicioY);

        // La referencia de tipo Pez almacena inicialmente una Anguila.
        seleccionado = anguila;

        crearEnemigo();
        new Timer(16, e -> actualizar()).start();

        // Administrador de controles del teclado.
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_1 -> seleccionar(anguila);
                    case KeyEvent.VK_2 -> seleccionar(globo);
                    case KeyEvent.VK_3 -> seleccionar(betta);
                    case KeyEvent.VK_Q -> activarHabilidad();
                    case KeyEvent.VK_R -> repetir();
                    case KeyEvent.VK_N -> { if (derrotado) { nivel++; repetir(); } }
                }
            }
        });

        // Administrador de interacciones con el mouse.
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                requestFocusInWindow();
                int x = e.getX(), y = e.getY();
                botones.actualizarPosiciones(getWidth());
                int boton = botones.detectarClic(x, y);
                if (boton == 1) { repetir(); return; }
                if (boton == 2) { if (derrotado) { nivel++; repetir(); } return; }
                if (seleccionado != null && !volando() && !derrotado && !perdido) {
                    seleccionado.lanzar((x - seleccionado.getX()) * .04, (y - seleccionado.getY()) * .04);
                    tiempoBala = ventana = true;
                    habilidad = false;
                    tiempo = 180;
                    fotogramas = rebote = 0;
                    camara.activar();
                    mensaje = "¡Lanzamiento!";
                }
            }
        });
    }

    @Override public void addNotify() {
        super.addNotify();
        SwingUtilities.invokeLater(this::requestFocusInWindow);
    }

    /**
     * Consulta el estado de movimiento del personaje seleccionado.
     * Utiliza el método heredado isLanzado() de la clase Pez.
     */
    private boolean volando() { return seleccionado != null && seleccionado.isLanzado(); }

    /**
     * Recibe una referencia de tipo Pez para seleccionar cualquier
     * personaje derivado sin necesitar un método diferente para cada uno.
     */
    private void seleccionar(Pez pez) { if (!volando() && !derrotado && !perdido) seleccionado = pez; }

    private void crearEnemigo() {
        int x = Math.min(470 + (nivel - 1) % 4 * 15, Math.max(300, getWidth() - 110));
        pulpo = new Pulpo(x, 250);
        derrotado = perdido = false;
    }

    /**
     * Activa la habilidad correspondiente según el tipo de personaje.
     *
     * Aunque los personajes comparten la clase base Pez,
     * cada clase derivada proporciona sus propios métodos especializados.
     */
    private void activarHabilidad() {
        if (!ventana || habilidad || !volando()) return;
        if (seleccionado == anguila) { anguila.electrocutar(); mensaje = "¡DESCARGA ELECTRICA!"; comprobarDescarga(); }
        else if (seleccionado == globo) { globo.inflar(); mensaje = "¡GLOBO EXPANDIDO!"; }
        else { betta.activarRebote(); rebote = 180; mensaje = "¡REBOTE ACTIVADO!"; }
        habilidad = true;
        ventana = false;
    }

    private void comprobarDescarga() {
        if (Math.hypot(pulpo.getX() - anguila.getX() - 20, pulpo.getY() - anguila.getY() + 10) <= 90) terminar(true);
    }

    private boolean colision() { return pulpo.colisiona(seleccionado); }

    /**
     * Actualiza el estado del nivel y los objetos del videojuego.
     *
     * Utiliza los métodos heredados de Pez para actualizar el movimiento
     * y la posición del personaje seleccionado, independientemente
     * de cuál de las clases derivadas se encuentre activa.
     */
    private void actualizar() {
        burbujas.actualizar();
        camara.actualizar();
        if (tiempoBala && --tiempo <= 0) { tiempoBala = ventana = false; mensaje = "¡Continúa el vuelo!"; }
        if (rebote > 0 && --rebote == 0) betta.desactivarRebote();
        if (volando() && !derrotado && !perdido) {
            if (!tiempoBala || ++fotogramas % 3 == 0) seleccionado.mover();
            seleccionado.setX(Math.max(10, Math.min(seleccionado.getX(), getWidth() - 125)));
            if (seleccionado.getY() < 80) { seleccionado.setY(80); seleccionado.setVelocidadY(0); }
            if (seleccionado == anguila && anguila.tieneElectricidad()) comprobarDescarga();
            if (!derrotado && colision()) terminar(true);
            if (!derrotado && seleccionado.getY() >= suelo) {
                if (seleccionado == betta && betta.tieneRebote() && rebote > 0) {
                    seleccionado.setY(suelo);
                    seleccionado.setVelocidadY(betta.calcularRebote(seleccionado.getVelocidadY()));
                } else terminar(false);
            }
        }
        repaint();
    }

    private void terminar(boolean gano) {
        derrotado = gano;
        perdido = !gano;
        seleccionado.setLanzado(false);
        tiempoBala = ventana = false;
        terminarHabilidades();
        camara.reiniciar();
        mensaje = gano ? "¡PULPO DERROTADO!" : "¡PERDISTE!";
    }

    /**
     * Reinicia los objetos de las clases derivadas utilizando
     * el método reiniciar() heredado de Pez.
     *
     * Esto permite reutilizar el comportamiento de reinicio
     * definido en la clase base.
     */
    private void repetir() {
        anguila.reiniciar(inicioX, inicioY);
        globo.reiniciar(inicioX, inicioY);
        betta.reiniciar(inicioX, inicioY);
        seleccionado = anguila;
        tiempoBala = ventana = habilidad = false;
        tiempo = fotogramas = 0;
        terminarHabilidades();
        camara.reiniciar();
        crearEnemigo();
        mensaje = "Nivel " + nivel;
        requestFocusInWindow();
        repaint();
    }

    private void terminarHabilidades() {
        anguila.detenerElectricidad();
        globo.desinflar();
        betta.desactivarRebote();
        rebote = 0;
    }

    /**
     * Sobrescribe el método paintComponent() de JPanel.
     *
     * Se encarga de representar gráficamente el escenario,
     * los personajes, el enemigo y los elementos de interfaz.
     *
     * El personaje seleccionado utiliza dibujar() mediante
     * una referencia de tipo Pez, permitiendo que cada clase
     * derivada ejecute su propia implementación sobrescrita.
     */
    @Override protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D fondo = (Graphics2D) g.create();
        fondo.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        escenario.dibujar(fondo, getWidth(), getHeight());
        Graphics2D mundo = (Graphics2D) fondo.create();
        camara.aplicar(mundo);
        burbujas.dibujar(mundo);
        algas.dibujarTodas(mundo, getWidth());
        paredes.dibujar(mundo, getWidth());
        mundo.setColor(Color.DARK_GRAY);
        mundo.fillRect(65, 250, 65, 25);
        mundo.fillOval(55, 245, 30, 35);
        if (seleccionado != null) seleccionado.dibujar(mundo);
        if (!derrotado) pulpo.dibujar(mundo);
        mundo.dispose();
        fondo.setColor(Color.WHITE);
        fondo.drawString("PECES - Aventura submarina", 22, 75);
        fondo.drawString("1: Anguila  2: Globo  3: Betta", 22, 95);
        fondo.drawString("Clic para lanzar", 22, 112);
        fondo.drawString("NIVEL " + nivel, 280, 32);
        fondo.drawString(mensaje, 22, 145);
        botones.dibujar(fondo, getWidth());
        if (ventana) {
            fondo.setColor(Color.YELLOW);
            fondo.drawString("¡PRESIONA Q PARA ACTIVAR TU HABILIDAD!", getWidth() / 2 - 155, 160);
            fondo.setColor(Color.WHITE);
            fondo.drawString("Tiempo: " + ((tiempo + 59) / 60) + " segundos", getWidth() / 2 - 55, 182);
        }
        if (derrotado || perdido) {
            fondo.drawString(derrotado ? "¡PULPO DERROTADO!" : "¡PERDISTE!", getWidth() / 2 - 85, 248);
            fondo.drawString(derrotado ? "Puedes repetir o avanzar" : "Presiona R para intentarlo otra vez", getWidth() / 2 - 105, 273);
        }
        fondo.dispose();
    }
}

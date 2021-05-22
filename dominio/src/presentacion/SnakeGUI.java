package presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

import aplicacion.*;

/**
 * Clase que construye y manipula la interfaz grafica del juego sNOOpe
 * @author Carlos Orduz
 * @author Felipe Giraldo
 * @version 1.0
 */
public class SnakeGUI extends JFrame implements ActionListener, KeyListener {

    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem nuevo, abrir, salvar, salvarComo, salir;
    private JLabel fondo;
    private JLabel score;
    private JPanel grillaBotones;
    private JButton jugar;
    private JPanel[][] cuadriculaSnake;
    private JButton menuPrincipal;
    private JLabel titulo;
    private JPanel principal;
    private JPanel juego;
    private JPanel elementos;
    private JPanel puntajes;
    private JPanel opcionesJuego;
    private final JRadioButton[] botonJugadores = new JRadioButton[2];
    private java.util.Timer timer;
    private Game game;
    private int players = 1;
    private String nombre1, nombre2;
    private boolean pausa;
    private TimerTask turno;

    /**
     * Constructor de la interfaz en donde preparamos los elementos graficos y las acciones
     */
    private SnakeGUI() {
        this.prepareElementos();
        this.prepareAcciones();
    }

    /**
     * metodo en la que se preparan los elementos graficos, el tamaño de la ventana y su posición.
     */
    private void prepareElementos() {
        setTitle("SnakeGame");
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        setSize((int) d.getWidth() / 2, (int) d.getHeight() / 2);
        setLocationRelativeTo(null);
        prepareElementosMenu();
        prepareElementosTablero();
        prepareElementosPrincipal();

    }

    /**
     * metodo en donde se preparan los elementos del menu principal
     */
    private void prepareElementosPrincipal() {
        setResizable(false);
        fondo = new JLabel();
        fondo.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("snake.jpg"))));
        fondo.setOpaque(false);
        fondo.setBounds(270, 10, 200, 70);
        titulo = new JLabel("SNAKE  \n" + "GAME");
        principal = new JPanel();
        principal.setLayout(new BorderLayout());
        principal.add(fondo, BorderLayout.CENTER);
        principal.add(titulo, BorderLayout.NORTH);
        add(principal);
        grillaBotones = new JPanel();
        Dimension dimension = new Dimension();
        dimension.setSize(40, 30);
        grillaBotones.setSize(dimension);
        grillaBotones.setLayout(new GridLayout(2, 1));
        grillaBotones.setLocation(50, 50);
        jugar = new JButton("Play");
        Dimension d2 = new Dimension();
        d2.setSize((int) dimension.getWidth() / 20, (int) dimension.getHeight() / 20);
        jugar.setSize(d2);
        grillaBotones.add(jugar);
        opcionesJuego = new JPanel();
        botonJugadores[0] = new JRadioButton();
        botonJugadores[1] = new JRadioButton();
        botonJugadores[0].setText("1 jugador");
        botonJugadores[1].setText("2 jugadores");
        opcionesJuego.add(botonJugadores[0]);
        botonJugadores[0].setSelected(true);
        opcionesJuego.add(botonJugadores[1]);
        ButtonGroup botonesDeNumeroDeJugadores = new ButtonGroup();
        botonesDeNumeroDeJugadores.add(botonJugadores[0]);
        botonesDeNumeroDeJugadores.add(botonJugadores[1]);
        grillaBotones.add(opcionesJuego);
        principal.add(grillaBotones, BorderLayout.SOUTH);
    }

    /**
     * metodo en donde se preparan los elementos del menu
     */
    private void prepareElementosMenu() {
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        menu = new JMenu("Menu");
        menuBar.add(menu);
        nuevo = new JMenuItem("Nuevo");
        menu.add(nuevo);
        abrir = new JMenuItem("Abrir");
        menu.add(abrir);
        salvar = new JMenuItem("Salvar");
        menu.add(salvar);
        salvarComo = new JMenuItem("Salvar Como");
        menu.add(salvarComo);
        salir = new JMenuItem("Salir");
        menu.add(salir);
    }

    /**
     * metodo en la que se preparan inicialmente los elementos del tablero
     */
    private void prepareElementosTablero() {
        juego = new JPanel();
        addKeyListener(this);
        setFocusable(true);
        elementos = new JPanel();
        elementos.setLayout(new GridLayout(10, 10));
        this.cuadriculaSnake = new JPanel[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {

                JPanel cuadrado = new JPanel();
                elementos.add(cuadrado);
                cuadriculaSnake[i][j] = cuadrado;
                cuadrado.setBackground(Color.black);
            }
        }
        menuPrincipal = new JButton("Main menu");
        menuPrincipal.setBackground(Color.orange);
        puntajes = new JPanel();
        puntajes.setLayout(new GridLayout(1, 2));
        score = new JLabel("Score :" + " " + "0");
        puntajes.setBackground(Color.ORANGE);
        puntajes.add(score);
        juego.setLayout(new BorderLayout());
        juego.add(elementos, BorderLayout.CENTER);
        juego.add(menuPrincipal, BorderLayout.SOUTH);
        juego.add(puntajes, BorderLayout.NORTH);
        add(juego);
    }

    /**
     * metodo que prepara las acciones posibles a realizar desde la interface
     */
    private void prepareAcciones() {
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ev) {
                close();
            }
        });
        prepareMenuAcciones();
        prepareMenuPrincipalAcciones();
        prepareJugarAcciones();
    }

    /**
     * metodo que prepara las acciones que se realizan en el menu principal
     */
    private void prepareMenuPrincipalAcciones() {
        jugar.addActionListener(this);
        botonJugadores[0].addActionListener(this);
        botonJugadores[1].addActionListener(this);
    }

    /**
     * metodo que prepara las acciones que se realizan al jugar
     */
    private void prepareJugarAcciones() {
        menuPrincipal.addActionListener(this);

    }

    /**
     * metodoq que prepara las acciones que se realizan en el menu
     */
    private void prepareMenuAcciones() {
        salir.addActionListener(this);
        abrir.addActionListener(this);
        salvar.addActionListener(this);
        menu.addActionListener(this);
    }

    /**
     * Metodo que detecta si un action event sucede y realiza la accion correspondiente a ese action event
     * @param e Action event correspondiente a la accion que se quiere realizar
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == salir) {
            close();
        } else if (e.getSource() == abrir) {
            abrirAccion();
        } else if (e.getSource() == salvar) {
            salvarAccion();
        } else if (e.getSource() == jugar) {
            jugarAccion();
        } else if (e.getSource() == menuPrincipal) {
            menuPrincipalAccion();
        } else if (e.getSource() == botonJugadores[0]) {
            players = 1;
        } else if (e.getSource() == botonJugadores[1]) {
            players = 2;
        }
    }

    /**
     * Metodo que ejecuta la accion menu principal, en esta actualizamos el jframe para volver al menu principal
     */
    private void menuPrincipalAccion() {
        game.getBoard().setGame(false);
        remove(juego);
        repaint();
        revalidate();
        add(principal);
        repaint();
        revalidate();
    }

    /**
     * Metodo que ejecuta la accion jugar, en esta iniciamos un juego con las configuraciones realizadas y creamos
     * el timer responsable de la ejecucion concurrente del juego y de actualizar los componentes graficos por medio
     * de refresque
     */
    private void jugarAccion() {
        String[] names = new String[2];
        pausa = false;
        game = new Game(players);
        if (players == 1) {
            nombre1 = JOptionPane.showInputDialog("Escribe tu nombre");
            names[0] = nombre1;
            game.setNames(names);
            Color color = JColorChooser.showDialog(null, "Seleccione un color", Color.GREEN);
            while (Color.BLACK.getRGB() == color.getRGB()) {
                JOptionPane.showMessageDialog(null, "No puede seleccionar ese color");
                color = JColorChooser.showDialog(null, "Seleccione un color", Color.GREEN);

            }
            game.getBoard().setSnakeColor(color, 1);
        } else {
            nombre1 = JOptionPane.showInputDialog("Escriba el nombre del jugador 1");
            names[0] = nombre1;
            Color color = JColorChooser.showDialog(null, "Seleccione un color para la serpiente 1", Color.GREEN);
            game.getBoard().setSnakeColor(color, 1);
            nombre2 = JOptionPane.showInputDialog("Escriba el nombre del jugador 2");
            names[1] = nombre2;
            game.setNames(names);
            Color color2 = JColorChooser.showDialog(null, "Seleccione un color para la serpiente 2", Color.GREEN);
            game.getBoard().setSnakeColor(color2, 2);
            while ((color.getRGB() == color2.getRGB()) || (Color.BLACK.getRGB() == color2.getRGB()) || Color.BLACK.getRGB() == color.getRGB()) {
                JOptionPane.showMessageDialog(null, "Seleccione colores distintos para cada serpiente");
                color = JColorChooser.showDialog(null, "Seleccione un color para la serpiente 1", Color.GREEN);
                game.getBoard().setSnakeColor(color, 1);
                color2 = JColorChooser.showDialog(null, "Seleccione un color para la serpiente 2", Color.GREEN);
                game.getBoard().setSnakeColor(color2, 2);
            }
        }
        remove(principal);
        repaint();
        revalidate();
        add(juego);
        repaint();
        revalidate();
        timer = new Timer();
        turno = new TimerTask() {
            @Override
            public void run() {
                if (!pausa) {
                    game.getBoard().turnS();
                    if (!game.getBoard().getStatus()) {
                        timer.cancel();
                        timer.purge();
                        if (players == 2) {
                            int[] points = game.getBoard().getScore();
                            if (points[0] > points[1]) {
                                JOptionPane.showMessageDialog(null, "GANA EL JUGADOR 1!");
                            } else if (points[0] < points[1]) {
                                JOptionPane.showMessageDialog(null, "GANA EL JUGADOR 2!");
                            } else {
                                JOptionPane.showMessageDialog(null, "EMPATE!");
                            }
                        }
                        JOptionPane.showMessageDialog(null, "GAME OVER");
                    }
                    refresque();
                }
            }
        };
        timer.schedule(turno, 0, 1000);
    }

    /**
     * Metodo que ejecuta la accion abrir, nos permite abrir un archivo .dat que contiene los datos guardados de una
     * partida
     */
    private void abrirAccion() {
        JFileChooser fileChooser = new JFileChooser();
        int opcion = fileChooser.showOpenDialog(abrir);
        if (opcion == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                this.game = game.open(file);
            } catch (ClassNotFoundException | SnakeException classNotFoundException) {
                classNotFoundException.printStackTrace();
            }
        }
    }

    /**
     * Metodo que ejecuta la accion salvar, nos permite guardar una partida como .dat
     */
    private void salvarAccion() {
        JFileChooser fileChooser = new JFileChooser();
        int opcion = fileChooser.showSaveDialog(null);
        if (opcion == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                this.game.save(file);
            } catch (SnakeException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Metodo que ejecuta la accion close, termina la ejecucion del programa
     */
    private void close() {
        if (JOptionPane.showConfirmDialog(rootPane, "Desea terminar el programa?", "Salir", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    /**
     * Metodo encargado de actualizar los elementos en el tablero de juego.
     */
    private void refresque() {
        remove(juego);
        repaint();
        revalidate();
        String[][] gameS = game.getBoard().readBoard();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (gameS[i][j].equals("rf")) {
                    RainbowFruit rF = (RainbowFruit) game.getBoard().getElement(i, j);
                    rF.nextColor();
                    cuadriculaSnake[i][j].setBackground(game.getBoard().getElement(i, j).getColor());
                } else if (gameS[i][j].equals("0") || game.getBoard().getElement(i, j) == null) {
                    cuadriculaSnake[i][j].setBackground(Color.black);
                } else {
                    cuadriculaSnake[i][j].setBackground(game.getBoard().getElement(i, j).getColor());
                }
            }
        }
        if (players == 1) {
            score.setText("Score de" + " " + game.getNames()[0] + " " + game.getBoard().getScore()[0] + ", Sorpresa de" + " " + game.getNames()[0] + " " + game.getBoard().getSorpresa()[0]);
        } else {// " Sorpresa de" + " "+ nombre + " " + game.getBoard().getSorpresa()[]
            score.setText("Score de" + " " + game.getNames()[0] + " " + game.getBoard().getScore()[0] + ", Sorpresa de" + " " + game.getNames()[0] + " " + game.getBoard().getSorpresa()[0] + "; " + "Score de" + " " + game.getNames()[1] + " " + game.getBoard().getScore()[1] + ", Sorpresa de" + " " + game.getNames()[1] + " " + game.getBoard().getSorpresa()[1]);
        }
        add(juego);
        repaint();
        revalidate();
    }


    public static void main(String[] args) {
        SnakeGUI gui = new SnakeGUI();
        gui.setVisible(true);
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * Metodo que detecta si un key event sucede y realiza la accion correspondiente a ese key event
     * @param e Action event correspondiente a la accion que se quiere realizar
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (KeyEvent.VK_P == key) {
            pausa = !pausa;
        }
        if (KeyEvent.VK_UP == key) {
            game.getBoard().move('u');
        } else if (KeyEvent.VK_DOWN == key) {
            game.getBoard().move('d');
        } else if (KeyEvent.VK_LEFT == key) {
            game.getBoard().move('l');
        } else if (KeyEvent.VK_RIGHT == key) {
            game.getBoard().move('r');
        } else if (KeyEvent.VK_SPACE == key) {
            game.getBoard().use(1);
        }
        if (players == 2) {
            if (KeyEvent.VK_W == key) {
                game.getBoard().move2('u');
            } else if (KeyEvent.VK_S == key) {
                game.getBoard().move2('d');
            } else if (KeyEvent.VK_A == key) {
                game.getBoard().move2('l');
            } else if (KeyEvent.VK_D == key) {
                game.getBoard().move2('r');
            } else if (KeyEvent.VK_E == key) {
                game.getBoard().use(2);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
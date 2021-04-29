package presentacion;

import org.w3c.dom.DOMImplementation;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import aplicacion.*;

public class SnakeGUI extends JFrame implements ActionListener {

    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem nuevo,abrir,salvar,salvarComo,salir,cambiarColor;
    private JLabel fondo;
    private JLabel score;
    private JLabel moves;
    private JPanel grillaBotones;
    private JButton jugar;
    private JPanel [][] cuadriculaSnake;
    private JButton scoreBoard;
    private JButton menuPrincipal;
    private JLabel titulo;
    private JPanel principal;
    private JPanel juego;
    private JPanel elementos;
    private JPanel puntajes;

    private Game game;

    private SnakeGUI() throws InterruptedException {
        this.prepareElementos();
        this.prepareAcciones();
    }

    private void prepareElementos() throws InterruptedException {
        setTitle("SnakeGame");
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        setSize((int) d.getWidth()/2,(int)d.getHeight()/2);
        setLocationRelativeTo(null);

        prepareElementosMenu();
        prepareElementosTablero();
        prepareElementosPrincipal();

    }
    private void prepareElementosPrincipal(){

        fondo = new JLabel();
        fondo.setIcon(new ImageIcon(getClass().getResource("snake.jpg")));
        fondo.setOpaque(false);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        fondo.setBounds(270,10,200,70);
        titulo = new JLabel("JEWEL  \n"+"QUEST");
        principal = new JPanel();
        principal.setLayout(new BorderLayout());
        principal.add(fondo, BorderLayout.CENTER);
        principal.add(titulo,BorderLayout.NORTH);
        add(principal);

        grillaBotones = new JPanel();
        Dimension dimension = new Dimension();
        dimension.setSize(40,30);
        grillaBotones.setSize(dimension);
        grillaBotones.setLayout(new GridLayout(1,1));
        grillaBotones.setLocation(50,50);
        jugar = new JButton("Play");
        Dimension d2 = new Dimension();
        d2.setSize((int)dimension.getWidth()/20,(int)dimension.getHeight()/20);
        jugar.setSize(d2);
        grillaBotones.add(jugar);

        principal.add(grillaBotones,BorderLayout.SOUTH);
    }
    private void prepareElementosMenu(){
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
        cambiarColor = new JMenuItem("Cambiar el color del fondo");
        menu.add(cambiarColor);
        salir = new JMenuItem("Salir");
        menu.add(salir);
    }
    private void prepareElementosTablero() throws InterruptedException {
        juego = new JPanel();
        elementos = new JPanel();
        elementos.setLayout(new GridLayout(10,10));
        this.cuadriculaSnake = new JPanel[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {

                JPanel cuadrado = new JPanel();
                elementos.add(cuadrado);
                cuadriculaSnake[i][j] = cuadrado;
                cuadrado.setBackground(Color.black);

            }
        }
        game = new Game(1,false);
        menuPrincipal = new JButton("Main menu");
        menuPrincipal.setBackground(Color.orange);
        puntajes = new JPanel();
        puntajes.setLayout(new GridLayout(1,2));
        score = new JLabel("Score :"+" "+"0");
        puntajes.setBackground(Color.ORANGE);
        puntajes.add(score);
        juego.setLayout(new BorderLayout());
        juego.add(elementos,BorderLayout.CENTER);
        juego.add(menuPrincipal,BorderLayout.SOUTH);
        juego.add(puntajes,BorderLayout.NORTH);
        add(juego);
    }

    private void prepareAcciones(){
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
    private void  prepareMenuPrincipalAcciones(){

        jugar.addActionListener(this);
    }
    private void  prepareJugarAcciones(){

        menuPrincipal.addActionListener(this);
    }
    private void prepareMenuAcciones(){
        salir.addActionListener(this);
        abrir.addActionListener(this);
        salvar.addActionListener(this);
        cambiarColor.addActionListener(this);
    }
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == salir){
            close();
        }else if(e.getSource() == abrir){
            abrirAccion();
        }else if(e.getSource() == salvar){
            salvarAccion();
        }
        else if (e.getSource() == jugar){
            jugarAccion();
        }
        else if (e.getSource() == menuPrincipal) {
            menuPrincipalAccion();
        }
        else if (e.getSource() == cambiarColor){
            cambiarColorAccion();
        }
    }
    private  void cambiarColorAccion(){
        JColorChooser sel = new JColorChooser();
        Color color = sel.showDialog(null, "Seleccione un color", Color.BLACK);
        elementos.setBackground(color);
        /*for (JButton joya: joyas){
            joya.setBorder(BorderFactory.createMatteBorder(10,30,10,30,color));
        }
        */

    }
    private void menuPrincipalAccion(){
        remove(juego);
        repaint();
        revalidate();
        add(principal);
        repaint();
        revalidate();
    }
    private void jugarAccion(){
        remove(principal);
        repaint();
        revalidate();
        add(juego);
        repaint();
        revalidate();
    }
    private void abrirAccion(){
        JFileChooser fileChooser = new JFileChooser();
        int opcion = fileChooser.showOpenDialog(abrir);
        if(opcion == JFileChooser.APPROVE_OPTION){
            File file = fileChooser.getSelectedFile();
            JOptionPane.showMessageDialog(null, "La funcionalidad de abrir esta en construccion, por lo tanto el archivo: "+file.getName()+" No se puede abrir");

        }
    }
    private void salvarAccion(){
        JFileChooser fileChooser = new JFileChooser();
        int opcion = fileChooser.showSaveDialog(null);
        if(opcion == JFileChooser.APPROVE_OPTION){
            File file = fileChooser.getSelectedFile();
            JOptionPane.showMessageDialog(null, "La funcionalidad de salvar esta en construccion, por lo tanto el archivo: "+file.getName()+" No se puede abrir");

        }
    }
    private void close(){
        if (JOptionPane.showConfirmDialog(rootPane,"Desea terminar el programa?","Salir",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
            System.exit(0);
        }
    }
    private void refresque() throws InterruptedException {
        prepareElementosTablero();
    }

    public static void main(String[] args) throws InterruptedException {
        SnakeGUI gui = new SnakeGUI();
        gui.setVisible(true);
    }
}
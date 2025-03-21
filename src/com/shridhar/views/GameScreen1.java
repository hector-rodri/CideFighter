package com.shridhar.views; //Paquete donde se encuentra la clase

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import com.shridhar.personalGame.GameFrame1;
import java.awt.event.*;

public class GameScreen1 extends JFrame { //Define la clase que hereda de JFrame

    private JPanel contentPane; //Panel principal de la ventana
    
    Timer timer; //Temporizador para la animación de carga
    int counter = 1; //Contador para la barra de progreso

    private void animation() { //Método para animar la barra de carga
        timer = new Timer(10, (e) -> { //Temporizador con intervalo de 10ms
            progressBar.setValue(counter); //Incrementa el valor de la barra de carga
            counter++;
            if (counter >= 100) { //Cuando llega al 100%
                this.setVisible(false); //Oculta la ventana actual
                this.dispose(); //Libera los recursos de la ventana
                timer.stop(); //Detiene el temporizador
                GameFrame1 game = new GameFrame1(); //Crea la ventana del juego
                game.setVisible(true); //Muestra la ventana del juego
            }
        });
        timer.start(); //Inicia la animación
    }

    private void help() { //Método para mostrar la pantalla de ayuda
        this.setVisible(false);
        this.dispose();
        HelpScreen help = new HelpScreen(); //Crea una nueva ventana de ayuda
        help.setVisible(true);
        help.setLocationRelativeTo(null); //Centra la ventana
    }

    private void customizePlayer() { //Método para personalizar el jugador
        this.setVisible(false);
        this.dispose();
        NameScreen customizeScreen = new NameScreen(); //Crea una nueva ventana de personalización
        customizeScreen.setVisible(true);
        customizeScreen.setLocationRelativeTo(null); //Centra la ventana
    }

    public static void main(String[] args) { //Método principal
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GameScreen1 frame = new GameScreen1(); //Crea la pantalla inicial
                    frame.setVisible(true); //Muestra la ventana
                    frame.setLocationRelativeTo(null); //Centra la ventana
                } catch (Exception e) {
                    e.printStackTrace(); //Muestra el error en consola
                }
            }
        });
    }

    JProgressBar progressBar = new JProgressBar(); //Barra de progreso

    public GameScreen1() { //Constructor de la pantalla inicial
        setTitle("CIDE FIGHTER"); //Título de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Cierra la aplicación al cerrar la ventana
        setBounds(100, 100, 370, 500); //Tamaño y posición de la ventana

        contentPane = new JPanel(); //Crea el panel principal
        contentPane.setBackground(new Color(232, 232, 232)); //Color de fondo
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5)); //Borde vacío
        setContentPane(contentPane);
        contentPane.setLayout(null); //Diseño absoluto

        //IMAGEN
        ImageIcon icono = new ImageIcon(getClass().getResource("/com/shridhar/personalGame/cide.png")); //Ruta de la imagen
        JLabel etiqueta = new JLabel(icono);
        etiqueta.setBounds(80, 5, 190, 190); //Ubicación y tamaño de la imagen
        contentPane.add(etiqueta);

        //BARRA DE CARGA
        progressBar.setBackground(new Color(190, 190, 190)); //Color de fondo
        progressBar.setStringPainted(true); //Muestra el porcentaje de carga
        progressBar.setBounds(35, 200, 278, 28); //Ubicación y tamaño
        progressBar.setVisible(false); //Oculta la barra de carga inicialmente
        contentPane.add(progressBar);

        //BOTÓN START
        JButton btnStartGame = new JButton("JUGAR"); //Botón para iniciar el juego
        btnStartGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                animation(); //Inicia la animación de carga
                progressBar.setVisible(true); //Muestra la barra de carga
            }
        });
        btnStartGame.setBackground(new Color(44, 150, 57)); //Color de fondo
        btnStartGame.setForeground(new Color(235, 235, 235)); //Color de letra
        btnStartGame.setFont(new Font("Tahoma", Font.BOLD, 28)); //Fuente y tamaño
        btnStartGame.setBounds(53, 237, 241, 55); //Ubicación y tamaño del botón
        contentPane.add(btnStartGame);

        //BOTÓN MI JUGADOR
        JButton btnCustomize = new JButton("JUGADOR"); //Botón para personalizar jugador
        btnCustomize.addActionListener(e -> customizePlayer()); //Llama al método de personalización
        btnCustomize.setForeground(new Color(235, 235, 235));
        btnCustomize.setBackground(new Color(44, 150, 57));
        btnCustomize.setFont(new Font("Tahoma", Font.BOLD, 28));
        btnCustomize.setBounds(53, 313, 241, 55); //Ubicación y tamaño
        contentPane.add(btnCustomize);

        //BOTÓN HELP
        JButton btnNewButton = new JButton("AJUDA"); //Botón para ayuda
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                help(); //Llama al método de ayuda
            }
        });
        btnNewButton.setBackground(new Color(44, 150, 57)); //Color de fondo
        btnNewButton.setForeground(new Color(235, 235, 235));
        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 28)); //Fuente y tamaño
        btnNewButton.setBounds(53, 390, 241, 55); //Ubicación y tamaño del botón
        contentPane.add(btnNewButton);

        //CARGAR FUENTE PERSONALIZADA
        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT,
                    getClass().getResourceAsStream("/com/shridhar/personalGame/junegull.ttf")); //Carga la fuente
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);
            Font buttonFont = font.deriveFont(Font.BOLD, 28f);

            //Aplica la fuente personalizada a los botones
            btnStartGame.setFont(buttonFont);
            btnCustomize.setFont(buttonFont);
            btnNewButton.setFont(buttonFont);
        } catch (Exception e) {
            e.printStackTrace(); //Muestra el error en consola si no se carga la fuente
        }
    }
}

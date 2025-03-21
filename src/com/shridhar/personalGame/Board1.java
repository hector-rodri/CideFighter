package com.shridhar.personalGame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import com.shridhar.views.GameScreen1;

public class Board1 extends JPanel { // Define la clase Board1 que extiende JPanel
    Camera1 camera1 = new Camera1(); // Objeto para manejar el fondo
    Ryu ryu; // Instancia del personaje Ryu
    Ken ken; // Instancia del personaje Ken
    Timer timer; // Temporizador para actualizar el juego
    private boolean gameOver = false; // Bandera para indicar si el juego ha terminado
    private String winner = ""; // Almacena el ganador
    private JButton replayButton; // Botón para volver a jugar
    private JButton homeButton;

    private String player1Name = "RYU";
    private String player2Name = "KEN";

    public void setPlayerNames(String player1, String player2) {
        this.player1Name = player1;
        this.player2Name = player2;
    }

    private void gameLoop() {
        timer = new Timer(90, (e) -> repaint());
        timer.start();
    }

    private void bindEvents() {
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_A, KeyEvent.VK_D -> ryu.setSpeed(0);
                    case KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT -> ken.setSpeed(0);
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (gameOver)
                    return;

                switch (e.getKeyCode()) {
                    case KeyEvent.VK_A -> ryu.setSpeed(-10);
                    case KeyEvent.VK_D -> ryu.setSpeed(10);
                    case KeyEvent.VK_W -> ryu.jump();
                    case KeyEvent.VK_G -> ryu.attackWithDelay(() -> {
                        ryu.setMode(IPlayer1.PUNCH);
                        if (isCollide(ryu, ken) && ryu.isCanDealDamage()) {
                            Ken.setKencounter(Ken.getKencounter() - 20 + (ken.getMode() == 5 ? 10 : 0));
                            ryu.resetDamageCooldown(500); // Enfriamiento de 500ms entre daños
                        }
                    }, 500);
                    case KeyEvent.VK_H -> ryu.attackWithDelay(() -> {
                        ryu.setMode(IPlayer1.KICK);
                        if (isCollide(ryu, ken)) {
                            Ken.setKencounter(Ken.getKencounter() - 30 + (ken.getMode() == 5 ? 15 : 0)); // Reduce la vida de Ken si hay colisión
                        }
                    }, 500); // Retraso de 500ms entre ataques

                    case KeyEvent.VK_E -> ryu.setMode(IPlayer1.DEFENCE); // Ryu usa un ataque especial

                    // Controles Ken (Jugador 2)

                    case KeyEvent.VK_LEFT -> ken.setSpeed(-10);
                    case KeyEvent.VK_RIGHT -> ken.setSpeed(10);
                    case KeyEvent.VK_UP -> ken.jump();
                    case KeyEvent.VK_NUMPAD1 -> ken.attackWithDelay(() -> {
                        ken.setMode(IPlayer1.PUNCH);
                        if (isCollide(ken, ryu) && ken.isCanDealDamage()) {
                            Ryu.setRyucounter(Ryu.getRyucounter() - 20 + (ryu.getMode() == 5 ? 10 : 0));
                            ken.resetDamageCooldown(500); // Enfriamiento de 500ms entre daños
                        }
                    }, 500);
                    case KeyEvent.VK_NUMPAD2 -> ken.attackWithDelay(() -> {
                        ken.setMode(IPlayer1.KICK);
                        if (isCollide(ken, ryu)) {
                            Ryu.setRyucounter(Ryu.getRyucounter() - 30 + (ryu.getMode() == 5 ? 15 : 0)); // Reduce la vida de Ryu si hay colisión
                        }
                    }, 500); // Retraso de 500ms entre ataques

                    case KeyEvent.VK_NUMPAD3 -> ken.setMode(IPlayer1.DEFENCE); // Ken bloquea
                }
                checkWinner();
            }
        });
    }

    private boolean isCollide(Sprite1 attacker, Sprite1 defender) {
        int xDistance = Math.abs(attacker.getX() - defender.getX());
        int yDistance = Math.abs(attacker.getY() - defender.getY());
        return xDistance <= Math.max(attacker.getW(), defender.getW()) - 10
                && yDistance <= Math.max(attacker.getH(), defender.getH()) - 10;
    }

    private void checkWinner() {
        if (Ryu.getRyucounter() <= 0) {
            gameOver = true;
            winner = player2Name + " GUANYA!!";
        } else if (Ken.getKencounter() <= 0) {
            gameOver = true;
            winner = player1Name + " GUANYA!!";
        }
    }

    public Board1() {
        setSize(800, 600);
        ryu = new Ryu(100, 100);
        ken = new Ken(600, 100);
        setFocusable(true);
        SwingUtilities.invokeLater(() -> requestFocusInWindow());
        bindEvents();
        gameLoop();
        setLayout(null); // Asegura que los botones se posicionen correctamente
        initReplayButton();
        initHomeButton();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        camera1.drawBG(g);
        ryu.draw(g);
        ryu.move();
        ken.draw(g);
        ken.move();
        drawHUD(g);
        if (gameOver) {
            drawGameOver(g);
            replayButton.setVisible(true);
            homeButton.setVisible(true);
            replayButton.requestFocusInWindow(); // Asegura que los botones capturen eventos
        }
    }

    private void initReplayButton() {
        replayButton = new JButton("Tornar a Jugar");
        replayButton.setBounds(430, 350, 200, 60);
        replayButton.setForeground(Color.WHITE);
        replayButton.setBackground(new Color(44, 150, 57));
        replayButton.setFont(new Font("Verdana", Font.BOLD, 20));
        replayButton.setVisible(false); // Inicialmente, el botón no es visible
        replayButton.addActionListener(e -> resetGame());
        add(replayButton);
        this.setLayout(null); // Establece el diseño del panel como nulo
        this.add(replayButton); // Añade el botón al panel
        try { // Fuente22
            Font font = Font.createFont(Font.TRUETYPE_FONT,
                    getClass().getResourceAsStream("/com/shridhar/personalGame/junegull.ttf"));
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);
            Font buttonFont = font.deriveFont(Font.BOLD, 22f);
            replayButton.setFont(buttonFont);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initHomeButton() {
        homeButton = new JButton("Inici");
        homeButton.setBounds(170, 350, 200, 60);
        homeButton.setForeground(Color.WHITE);
        homeButton.setBackground(new Color(44, 150, 57));
        homeButton.setFont(new Font("Verdana", Font.BOLD, 20));
        homeButton.setVisible(false);
        homeButton.addActionListener(e -> goToHomeScreen());
        
        add(homeButton);
    }
    
    public void goToHomeScreen() {
        JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this); // Obtiene el JFrame padre
        if (topFrame != null) {
            topFrame.dispose(); // Cierra la ventana actual
        }
        
        GameScreen1 frame = new GameScreen1(); // Abre la nueva pantalla
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    private void resetGame() {
        gameOver = false;
        winner = "";
        Ryu.setRyucounter(300);
        Ken.setKencounter(300);
        ryu = new Ryu(100, 400);
        ken = new Ken(600, 400);
        replayButton.setVisible(false);
        homeButton.setVisible(false);
        requestFocusInWindow(); // Permite que el panel recupere eventos de teclado
        repaint();
    }

    private void drawHUD(Graphics g) {
        g.setColor(new Color(44, 150, 57));
        g.fillRect(40, 40, Ryu.getRyucounter(), 40);
        g.setColor(new Color(44, 150, 57));
        g.fillRect(450, 40, Ken.getKencounter(), 40);
        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.BOLD, 32));
        g.drawString(player1Name, 40, 72);
        g.drawString(player2Name, 450, 72);
    }

    private void drawGameOver(Graphics g) {
        g.setColor(new Color(44, 150, 57)); // Establece el color negrito
        g.setFont(new Font("Trebuchet Ms", Font.BOLD, 70)); // Establece la fuente
        g.drawString(winner, getWidth() / 2 - 220, getHeight() / 2); // Dibuja el texto del ganador en el centro de la pantalla
    }
}

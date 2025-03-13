package com.shridhar.personalGame; // Define el paquete al que pertenece esta clase

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Board1 extends JPanel { // Define la clase Board1 que extiende JPanel
    Camera1 camera1 = new Camera1(); // Objeto para manejar el fondo
    Ryu ryu; // Instancia del personaje Ryu
    Ken ken; // Instancia del personaje Ken
    Timer timer; // Temporizador para actualizar el juego
    private boolean gameOver = false; // Bandera para indicar si el juego ha terminado
    private String winner = ""; // Almacena el ganador
    private JButton replayButton; // Botón para volver a jugar
    private JButton homeButton; // Botón para volver a inicio
    private String player1Name = "RYU";
    private String player2Name = "KEN";

    public void setPlayerNames(String player1, String player2) {
        this.player1Name = player1;
        this.player2Name = player2;
    }

    // Método para iniciar el bucle del juego
    private void gameLoop() {
        timer = new Timer(90, (e) -> repaint()); // Configura el temporizador para actualizar el juego cada 90 ms
        timer.start(); // Inicia el temporizador
    }

    // Método para asociar eventos de teclado a las acciones del juego
    private void bindEvents() {
        this.addKeyListener(new KeyAdapter() { // Añade un KeyListener al panel
            @Override
            public void keyReleased(KeyEvent e) { // Maneja el evento cuando se suelta una tecla
                switch (e.getKeyCode()) { // Verifica qué tecla se soltó
                    case KeyEvent.VK_A, KeyEvent.VK_D -> ryu.setSpeed(0); // Detiene a Ryu cuando se sueltan las teclas
                                                                          // A o D
                    case KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT -> ken.setSpeed(0); // Detiene a Ken cuando se sueltan las
                                                                                 // teclas de flecha izquierda o derecha
                }
            }

            // ...existing code...

            @Override
            public void keyPressed(KeyEvent e) { // Maneja el evento cuando se presiona una tecla
                if (gameOver)
                    return; // Si el juego terminó, ignorar eventos

                switch (e.getKeyCode()) { // Verifica qué tecla se presionó
                    // Controles Ryu (Jugador 1)

                    case KeyEvent.VK_A -> ryu.setSpeed(-10);
                    case KeyEvent.VK_D -> ryu.setSpeed(10);
                    case KeyEvent.VK_W -> {
                        ryu.jump(); // Ahora Ryu puede saltar correctamente
                    }

                    case KeyEvent.VK_G -> ryu.attackWithDelay(() -> {
                        ryu.setMode(IPlayer1.PUNCH);
                        if (isCollide(ryu, ken) && ryu.isCanDealDamage()) {
                            Ken.setKencounter(Ken.getKencounter() - 20);
                            ryu.resetDamageCooldown(500); // Enfriamiento de 500ms entre daños
                        }
                    }, 500); // Retraso de 500ms entre ataques

                    case KeyEvent.VK_H -> ryu.attackWithDelay(() -> {
                        ryu.setMode(IPlayer1.KICK); // Ryu lanza una patada
                        if (isCollide(ryu, ken)) {
                            Ken.setKencounter(Ken.getKencounter() - 30); // Reduce la vida de Ken si hay colisión
                        }
                    }, 500); // Retraso de 500ms entre ataques

                    case KeyEvent.VK_J -> ryu.setMode(IPlayer1.POWER); // Ryu usa un ataque especial

                    // Controles Ken (Jugador 2)

                    case KeyEvent.VK_LEFT -> ken.setSpeed(-10);
                    case KeyEvent.VK_RIGHT -> ken.setSpeed(10);
                    case KeyEvent.VK_UP -> ken.jump(); // Ahora Ken puede saltar correctamente

                    case KeyEvent.VK_NUMPAD1 -> ken.attackWithDelay(() -> {
                        ken.setMode(IPlayer1.PUNCH);
                        if (isCollide(ken, ryu) && ken.isCanDealDamage()) {
                            Ryu.setRyucounter(Ryu.getRyucounter() - 20);
                            ken.resetDamageCooldown(500); // Enfriamiento de 500ms entre daños
                        }
                    }, 500); // Retraso de 500ms entre ataques

                    case KeyEvent.VK_NUMPAD2 -> ken.attackWithDelay(() -> {
                        ken.setMode(IPlayer1.KICK); // Ken lanza una patada
                        if (isCollide(ken, ryu)) {
                            Ryu.setRyucounter(Ryu.getRyucounter() - 30); // Reduce la vida de Ryu si hay colisión
                        }
                    }, 500); // Retraso de 500ms entre ataques

                    // case KeyEvent.VK_NUMPAD3 -> ken.setMode(IPlayer1.POWER); // Ken usa un ataque
                    // especial
                }
                checkWinner(); // Verificar si hay un ganador después de cada acción
            }

            // ...existing code...
        });
    }

    // Método mejorado para verificar colisiones, permitiendo ataques en el aire
    private boolean isCollide(Sprite1 attacker, Sprite1 defender) {
        int xDistance = Math.abs(attacker.getX() - defender.getX()); // Calcula la distancia en el eje X
        int yDistance = Math.abs(attacker.getY() - defender.getY()); // Calcula la distancia en el eje Y
        return xDistance <= Math.max(attacker.getW(), defender.getW()) - 10
                && yDistance <= Math.max(attacker.getH(), defender.getH()) - 10; // Verifica si hay colisión
    }

    // Método para verificar si hay un ganador
    private void checkWinner() {
        if (Ryu.getRyucounter() <= 0) { // Verifica si la vida de Ryu es 0 o menos
            gameOver = true; // Indica que el juego ha terminado
            winner = player2Name + " GUANYA!!"; // Establece a Ken como ganador

        } else if (Ken.getKencounter() <= 0) { // Verifica si la vida de Ken es 0 o menos
            gameOver = true; // Indica que el juego ha terminado
            winner = player1Name + " GUANYA!!"; // Establece a Ryu como ganador
        }
    }

    public Board1() {
        setSize(800, 600); // Establece el tamaño del panel
        ryu = new Ryu(100, 100); // Crea una instancia de Ryu
        ken = new Ken(600, 100); // Crea una instancia de Ken
        setFocusable(true); // Permite que el panel reciba eventos de teclado
        SwingUtilities.invokeLater(() -> requestFocusInWindow());
        bindEvents(); // Asocia los eventos de teclado
        gameLoop(); // Inicia el bucle del juego
        initReplayButton(); // Inicializa el botón de volver a jugar
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); // Llama al método de la superclase para limpiar el componente
        camera1.drawBG(g); // Dibuja el fondo
        ryu.draw(g); // Dibuja a Ryu
        ryu.move(); // Mueve a Ryu
        ken.draw(g); // Dibuja a Ken
        ken.move(); // Mueve a Ken
        drawHUD(g); // Dibuja la interfaz de usuario
        if (gameOver) { // Verifica si el juego ha terminado
            drawGameOver(g); // Dibuja la pantalla de fin de juego
            replayButton.setVisible(true); // Muestra el botón cuando el juego ha terminado
        }
    }

    private void initReplayButton() {
        replayButton = new JButton("Tornar a Jugar"); // Crea el botón de volver a jugar
        replayButton.setBounds(300, 300, 200, 60); // Establece la posición y tamaño del botón
        replayButton.setForeground(new Color(255, 255, 255));// COLOR LETRA
        replayButton.setBackground(new Color(44, 150, 57));// COLOR FONDO
        replayButton.setFont(new Font("Verdana", Font.BOLD, 20));
        replayButton.setVisible(false); // Inicialmente, el botón no es visible
        replayButton.addActionListener(new ActionListener() { // Añade un ActionListener al botón
            @Override
            public void actionPerformed(ActionEvent e) {
                resetGame(); // Reinicia el juego cuando se hace clic en el botón
            }
        });
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

    private void resetGame() {
        gameOver = false; // Indica que el juego no ha terminado
        winner = ""; // Limpia el ganador

        Ryu.setRyucounter(300); // Restablece la vida de Ryu
        Ken.setKencounter(300); // Restablece la vida de Ken
        ryu = new Ryu(100, 400);
        ken = new Ken(600, 400);

        replayButton.setVisible(false); // Oculta el botón de volver a jugar
        repaint(); // Redibuja el componente
    }

    private void drawHUD(Graphics g) {
        g.setColor(Color.RED); // Establece el color rojo
        g.fillRect(40, 40, Ryu.getRyucounter(), 40); // Dibuja la barra de vida de Ryu
        g.setColor(Color.YELLOW); // Establece el color amarillo
        g.fillRect(400, 40, Ken.getKencounter(), 40); // Dibuja la barra de vida de Ken
        g.setColor(Color.black); // Establece el color negro
        g.setFont(new Font("Arial", Font.BOLD, 32)); // Establece la fuente
        g.drawString(player1Name, 40, 40); // Dibuja el texto "RYU"
        g.drawString(player2Name, 400, 40); // Dibuja el texto "KEN"
    }

    private void drawGameOver(Graphics g) {
        g.setColor(new Color(44, 150, 57)); // Establece el color negrito
        g.setFont(new Font("Trebuchet Ms", Font.BOLD, 70)); // Establece la fuente
        g.drawString(winner, getWidth() / 2 - 220, getHeight() / 2); // Dibuja el texto del ganador en el centro de la
                                                                     // pantalla
    }
}
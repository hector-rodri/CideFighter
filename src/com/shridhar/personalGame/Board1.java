package com.shridhar.personalGame; // Define el paquete al que pertenece esta clase

import java.awt.*; 
import java.awt.event.*; 
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Board1 extends JPanel { // Define la clase Board1 que extiende JPanel
    Camera1 camera1 = new Camera1(); // Objeto para manejar el fondo
    Ryu ryu; // Instancia del personaje Ryu
    Ken ken; // Instancia del personaje Ken
    Timer timer; // Temporizador para actualizar el juego
    private boolean gameOver = false; // Bandera para indicar si el juego ha terminado
    private String winner = ""; // Almacena el ganador
    private JButton replayButton; // Botón para volver a jugar

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
                    case KeyEvent.VK_A, KeyEvent.VK_D -> ryu.setSpeed(0); // Detiene a Ryu cuando se sueltan las teclas A o D
                    case KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT -> ken.setSpeed(0); // Detiene a Ken cuando se sueltan las teclas de flecha izquierda o derecha
                }
            }

            @Override
            public void keyPressed(KeyEvent e) { // Maneja el evento cuando se presiona una tecla
                if (gameOver)
                    return; // Si el juego terminó, ignorar eventos

                switch (e.getKeyCode()) { // Verifica qué tecla se presionó
                    // Controles Ryu (Jugador 1)
                    case KeyEvent.VK_A -> ryu.setSpeed(-10); // Mueve a Ryu a la izquierda
                    case KeyEvent.VK_D -> ryu.setSpeed(20); // Mueve a Ryu a la derecha
                    case KeyEvent.VK_W -> jump(ryu); // Hace que Ryu salte
                    case KeyEvent.VK_G -> {
                        ryu.setMode(IPlayer1.PUNCH); // Ryu lanza un puñetazo
                        if (isCollide(ryu, ken)) {
                            Ken.setKencounter(Ken.getKencounter() - 20); // Reduce la vida de Ken si hay colisión
                        }
                    }
                    case KeyEvent.VK_H -> {
                        ryu.setMode(IPlayer1.KICK); // Ryu lanza una patada
                        if (isCollide(ryu, ken)) {
                            Ken.setKencounter(Ken.getKencounter() - 30); // Reduce la vida de Ken si hay colisión
                        }
                    }
                    case KeyEvent.VK_J -> ryu.setMode(IPlayer1.POWER); // Ryu usa un ataque especial

                    // Controles Ken (Jugador 2)
                    case KeyEvent.VK_LEFT -> ken.setSpeed(-10); // Mueve a Ken a la izquierda
                    case KeyEvent.VK_RIGHT -> ken.setSpeed(20); // Mueve a Ken a la derecha
                    case KeyEvent.VK_UP -> jump(ken); // Hace que Ken salte
                    case KeyEvent.VK_NUMPAD1 -> {
                        ken.setMode(IPlayer1.PUNCH); // Ken lanza un puñetazo
                        if (isCollide(ken, ryu)) {
                            Ryu.setRyucounter(Ryu.getRyucounter() - 20); // Reduce la vida de Ryu si hay colisión
                        }
                    }
                    // case KeyEvent.VK_NUMPAD2 -> {
                    //     ken.setMode(IPlayer1.KICK); // Ken lanza una patada
                    //     if (isCollide(ken, ryu)) {
                    //         Ryu.setRyucounter(Ryu.getRyucounter() - 30); // Reduce la vida de Ryu si hay colisión
                    //     }
                    // }
                    // case KeyEvent.VK_NUMPAD3 -> ken.setMode(IPlayer1.POWER); // Ken usa un ataque especial
                }
                checkWinner(); // Verificar si hay un ganador después de cada acción
            }
        });
    }

    private boolean salta = false; // Bandera para indicar si un personaje está saltando

    private void jump(Sprite1 player) {
        if (salta)
            return; // Si ya está saltando, no hacer nada
        salta = true; // Indicar que el personaje está saltando

        new Thread(() -> { // Crea un nuevo hilo para manejar el salto
            // Subida del personaje
            for (int i = 0; i < 10; i++) {
                player.setY(player.getY() - 5); // Mueve al personaje hacia arriba
                repaint(); // Redibuja el componente
                try {
                    Thread.sleep(20); // Pausa para crear el efecto de salto
                } catch (InterruptedException ignored) {
                }
            }

            // Bajada del personaje
            for (int i = 0; i < 10; i++) {
                player.setY(player.getY() + 5); // Mueve al personaje hacia abajo
                repaint(); // Redibuja el componente
                try {
                    Thread.sleep(10); // Pausa para crear el efecto de caída
                } catch (InterruptedException ignored) {
                }
            }

            salta = false; // Indicar que el personaje ha terminado de saltar
        }).start(); // Inicia el hilo para manejar el salto
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
            winner = "KEN GUANYA!"; // Establece a Ken como ganador
        } else if (Ken.getKencounter() <= 0) { // Verifica si la vida de Ken es 0 o menos
            gameOver = true; // Indica que el juego ha terminado
            winner = "RYU GUANYA!"; // Establece a Ryu como ganador
        }
    }

    public Board1() {
        setSize(800, 600); // Establece el tamaño del panel
        ryu = new Ryu(100, 100); // Crea una instancia de Ryu
        ken = new Ken(600, 100); // Crea una instancia de Ken
        setFocusable(true); // Permite que el panel reciba eventos de teclado
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
        replayButton.setFont(new Font("Arial", Font.BOLD, 20)); // Establece la fuente del botón
        replayButton.setVisible(false); // Inicialmente, el botón no es visible
        replayButton.addActionListener(new ActionListener() { // Añade un ActionListener al botón
            @Override
            public void actionPerformed(ActionEvent e) {
                resetGame(); // Reinicia el juego cuando se hace clic en el botón
            }
        });
        this.setLayout(null); // Establece el diseño del panel como nulo
        this.add(replayButton); // Añade el botón al panel
    }

    private void resetGame() {
        gameOver = false; // Indica que el juego no ha terminado
        winner = ""; // Limpia el ganador
        Ryu.setRyucounter(300); // Restablece la vida de Ryu
        Ken.setKencounter(300); // Restablece la vida de Ken
        ryu.setX(100); // Restablece la posición de Ryu
        ryu.setY(400);
        ken.setX(600); // Restablece la posición de Ken
        ken.setY(400);
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
        g.drawString("RYU", 20, 40); // Dibuja el texto "RYU"
        g.drawString("KEN", 380, 40); // Dibuja el texto "KEN"
    }

    private void drawGameOver(Graphics g) {
        g.setColor(Color.BLACK); // Establece el color negro
        g.fillRect(0, 0, getWidth(), getHeight()); // Dibuja un rectángulo negro que cubre toda la pantalla
        g.setColor(Color.CYAN); // Establece el color cian
        g.setFont(new Font("Arial", Font.BOLD, 60)); // Establece la fuente
        g.drawString(winner, getWidth() / 2 - 150, getHeight() / 2); // Dibuja el texto del ganador en el centro de la pantalla
    }
}
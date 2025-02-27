package com.shridhar.personalGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Board1 extends JPanel {
    Camera1 camera1 = new Camera1(); // Objeto para manejar el fondo
    Ryu ryu; // Instancia del personaje Ryu
    Ken ken; // Instancia del personaje Ken
    Timer timer; // Temporizador para actualizar el juego
    private boolean gameOver = false; // Bandera para indicar si el juego ha terminado
    private String winner = ""; // Almacena el ganador

    // Método para iniciar el bucle del juego
    private void gameLoop() {
        timer = new Timer(90, (e) -> repaint()); // Redibuja el juego cada 30 ms
        timer.start();
    }

    // Método para asociar eventos de teclado a las acciones del juego
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
                    return; // Si el juego terminó, ignorar eventos

                switch (e.getKeyCode()) {
                    // Controles Ryu (Jugador 1)
                    case KeyEvent.VK_A -> ryu.setSpeed(-10);
                    case KeyEvent.VK_D -> ryu.setSpeed(20);
                    case KeyEvent.VK_W -> {// Ahora Ryu puede saltar correctamente
                        jump(ryu);
                    }
                    case KeyEvent.VK_G -> {
                        ryu.setMode(IPlayer1.PUNCH);
                        if (isCollide(ryu, ken)) {
                            Ken.setKencounter(Ken.getKencounter() - 20);
                        }
                    }
                    case KeyEvent.VK_H -> {
                        ryu.setMode(IPlayer1.KICK);
                        if (isCollide(ryu, ken)) {
                            Ken.setKencounter(Ken.getKencounter() - 30);
                        }
                    }
                    case KeyEvent.VK_J -> ryu.setMode(IPlayer1.POWER);

                    // Controles Ken (Jugador 2)
                    case KeyEvent.VK_LEFT -> ken.setSpeed(-10);
                    case KeyEvent.VK_RIGHT -> ken.setSpeed(20);
                    case KeyEvent.VK_UP -> jump(ken); // Ahora Ken puede saltar correctamente
                    case KeyEvent.VK_NUMPAD1 -> {
                        ken.setMode(IPlayer1.PUNCH);
                        if (isCollide(ken, ryu)) {
                            Ryu.setRyucounter(Ryu.getRyucounter() - 20);
                        }
                    }
                    // case KeyEvent.VK_NUMPAD2 -> {
                    //     ken.setMode(IPlayer1.KICK);
                    //     if (isCollide(ken, ryu)) {
                    //         Ryu.setRyucounter(Ryu.getRyucounter() - 30);
                    //     }
                    // }
                    // case KeyEvent.VK_NUMPAD3 -> ken.setMode(IPlayer1.POWER);
                }
                checkWinner(); // Verificar si hay un ganador después de cada acción
            }
        });
    }

    private boolean salta = false;

    private void jump(Sprite1 player) {
        if (salta)
            return; 
        salta = true; 

        new Thread(() -> {
            // Subida del personaje
            for (int i = 0; i < 10; i++) {
                player.setY(player.getY() - 5);
                repaint();
                try {
                    Thread.sleep(20);
                } catch (InterruptedException ignored) {
                }
            }

            // Bajada del personaje
            for (int i = 0; i < 10; i++) {
                player.setY(player.getY() + 5);
                repaint();
                try {
                    Thread.sleep(20);
                } catch (InterruptedException ignored) {
                }
            }

            salta = false;
        }).start();
    }
    
    // Método mejorado para verificar colisiones, permitiendo ataques en el aire
    private boolean isCollide(Sprite1 attacker, Sprite1 defender) {
        int xDistance = Math.abs(attacker.getX() - defender.getX());
        int yDistance = Math.abs(attacker.getY() - defender.getY());
        return xDistance <= Math.max(attacker.getW(), defender.getW()) - 10
                && yDistance <= Math.max(attacker.getH(), defender.getH()) - 10;
    }

    // Método para verificar si hay un ganador
    private void checkWinner() {
        if (Ryu.getRyucounter() <= 0) {
            gameOver = true;
            winner = "KEN WINS!";
        } else if (Ken.getKencounter() <= 0) {
            gameOver = true;
            winner = "RYU WINS!";
        }
    }

    public Board1() {
        setSize(800, 600);
        ryu = new Ryu(100, 100);
        ken = new Ken(600, 100);
        setFocusable(true);
        bindEvents();
        gameLoop();
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
        if (gameOver)
            drawGameOver(g);
    }

    private void drawHUD(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(40, 40, Ryu.getRyucounter(), 40);
        g.setColor(Color.YELLOW);
        g.fillRect(400, 40, Ken.getKencounter(), 40);
        g.setColor(Color.black);
        g.setFont(new Font("Arial", Font.BOLD, 32));
        g.drawString("RYU", 20, 40);
        g.drawString("KEN", 380, 40);
    }

    private void drawGameOver(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.CYAN);
        g.setFont(new Font("Arial", Font.BOLD, 60));
        g.drawString(winner, getWidth() / 2 - 150, getHeight() / 2);
    }
}

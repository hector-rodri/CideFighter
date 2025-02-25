package com.shridhar.personalGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Board1 extends JPanel {
    Camera1 camera1 = new Camera1();
    Ryu ryu;
    Ken ken;
    Timer timer;

    private void gameLoop() {
        timer = new Timer(30, (e) -> repaint());
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
                switch (e.getKeyCode()) {
                    // Controles Ryu (Jugador 1)
                    case KeyEvent.VK_A -> ryu.setSpeed(-6);
                    case KeyEvent.VK_D -> ryu.setSpeed(6);
                    case KeyEvent.VK_W -> ryu.setMode(IPlayer1.WALK);
                    case KeyEvent.VK_G -> {
                        ryu.setMode(IPlayer1.PUNCH);
                        if (isCollide(ryu.getX(), ryu.getY(), ken.getX(), ken.getY(), ryu.getW(), ken.getW())) {
                            Ken.setKencounter(Ken.getKencounter() - 20);
                        }
                    }
                    case KeyEvent.VK_H -> {
                        ryu.setMode(IPlayer1.KICK);
                        if (isCollide(ryu.getX(), ryu.getY(), ken.getX(), ken.getY(), ryu.getW(), ken.getW())) {
                            Ken.setKencounter(Ken.getKencounter() - 30);
                        }
                    }
                    case KeyEvent.VK_J -> ryu.setMode(IPlayer1.POWER);
                    
                    // Controles Ken (Jugador 2)
                    case KeyEvent.VK_LEFT -> ken.setSpeed(-6);
                    case KeyEvent.VK_RIGHT -> ken.setSpeed(6);
                    case KeyEvent.VK_UP -> ken.setMode(IPlayer1.WALK);
                    case KeyEvent.VK_NUMPAD1 -> {
                        ken.setMode(IPlayer1.PUNCH);
                        if (isCollide(ken.getX(), ken.getY(), ryu.getX(), ryu.getY(), ken.getW(), ryu.getW())) {
                            Ryu.setRyucounter(Ryu.getRyucounter() - 20);
                        }
                    }
                    case KeyEvent.VK_NUMPAD2 -> {
                        ken.setMode(IPlayer1.KICK);
                        if (isCollide(ken.getX(), ken.getY(), ryu.getX(), ryu.getY(), ken.getW(), ryu.getW())) {
                            Ryu.setRyucounter(Ryu.getRyucounter() - 30);
                        }
                    }
                    case KeyEvent.VK_NUMPAD3 -> ken.setMode(IPlayer1.POWER);
                }
            }
        });
    }

    private boolean isCollide(int x1, int y1, int x2, int y2, int w1, int w2) {
        int xDistance = Math.abs(x1 - x2);
        return xDistance <= Math.max(w1, w2) - 10;
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
    }

    private void drawHUD(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(40, 40, Ryu.getRyucounter(), 40);
        g.setColor(Color.YELLOW);
        g.fillRect(400, 40, Ken.getKencounter(), 40);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 32));
        g.drawString("RYU", 20, 40);
        g.drawString("KEN", 380, 40);
    }
}


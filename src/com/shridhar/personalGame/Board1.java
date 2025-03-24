package com.shridhar.personalGame;

import java.awt.*; //Importa todas las clases necesarias para la interfaz gráfica
import java.awt.event.*; 
import javax.swing.*; 
import com.shridhar.views.GameScreen1;

public class Board1 extends JPanel { 
    Camera1 camera1 = new Camera1(); //Crea un objeto Camera1 para manejar el fondo del juego
    Ryu ryu; //Declara una instancia del personaje Ryu
    Ken ken; //Declara una instancia del personaje Ken
    Timer timer; //Declara un temporizador para actualizar el juego periódicamente
    private boolean gameOver = false; //Indicar si el juego ha terminado
    private String winner = ""; //Almacena el nombre del ganador
    private JButton replayButton; //Botón para reiniciar el juego
    private JButton homeButton; //Botón para volver a la pantalla principal

    private String player1Name = "RYU"; 
    private String player2Name = "KEN"; 

    public void setPlayerNames(String player1, String player2) { //Método para establecer los nombres de los jugadores
        this.player1Name = player1; 
        this.player2Name = player2;
    }

    private void gameLoop() { //Método para iniciar el bucle principal del juego
        timer = new Timer(90, (e) -> repaint());
        timer.start(); 
    }

    private void bindEvents() { //Método para vincular eventos de teclado
        this.addKeyListener(new KeyAdapter() { //Añade un KeyListener para manejar eventos de teclado
            @Override
            public void keyReleased(KeyEvent e) { //Evento cuando se suelta una tecla
                switch (e.getKeyCode()) { //Verifica qué tecla se soltó
                    case KeyEvent.VK_A, KeyEvent.VK_D -> ryu.setSpeed(0); //Detiene a Ryu si se suelta A o D
                    case KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT -> ken.setSpeed(0); //Detiene a Ken si se suelta LEFT o RIGHT
                }
            }

            @Override
            public void keyPressed(KeyEvent e) { //Evento cuando se presiona una tecla
                if (gameOver) //Si el juego ha terminado no hay mas eventos
                    return;

                switch (e.getKeyCode()) { //Verifica qué tecla se presionó
                    //Controles para Ryu (Jugador 1)
                    case KeyEvent.VK_A -> ryu.setSpeed(-10); //Mueve a Ryu a la izquierda
                    case KeyEvent.VK_D -> ryu.setSpeed(10); //Mueve a Ryu a la derecha
                    case KeyEvent.VK_W -> ryu.jump(); //Hace que Ryu salte.
                    case KeyEvent.VK_G -> ryu.attackWithDelay(() -> { //Ryu realiza un ataque de puño con retraso
                        ryu.setMode(IPlayer1.PUNCH);
                        if (isCollide(ryu, ken) && ryu.isCanDealDamage()) { //Verifica si Ryu golpea a Ken
                            Ken.setKencounter(Ken.getKencounter() - 20 + (ken.getMode() == 5 ? 10 : 0)); //Reduce la vida de Ken
                            ryu.resetDamageCooldown(500); //Establece un cooldown de 500 ms para el daño
                        }
                    }, 500);
                    case KeyEvent.VK_H -> ryu.attackWithDelay(() -> { //Ryu realiza un ataque de patada con retraso
                        ryu.setMode(IPlayer1.KICK); 
                        if (isCollide(ryu, ken)) { //Verifica si Ryu golpea a Ken
                            Ken.setKencounter(Ken.getKencounter() - 30 + (ken.getMode() == 5 ? 15 : 0)); //Reduce la vida de Ken
                        }
                    }, 500);
                    case KeyEvent.VK_E -> ryu.setMode(IPlayer1.DEFENCE); //Ryu entra en modo defensa si se presiona E

                    //Controles para Ken (Jugador 2)
                    case KeyEvent.VK_LEFT -> ken.setSpeed(-10); //Mueve a Ken a la izquierda
                    case KeyEvent.VK_RIGHT -> ken.setSpeed(10); //Mueve a Ken a la derecha
                    case KeyEvent.VK_UP -> ken.jump(); // Hace que Ken salte.
                    case KeyEvent.VK_NUMPAD1 -> ken.attackWithDelay(() -> { // Ken realiza un ataque de puño con retraso.
                        ken.setMode(IPlayer1.PUNCH); // Cambia el modo de Ken a PUNCH.
                        if (isCollide(ken, ryu) && ken.isCanDealDamage()) { // Verifica si Ken golpea a Ryu.
                            Ryu.setRyucounter(Ryu.getRyucounter() - 20 + (ryu.getMode() == 5 ? 10 : 0)); // Reduce la vida de Ryu.
                            ken.resetDamageCooldown(500); // Establece un enfriamiento de 500 ms para el daño.
                        }
                    }, 500);
                    case KeyEvent.VK_NUMPAD2 -> ken.attackWithDelay(() -> { // Ken realiza un ataque de patada con retraso.
                        ken.setMode(IPlayer1.KICK); // Cambia el modo de Ken a KICK.
                        if (isCollide(ken, ryu)) { // Verifica si Ken golpea a Ryu.
                            Ryu.setRyucounter(Ryu.getRyucounter() - 30 + (ryu.getMode() == 5 ? 15 : 0)); // Reduce la vida de Ryu.
                        }
                    }, 500);
                    case KeyEvent.VK_NUMPAD3 -> ken.setMode(IPlayer1.DEFENCE); // Ken entra en modo defensa.
                }
                checkWinner(); // Verifica si hay un ganador después de cada acción.
            }
        });
    }

    private boolean isCollide(Sprite1 attacker, Sprite1 defender) { // Método para verificar colisiones entre personajes.
        int xDistance = Math.abs(attacker.getX() - defender.getX()); // Calcula la distancia en X entre los personajes.
        int yDistance = Math.abs(attacker.getY() - defender.getY()); // Calcula la distancia en Y entre los personajes.
        return xDistance <= Math.max(attacker.getW(), defender.getW()) - 10 // Verifica si hay colisión en X.
                && yDistance <= Math.max(attacker.getH(), defender.getH()) - 10; // Verifica si hay colisión en Y.
    }

    private void checkWinner() { // Método para verificar si hay un ganador.
        if (Ryu.getRyucounter() <= 0) { // Si la vida de Ryu llega a 0.
            gameOver = true; // Marca el juego como terminado.
            winner = player2Name + " GUANYA!!"; // Declara a Ken como ganador.
        } else if (Ken.getKencounter() <= 0) { // Si la vida de Ken llega a 0.
            gameOver = true; // Marca el juego como terminado.
            winner = player1Name + " GUANYA!!"; // Declara a Ryu como ganador.
        }
    }

    public Board1() { // Constructor de la clase Board1.
        setSize(800, 600); // Establece el tamaño del panel.
        ryu = new Ryu(100, 100); // Crea una instancia de Ryu en la posición inicial.
        ken = new Ken(600, 100); // Crea una instancia de Ken en la posición inicial.
        setFocusable(true); // Hace que el panel sea enfocable para recibir eventos de teclado.
        SwingUtilities.invokeLater(() -> requestFocusInWindow()); // Solicita el enfoque en la ventana.
        bindEvents(); // Vincula los eventos de teclado.
        gameLoop(); // Inicia el bucle principal del juego.
        setLayout(null); // Establece un diseño nulo para posicionar componentes manualmente.
        initReplayButton(); // Inicializa el botón de reinicio.
        initHomeButton(); // Inicializa el botón de inicio.
    }

    @Override
    public void paintComponent(Graphics g) { // Método para dibujar los elementos del juego.
        super.paintComponent(g); // Llama al método de la superclase para limpiar el área de dibujo.
        camera1.drawBG(g); // Dibuja el fondo del juego.
        ryu.draw(g); // Dibuja a Ryu.
        ryu.move(); // Actualiza la posición de Ryu.
        ken.draw(g); // Dibuja a Ken.
        ken.move(); // Actualiza la posición de Ken.
        drawHUD(g); // Dibuja el HUD (barra de vida y nombres).
        if (gameOver) { // Si el juego ha terminado.
            drawGameOver(g); // Dibuja el mensaje de fin del juego.
            replayButton.setVisible(true); // Muestra el botón de reinicio.
            homeButton.setVisible(true); // Muestra el botón de inicio.
            replayButton.requestFocusInWindow(); // Asegura que los botones capturen eventos.
        }
    }

    private void initReplayButton() { // Método para inicializar el botón de reinicio.
        replayButton = new JButton("Tornar a Jugar"); // Crea el botón con texto.
        replayButton.setBounds(430, 350, 200, 60); // Establece la posición y tamaño del botón.
        replayButton.setForeground(Color.WHITE); // Establece el color del texto.
        replayButton.setBackground(new Color(44, 150, 57)); // Establece el color de fondo.
        replayButton.setFont(new Font("Verdana", Font.BOLD, 20)); // Establece la fuente del texto.
        replayButton.setVisible(false); // Inicialmente, el botón no es visible.
        replayButton.addActionListener(e -> resetGame()); // Asocia un evento para reiniciar el juego.
        add(replayButton); // Añade el botón al panel
    }

    private void initHomeButton() { // Método para inicializar el botón de inicio
        homeButton = new JButton("Inici"); // Crea el botón con texto
        homeButton.setBounds(170, 350, 200, 60); // Establece la posición y tamaño del botón
        homeButton.setForeground(Color.WHITE); // Establece el color del texto
        homeButton.setBackground(new Color(44, 150, 57)); // Establece el color de fondo
        homeButton.setFont(new Font("Verdana", Font.BOLD, 20)); // Establece la fuente del texto
        homeButton.setVisible(false); // Inicialmente, el botón no es visible
        homeButton.addActionListener(e -> goToHomeScreen()); // Asocia un evento para ir a la pantalla principal
        add(homeButton); // Añade el botón al panel
    }

    public void goToHomeScreen() { //Método para ir a la pantalla principal
        JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this); //Obtiene el JFrame padre
        if (topFrame != null) { //Si existe un JFrame padre
            topFrame.dispose(); //Cierra la ventana actual
        }
        GameScreen1 frame = new GameScreen1(); //Crea una nueva instancia de la pantalla principal
        frame.setVisible(true); //Muestra la pantalla principal
        frame.setLocationRelativeTo(null); //Centra la pantalla principal en la ventana
        frame.setResizable(false); //Evita que la ventana sea redimensionable
        resetGame(); //Reinicia el juego
    }

    private void resetGame() { //Método para reiniciar el juego
        gameOver = false; //Marca el juego como no terminado
        winner = ""; //Limpia el nombre del ganador
        Ryu.setRyucounter(300); //Restaura la vida de Ryu
        Ken.setKencounter(300); //Restaura la vida de Ken
        ryu = new Ryu(100, 400); //Reinicia la posición de Ryu
        ken = new Ken(600, 400); //Reinicia la posición de Ken
        replayButton.setVisible(false); //Oculta el botón de reinicio
        homeButton.setVisible(false); //Oculta el botón de inicio
        requestFocusInWindow(); //Permite que el panel recupere eventos de teclado
        repaint(); //Redibuja el panel.
    }

    private void drawHUD(Graphics g) { //Método para dibujar la barra de vida y nombres+
        g.setColor(new Color(44, 150, 57)); //Establece el color de la barra de vida
        g.fillRect(40, 40, Ryu.getRyucounter(), 40); //Dibuja la barras de vida de los personajes
        g.fillRect(450, 40, Ken.getKencounter(), 40); 
        g.setColor(Color.white); 
        g.setFont(new Font("Arial", Font.BOLD, 32)); 
        g.drawString(player1Name, 40, 72); //Dibuja los nombres de los jugadores
        g.drawString(player2Name, 450, 72);
    }

    private void drawGameOver(Graphics g) { //Método para dibujar el mensaje de fin del juego
        g.setColor(new Color(44, 150, 57));
        g.setFont(new Font("Trebuchet Ms", Font.BOLD, 70));
        g.drawString(winner, getWidth() / 2 - 220, getHeight() / 2); //Dibuja el texto del ganador en el centro de la pantalla
    }
}
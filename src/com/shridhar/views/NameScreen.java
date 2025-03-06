package com.shridhar.views;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import com.shridhar.personalGame.Board1;

import java.awt.*;

public class NameScreen extends JFrame {
    public JTextField txtPlayer1Name, txtPlayer2Name;

    public NameScreen() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 501, 447);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel contentPane = new JPanel();
        contentPane.setBackground(new Color(44, 150, 57));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Título de la ventana
        JLabel lblTitle = new JLabel("INTRODUEIX EL TEU NOM:");
        lblTitle.setForeground(Color.black);
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblTitle.setBounds(120, 20, 400, 46);
        contentPane.add(lblTitle);

        // Jugador 1
        JLabel lblPlayer1 = new JLabel("JUGADOR 1");
        lblPlayer1.setFont(new Font("Tahoma", Font.BOLD, 19));
        lblPlayer1.setBounds(20, 75, 200, 29);
        contentPane.add(lblPlayer1);

        txtPlayer1Name = new JTextField();
        txtPlayer1Name.setFont(new Font("Tahoma", Font.PLAIN, 16));
        txtPlayer1Name.setHorizontalAlignment(JTextField.CENTER);
        txtPlayer1Name.setBounds(20, 115, 200, 30);
        contentPane.add(txtPlayer1Name);

        // Jugador 2
        JLabel lblPlayer2 = new JLabel("JUGADOR 2");
        lblPlayer2.setFont(new Font("Tahoma", Font.BOLD, 19));
        lblPlayer2.setBounds(280, 75, 200, 29);
        contentPane.add(lblPlayer2);

        txtPlayer2Name = new JTextField();
        txtPlayer2Name.setFont(new Font("Tahoma", Font.PLAIN, 16));
        txtPlayer2Name.setHorizontalAlignment(JTextField.CENTER);
        txtPlayer2Name.setBounds(280, 115, 200, 30);
        contentPane.add(txtPlayer2Name);

        // Botón para guardar
        JButton btnSave = new JButton("GUARDAR");
        btnSave.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnSave.setForeground(new Color(44, 150, 57));
        btnSave.setBackground(new Color(190, 190, 190));
        btnSave.setFocusPainted(false);
        btnSave.setBorderPainted(false);
        btnSave.setBounds(175, 200, 150, 40);
        btnSave.addActionListener(e -> savePlayerNames());
        contentPane.add(btnSave);
        
        // Botón para salir
        JButton btnExit = new JButton("SORTIR");
        btnExit.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnExit.setBackground(new Color(190, 190, 190)); // Color fondo del botón
        btnExit.setForeground(new Color(44, 150, 57)); // Color de la letra
        btnExit.setBounds(175, 260, 150, 40); // Ubicación del botón
        btnExit.addActionListener(e -> exit());
        contentPane.add(btnExit);
    }

    public void savePlayerNames() {
        String player1Name = txtPlayer1Name.getText().trim();
        String player2Name = txtPlayer2Name.getText().trim();

        if (player1Name.isEmpty() || player2Name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Si us plau, introdueix els noms de tots els jugadors!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        System.out.println("Nom del Jugador 1: " + player1Name);
        System.out.println("Nom del Jugador 2: " + player2Name);

        this.setVisible(false);
        this.dispose();

        Board1 board1 = new Board1();
        board1.setPlayerNames(player1Name, player2Name);

        JFrame gameFrame = new JFrame("Street Fighter");
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setSize(800, 600);
        gameFrame.setLocationRelativeTo(null);
        gameFrame.add(board1);
        gameFrame.setVisible(true);
    }

    // Método para salir
    public void exit() {
        this.setVisible(false);
        this.dispose();
        GameScreen1 frame = new GameScreen1();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(NameScreen::new);
    }
}
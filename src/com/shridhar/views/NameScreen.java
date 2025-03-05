package com.shridhar.views;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

public class NameScreen extends JFrame{
     private JPanel contentPane;
    private JTextField txtPlayerName;

    public void CustomizePlayerScreen() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 370, 200);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblEnterName = new JLabel("Introdueix el teu nom:");
        lblEnterName.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblEnterName.setBounds(20, 30, 200, 30);
        contentPane.add(lblEnterName);

        txtPlayerName = new JTextField();
        txtPlayerName.setBounds(180, 30, 150, 30);
        contentPane.add(txtPlayerName);
        txtPlayerName.setColumns(10);

        JButton btnSave = new JButton("Guardar");
        btnSave.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnSave.setBounds(120, 80, 120, 40);
        btnSave.addActionListener(e -> savePlayerName());
        contentPane.add(btnSave);
        setVisible(true);
    }

    private void savePlayerName() {
        String playerName = txtPlayerName.getText();
        System.out.println("Nom del jugador guardat: " + playerName);
        this.setVisible(false);
        this.dispose();
        GameScreen1 gameScreen = new GameScreen1();
        gameScreen.setVisible(true);
    }
}

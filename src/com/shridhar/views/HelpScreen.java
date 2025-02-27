package com.shridhar.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JWindow;

import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HelpScreen extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField txtInstructions;
	
	private void exit() {
		this.setVisible(false);
		this.dispose();
		GameScreen1 frame = new GameScreen1();
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}

	
	public HelpScreen() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 501, 447);
		contentPane = new JPanel();
		contentPane.setBackground(Color.CYAN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblInstructions = new JLabel("INSTRUCTIONS");
		lblInstructions.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblInstructions.setBounds(157, 26, 200, 46);
		contentPane.add(lblInstructions);
		
		JLabel jugador1 = new JLabel("RYU JUGADOR 1");
		jugador1.setFont(new Font("Tahoma", Font.BOLD, 19));
		jugador1.setBounds(24, 75, 304, 29);
		contentPane.add(jugador1);

		JLabel lblVkupIncrease = new JLabel("Tecla (W) :  Salto");
		lblVkupIncrease.setFont(new Font("Tahoma", Font.ITALIC, 18));
		lblVkupIncrease.setBounds(24, 100, 304, 29);
		contentPane.add(lblVkupIncrease);

		JLabel lblVkrightIncrease = new JLabel("Tecla (A) :  Izquierda");
		lblVkrightIncrease.setFont(new Font("Tahoma", Font.ITALIC, 18));
		lblVkrightIncrease.setBounds(24, 135, 304, 29);
		contentPane.add(lblVkrightIncrease);
		
		JLabel lblVkdownDecrease = new JLabel("Tecla (S) :  Agacharse");
		lblVkdownDecrease.setFont(new Font("Tahoma", Font.ITALIC, 18));
		lblVkdownDecrease.setBounds(24, 170, 304, 29);
		contentPane.add(lblVkdownDecrease);

		JLabel lblVkleftDecrease = new JLabel("Tecla (D) :  Derecha");
		lblVkleftDecrease.setFont(new Font("Tahoma", Font.ITALIC, 18));
		lblVkleftDecrease.setBounds(24, 205, 304, 29);
		contentPane.add(lblVkleftDecrease);

		JLabel lblVkrIncrease = new JLabel("Tecla (G) :  Golpe");
		lblVkrIncrease.setFont(new Font("Tahoma", Font.ITALIC, 18));
		lblVkrIncrease.setBounds(24, 240, 304, 29);
		contentPane.add(lblVkrIncrease);

		JLabel lblVklDecrease = new JLabel("Tecla (H) :  Patada");
		lblVklDecrease.setFont(new Font("Tahoma", Font.ITALIC, 18));
		lblVklDecrease.setBounds(24, 275, 304, 29);
		contentPane.add(lblVklDecrease);

		JLabel ldlVklPoderespecial = new JLabel("Tecla (J) :  Poder Especial");
		ldlVklPoderespecial.setFont(new Font("Tahoma", Font.ITALIC, 18));	
		ldlVklPoderespecial.setBounds(24, 310, 304, 29);
		contentPane.add(ldlVklPoderespecial);


		
		JButton btnExit = new JButton("EXIT");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exit();
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnExit.setBounds(201, 385, 89, 23);
		contentPane.add(btnExit);
		
		
		
		
		
		
		
		
		
		
		
		
	}
}

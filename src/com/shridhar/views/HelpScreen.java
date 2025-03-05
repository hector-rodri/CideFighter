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
		contentPane.setBackground(Color.lightGray);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblInstructions = new JLabel("INSTRUCCIONS");
		lblInstructions.setForeground(Color.black);
		lblInstructions.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblInstructions.setBounds(157, 20, 200, 46);
		contentPane.add(lblInstructions);
		
		JLabel jugador1 = new JLabel("RYU JUGADOR 1");
		jugador1.setFont(new Font("Tahoma", Font.BOLD, 19));
		jugador1.setBounds(20, 75, 304, 29);
		contentPane.add(jugador1);

		JLabel lblVkupIncrease = new JLabel("Tecla (W) :  Salt");
		lblVkupIncrease.setFont(new Font("Tahoma", Font.ITALIC, 18));
		lblVkupIncrease.setBounds(20, 100, 304, 29);
		contentPane.add(lblVkupIncrease);

		JLabel lblVkrightIncrease = new JLabel("Tecla (A) :  Esquerra");
		lblVkrightIncrease.setFont(new Font("Tahoma", Font.ITALIC, 18));
		lblVkrightIncrease.setBounds(20, 135, 304, 29);
		contentPane.add(lblVkrightIncrease);
		
		JLabel lblVkdownDecrease = new JLabel("Tecla (S) :  Acotar-se");
		lblVkdownDecrease.setFont(new Font("Tahoma", Font.ITALIC, 18));
		lblVkdownDecrease.setBounds(20, 170, 304, 29);
		contentPane.add(lblVkdownDecrease);

		JLabel lblVkleftDecrease = new JLabel("Tecla (D) :  Dreta");
		lblVkleftDecrease.setFont(new Font("Tahoma", Font.ITALIC, 18));
		lblVkleftDecrease.setBounds(20, 205, 304, 29);
		contentPane.add(lblVkleftDecrease);

		JLabel lblVkrIncrease = new JLabel("Tecla (G) :  Cop");
		lblVkrIncrease.setFont(new Font("Tahoma", Font.ITALIC, 18));
		lblVkrIncrease.setBounds(20, 240, 304, 29);
		contentPane.add(lblVkrIncrease);

		JLabel lblVklDecrease = new JLabel("Tecla (H) :  Potada");
		lblVklDecrease.setFont(new Font("Tahoma", Font.ITALIC, 18));
		lblVklDecrease.setBounds(20, 275, 304, 29);
		contentPane.add(lblVklDecrease);

		JLabel ldlVklPoderespecial = new JLabel("Tecla (J) :  Poder");
		ldlVklPoderespecial.setFont(new Font("Tahoma", Font.ITALIC, 18));	
		ldlVklPoderespecial.setBounds(20, 310, 304, 29);
		contentPane.add(ldlVklPoderespecial);


		JLabel jugador2 = new JLabel("KEN JUGADOR 2");
		jugador2.setFont(new Font("Tahoma", Font.BOLD, 19));
		jugador2.setBounds(280, 75, 304, 29);
		contentPane.add(jugador2);

		JLabel lblVkupIncrease2 = new JLabel("Tecla (↑) :  Salt");
		lblVkupIncrease2.setFont(new Font("Tahoma", Font.ITALIC, 18));
		lblVkupIncrease2.setBounds(280, 100, 304, 29);
		contentPane.add(lblVkupIncrease2);

		JLabel lblVkrightIncrease2 = new JLabel("Tecla (←) :  Esquerra");
		lblVkrightIncrease2.setFont(new Font("Tahoma", Font.ITALIC, 18));
		lblVkrightIncrease2.setBounds(280, 135, 304, 29);
		contentPane.add(lblVkrightIncrease2);

		JLabel lblVkdownDecrease2 = new JLabel("Tecla (↓) :  Acotar-se");
		lblVkdownDecrease2.setFont(new Font("Tahoma", Font.ITALIC, 18));
		lblVkdownDecrease2.setBounds(280, 170, 304, 29);
		contentPane.add(lblVkdownDecrease2);

		JLabel lblVkleftDecrease2 = new JLabel("Tecla (→) :  Dreta");
		lblVkleftDecrease2.setFont(new Font("Tahoma", Font.ITALIC, 18));
		lblVkleftDecrease2.setBounds(280, 205, 304, 29);
		contentPane.add(lblVkleftDecrease2);

		JLabel lblVkrIncrease2 = new JLabel("NumPad (1) :  Cop");
		lblVkrIncrease2.setFont(new Font("Tahoma", Font.ITALIC, 18));
		lblVkrIncrease2.setBounds(280, 240, 304, 29);
		contentPane.add(lblVkrIncrease2);

		JLabel lblVklDecrease2 = new JLabel("NumPad (2) :  Potada");
		lblVklDecrease2.setFont(new Font("Tahoma", Font.ITALIC, 18));
		lblVklDecrease2.setBounds(280, 275, 304, 29);
		contentPane.add(lblVklDecrease2);

		JLabel ldlVklPoderespecial2 = new JLabel("NumPad (3) :  Poder");
		ldlVklPoderespecial2.setFont(new Font("Tahoma", Font.ITALIC, 18));
		ldlVklPoderespecial2.setBounds(280, 310, 304, 29);
		contentPane.add(ldlVklPoderespecial2);

		
		JButton btnExit = new JButton("SORTIR");
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

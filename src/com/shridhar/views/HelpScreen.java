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
		lblInstructions.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblInstructions.setBounds(157, 26, 189, 46);
		contentPane.add(lblInstructions);
		
		JLabel lblVkrightIncrease = new JLabel("VK_RIGHT :- Increase RYU speed");
		lblVkrightIncrease.setFont(new Font("Tahoma", Font.ITALIC, 18));
		lblVkrightIncrease.setBounds(24, 100, 304, 29);
		contentPane.add(lblVkrightIncrease);
		
		JLabel lblVkleftDecrease = new JLabel("VK_LEFT :- Decrease RYU speed");
		lblVkleftDecrease.setFont(new Font("Tahoma", Font.ITALIC, 18));
		lblVkleftDecrease.setBounds(24, 135, 304, 29);
		contentPane.add(lblVkleftDecrease);
		
		JLabel lblVkupIncrease = new JLabel("VK_UP :- Increase KEN speed");
		lblVkupIncrease.setFont(new Font("Tahoma", Font.ITALIC, 18));
		lblVkupIncrease.setBounds(24, 170, 304, 29);
		contentPane.add(lblVkupIncrease);
		
		JLabel lblVkdownDecrease = new JLabel("VK_DOWN :- Decrease KEN speed");
		lblVkdownDecrease.setFont(new Font("Tahoma", Font.ITALIC, 18));
		lblVkdownDecrease.setBounds(24, 205, 304, 29);
		contentPane.add(lblVkdownDecrease);
		
		JLabel lblVkrRyu = new JLabel("VK_R :- RYU Punch");
		lblVkrRyu.setFont(new Font("Tahoma", Font.ITALIC, 18));
		lblVkrRyu.setBounds(24, 240, 304, 29);
		contentPane.add(lblVkrRyu);
		
		JLabel lblVkkKen = new JLabel("VK_K :- KEN Punch");
		lblVkkKen.setFont(new Font("Tahoma", Font.ITALIC, 18));
		lblVkkKen.setBounds(24, 275, 304, 29);
		contentPane.add(lblVkkKen);
		
		JLabel lblVkfRyu = new JLabel("VK_F :- RYU Kick");
		lblVkfRyu.setFont(new Font("Tahoma", Font.ITALIC, 18));
		lblVkfRyu.setBounds(24, 310, 304, 29);
		contentPane.add(lblVkfRyu);
		
		JLabel lblVkpRyu = new JLabel("VK_P :- RYU Power");
		lblVkpRyu.setFont(new Font("Tahoma", Font.ITALIC, 18));
		lblVkpRyu.setBounds(24, 345, 304, 29);
		contentPane.add(lblVkpRyu);
		
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

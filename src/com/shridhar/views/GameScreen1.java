package com.shridhar.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import com.shridhar.personalGame.GameFrame1;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JProgressBar;
import javax.swing.JWindow;
import javax.swing.Timer;

public class GameScreen1 extends JFrame {

	private JPanel contentPane;

	Timer timer;
	int counter = 1;
	
	private void animation() {
			timer = new Timer(10, (e)->{
			progressBar.setValue(counter);
			counter++;
			if(counter>=100) {
				this.setVisible(false);
				this.dispose();
				timer.stop();
				GameFrame1 game = new GameFrame1();
				game.setVisible(true);
			}
		});
		timer.start();
	}
	
	private void help() {
		this.setVisible(false);
		this.dispose();
		HelpScreen help = new HelpScreen();
		help.setVisible(true);
		help.setLocationRelativeTo(null);
	}
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameScreen1 frame = new GameScreen1();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	JProgressBar progressBar = new JProgressBar();
	public GameScreen1() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 370, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(44, 150, 57));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ImageIcon icono = new ImageIcon("C:\\Users\\Lenovo\\Desktop\\CideFighter\\src\\com\\shridhar\\personalGame\\cide.jpg"); // Ruta de la imagen
        JLabel etiqueta = new JLabel(icono); 
		etiqueta.setBounds(100, 20, 150, 150); 
		contentPane.add(etiqueta);
		
		JButton btnNewButton = new JButton("HELP");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				help();
			}
		});
		btnNewButton.setBackground(Color.BLUE);
		btnNewButton.setForeground(Color.GREEN);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnNewButton.setBounds(80, 318, 181, 52);
		contentPane.add(btnNewButton);
		
		JButton btnStartGame = new JButton("START GAME");
		btnStartGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				GameFrame1 frame = new GameFrame1();
				animation();
			}
		});
		btnStartGame.setBackground(Color.BLUE);
		btnStartGame.setForeground(Color.GREEN);
		btnStartGame.setFont(new Font("Tahoma", Font.BOLD, 28));
		btnStartGame.setBounds(53, 237, 241, 59);
		contentPane.add(btnStartGame);
		
//		JProgressBar progressBar = new JProgressBar();
		progressBar.setBackground(Color.RED);
		progressBar.setStringPainted(true);
		progressBar.setBounds(35, 195, 278, 28);
		contentPane.add(progressBar);
		
		
	}
}

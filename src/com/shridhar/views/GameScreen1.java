package com.shridhar.views;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import com.shridhar.personalGame.GameFrame1;
import java.awt.event.*;


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
	
	private void customizePlayer() {
        this.setVisible(false);
        this.dispose();
        NameScreen customizeScreen = new NameScreen();
        customizeScreen.setVisible(true);
        customizeScreen.setLocationRelativeTo(null);
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

	JProgressBar progressBar = new JProgressBar();
	public GameScreen1() {//PANTALLA INICIAL
		setTitle("CIDE FIGHTER");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 370, 500);
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(44, 150, 57));//COLOR DE FONDO
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		//IMAGEN
		ImageIcon icono = new ImageIcon(getClass().getResource("/com/shridhar/personalGame/cide.jpg")); //Ruta de la imagen
        JLabel etiqueta = new JLabel(icono);
		etiqueta.setBounds(80, 5, 190, 190); //UBICACIÓN DE LA IMAGEN
		contentPane.add(etiqueta);
		
		//BARRA DE CARGA
		progressBar.setBackground(new Color(190, 190, 190));//COLOR DE FONDO
		progressBar.setStringPainted(true);
		progressBar.setBounds(35, 200, 278, 28);//UBICACIÓN
		progressBar.setVisible(false);
		contentPane.add(progressBar);
		
		
		//BOTÓN START
		JButton btnStartGame = new JButton("JUGAR");
		btnStartGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				animation();
				progressBar.setVisible(true);
			}
		});
		btnStartGame.setBackground(new Color(190, 190, 190));//COLOR FONDO BOTÓN
		btnStartGame.setBackground(new Color(235, 235, 235));//COLOR DE FONDO
		btnStartGame.setForeground(new Color(44, 150, 57));//COLOR LETRA
		btnStartGame.setFont(new Font("Tahoma", Font.BOLD, 28));
		btnStartGame.setBounds(53, 237, 241, 59);//UBICACIÓN DEL BOTÓN
		contentPane.add(btnStartGame);
		
		//BOTÓN MI JUGADOR
		JButton btnCustomize = new JButton("JUGADOR");
        btnCustomize.addActionListener(e -> customizePlayer());
        btnCustomize.setBackground(new Color(235, 235, 235));//COLOR DE FONDO
        btnCustomize.setForeground(new Color(44, 150, 57));//COLOR LETRA
        btnCustomize.setFont(new Font("Tahoma", Font.BOLD, 28));
        btnCustomize.setBounds(53, 318, 241, 50);//UBICACIÓN
        contentPane.add(btnCustomize);

		//BOTÓN HELP
		JButton btnNewButton = new JButton("AJUDA");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				help();
			}
		});
		btnNewButton.setBackground(new Color(190, 190, 190));//COLOR FONDO BOTÓN
		btnNewButton.setBackground(new Color(235, 235, 235));//COLOR DE FONDO
		btnNewButton.setForeground(new Color(44, 150, 57));//COLOR LETRA
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 28));
		btnNewButton.setBounds(80, 390, 181, 52);//UBICACIÓN DEL BOTÓN
		contentPane.add(btnNewButton);
		try {
			Font font = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/com/shridhar/personalGame/junegull.ttf"));
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(font);
			Font buttonFont = font.deriveFont(Font.BOLD, 28f);
			
			// Set the font for the buttons
			btnStartGame.setFont(buttonFont); 
			btnCustomize.setFont(buttonFont);
			btnNewButton.setFont(buttonFont);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
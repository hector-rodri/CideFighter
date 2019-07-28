package com.shridhar.personalGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.Timer;

import com.shridhar.personalGame.utils.GameUtils1;
import com.shridhar.personalGame.Sprite1;
import com.shridhar.personalGame.IPlayer1;

public class Board1 extends JPanel implements GameUtils1 {
	Camera1 camera1 = new Camera1();
	Ryu ryu;
	Ken ken;
	Timer timer;
//	Graphics g;
	
	private void gameLoop() {
		timer = new Timer(DELAY,(e)-> {
			repaint();
		}); 
			
		timer.start();
	}
	
	private void bindEvents() {
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				ryu.setSpeed(0);
				ken.setSpeed(0);
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				System.out.println("Key Press "+e.getKeyCode()+" "+e.getKeyChar());
				
				if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
					ryu.setSpeed(6);
				}
				if(e.getKeyCode()==KeyEvent.VK_UP) {
					ken.setSpeed(-6);
				}
				if(e.getKeyCode()==KeyEvent.VK_LEFT) {
					ryu.setSpeed(-6);
				}
				if(e.getKeyCode()==KeyEvent.VK_DOWN) {
					ken.setSpeed(6);
				}
				if(e.getKeyCode()==KeyEvent.VK_R) {
					ryu.setMode(IPlayer1.PUNCH);
					if(isCollide(ken, ryu)) {
						System.out.println("Game Over");
//						gameOver(g, "U Have completed the first round");
						ken.setMode(IPlayer1.FALL);
						ken.setKendamage(true);
//						ryu.setRyudamage(true);
					}
				}
				if(e.getKeyCode()==KeyEvent.VK_K) {
					ken.setMode(IPlayer1.PUNCH);
					if(isCollide(ken, ryu)) {
						System.out.println("Game Over");
						ryu.setMode(IPlayer1.FALL);
						ryu.setRyudamage(true);
//						ken.setKendamage(true);
//					    gameOver(g, "U Have completed the first round");
					}
				}
				if(e.getKeyCode()==KeyEvent.VK_F) {
					ryu.setMode(IPlayer1.KICK);
					if(isCollide(ken, ryu)) {
						System.out.println("Game Over");
						for(int i=0; i<=100; ++i) {
							if(i==100) {
						ken.setMode(IPlayer1.DEFENCE);
							}
						}
//					    gameOver(g, "U Have completed the first round");
					}
				}
				if(e.getKeyCode()==KeyEvent.VK_P) {
					ryu.setMode(IPlayer1.POWER);;
				}
			}
		});
	}
	
	private int timeleft=TIME_LEFT;
	int mydelay=1;
	private void drawTime(Graphics g) {
		if(mydelay==30) {
			timeleft--;
			mydelay=0;
		}
		if(timeleft==0) {
			gameOver(g,"Game Over Time Up");
		}
		g.setColor(Color.red);
		g.setFont(new Font("Arial", Font.BOLD, 32));
		g.drawString(String.valueOf(timeleft), FRAME_WIDTH-70, 60);		
		mydelay++;
		
	}
	
	private void gameOver(Graphics g,String reason) {
		g.setColor(Color.red);
		g.setFont(new Font("Arial", Font.BOLD, 32));
		g.drawString(reason, (FRAME_WIDTH/2)-150, FRAME_HEIGHT/2);
		timer.stop();
	}
	
	private boolean isCollide(Ken ken,Ryu ryu) {
//		boolean collide=false;
		int xDistance=Math.abs(ken.getX()-ryu.getX());
		int yDistance=Math.abs(ken.getY()-ryu.getY());
		int width = ken.getW();
		int height = Math.max(ken.getH(), ryu.getH());
		return xDistance<=width-10 && yDistance<=height-40;
	}
	
	
	public Board1() {
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
//		setBackground(Color.YELLOW);
		ryu = new Ryu(2, 100);
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
		drawTime(g);
		if(ken.isKendamage() == true) {
			 Ken.setKencounter(ken.drawProgress(g)); 
			 if(Ken.getKencounter()==0) {
				 gameOver(g, "RYU WIN");
			 }
			ken.setKendamage(false);
		}
		g.setColor(Color.YELLOW);
		g.fillRect(400, 40, Ken.getKencounter(), 40);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.BOLD, 32));
		g.drawString("KEN", 380, 40);
		
		
		if(ryu.isRyudamage() == true) {
			 Ryu.setRyucounter(ryu.drawProgress(g)); 
			 if(Ryu.getRyucounter()==0) {
				 gameOver(g, "KEN WIN");
			 }
			ryu.setRyudamage(false);
		}
		g.setColor(Color.RED);
		g.fillRect(40, 40, Ryu.getRyucounter(), 40);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.BOLD, 32));
		g.drawString("RYU", 20, 40);

	}

}

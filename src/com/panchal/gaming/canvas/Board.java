package com.panchal.gaming.canvas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.panchal.gaming.sprites.Camera;
import com.panchal.gaming.sprites.KenPlayer;
import com.panchal.gaming.sprites.Power;
import com.panchal.gaming.sprites.PowerEffect;
import com.panchal.gaming.sprites.RyuPlayer;
import com.panchal.gaming.utils.GameConstants;
import com.panchal.gaming.utils.PlayerConstants;

public class Board extends JPanel implements GameConstants , PlayerConstants {//for all designing part ,layout part 
//JPanel for printing 	
	BufferedImage imageBg;//for load the bg image
	private RyuPlayer ryuplayer;
	private KenPlayer kenplayer;
	private Timer timer;
	private Power ryuPower;
	private Power kenPower;
	private boolean isGameOver;
	private Camera camera;
	private void  loadPower() {
		ryuPower=new Power(20,"Ryu".toUpperCase());
		kenPower=new Power(GWIDTH/2+100,"Ken".toUpperCase());
	}
	private void paintPower(Graphics pen) {
		ryuPower.printBox(pen);
		kenPower.printBox(pen);
		
	}
	public Board() throws IOException{
		camera = new Camera();
		//loadBackgroundImage();
		ryuplayer = new RyuPlayer();
		kenplayer = new KenPlayer();
		setFocusable(true);
		bindEvents();
		gameLoop();
		loadPower();
		}
	public void Collision() {
		if(isCollide()) {
			if(ryuplayer.isAttacking()) {
				kenplayer.setCurrentMove(DAMAGE);
				kenPower.setHealth();
			}
			if(kenPower.getHealth()<=0||ryuPower.getHealth()<=0) {
				isGameOver=true;
			}
			ryuplayer.setCollide(true);
			//System.out.println("Collision...");
			ryuplayer.setSpeed(0);
			
		}
		else {
			ryuplayer.setSpeed(SPEED);
		}
	}
	private void printMessage(Graphics pen) {
		pen.setColor(Color.RED);
		pen.setFont(new Font("times",Font.BOLD, 40));
		pen.drawString("Game Over",GWIDTH/2-50,GHEIGHT/2);
		
	}
	private boolean isCollide() {
		int xDistance=Math.abs(ryuplayer.getX()-kenplayer.getX());
		int yDistance=Math.abs(ryuplayer.getY()-kenplayer.getY());
		int maxW=Math.max(ryuplayer.getW(), kenplayer.getW());
		int maxH=Math.max(ryuplayer.getH(), kenplayer.getH());
		return xDistance<=maxW-40 && yDistance<=maxH;
	}
	private void gameLoop() {
		
		//Thread trigger 
		timer=new Timer(150,new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				repaint();
				ryuplayer.fall();
				Collision();
				
			}
			
		});
		timer.start();
	}
	private void printPower(Graphics pen) {
		//ryuplayer.getPowers();
		for(PowerEffect power : ryuplayer.getPowers()){
			power.printPower(pen);
		}
	}
	private void bindEvents() {
		this.addKeyListener(new KeyAdapter(){

			

			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_J) {
				ryuplayer.setSpeed(-SPEED);
				camera.setSpeed(-SPEED);
				ryuplayer.setCurrentMove(WALK);
				ryuplayer.move();
				camera.move();
				
				ryuplayer.setCollide(false);
				//repaint();
			}
				else if(e.getKeyCode()==KeyEvent.VK_L) {
					if(ryuplayer.isCollide()) {
						
						ryuplayer.setSpeed(0);
						camera.setSpeed(0);
					}
					else {
						ryuplayer.setCollide(false);
						ryuplayer.setSpeed(SPEED);
						camera.setSpeed(SPEED);
					}
					
					ryuplayer.setCurrentMove(WALK);
					ryuplayer.move();
					camera.move();
					//repaint();
					
					}
				else if(e.getKeyCode()==KeyEvent.VK_K) {
					ryuplayer.setAttacking(true);
					ryuplayer.setCurrentMove(KICK);
				}
				else if(e.getKeyCode()==KeyEvent.VK_P) {
					ryuplayer.setAttacking(true);
					ryuplayer.setCurrentMove(PUNCH);
				}
				else if(e.getKeyCode()==KeyEvent.VK_A) {
					ryuplayer.setAttacking(true);
					ryuplayer.setCurrentMove(POWEREFFECT);
					ryuplayer.addPower();
				}
				
				else if(e.getKeyCode()==KeyEvent.VK_SPACE) {
					ryuplayer.jump();
				}
				else if(e.getKeyCode()==KeyEvent.VK_LEFT) {//KEN
					kenplayer.setSpeed(-SPEED);
					kenplayer.move();
					repaint();
					
					}
				else if(e.getKeyCode()==KeyEvent.VK_RIGHT) {//KEN
					kenplayer.setSpeed(SPEED);
					kenplayer.move();
					repaint();
					
					}
				}

			@Override
			public void keyReleased(KeyEvent e) {
				ryuplayer.setSpeed(0);
				
			}
			
		});
	}
	@Override 
	public void paintComponent(Graphics pen) {
		/* paintComponent is the method of JComponent 
		 * it is used for paint or Randering 
		 * and Graphics is the type of pen that can be help for Randering the data 
		*/
		//pen.drawImage(imageBg, WIDTH, SOMEBITS, PROPERTIES, HEIGHT, FRAMEBITS, ERROR, ALLBITS, ABORT, getFocusCycleRootAncestor())
		super.paintComponent(pen);
		printBackgroundImage(pen);
		ryuplayer.printPlayer(pen);
		kenplayer.printPlayer(pen);
		paintPower(pen);//for ryuplayer power action
		printPower(pen);// for ryupower ball 
		if(isGameOver) {
			printMessage(pen);
			timer.stop();
		}
	}
	private void printBackgroundImage(Graphics pen) {//for showing / background image 
		pen.drawImage(camera.defaultImage(),0,0,1400,900,null);// null-> image is fixed 
		
	}
	private void loadBackgroundImage() {// for load the bg image 
		try {
			imageBg=ImageIO.read(Board.class.getResource("bg.jpg"));
		}
		catch(Exception ex) {
			System.out.println("Background Image Loading Fail....");
			System.exit(0);
		}
		
	}

}

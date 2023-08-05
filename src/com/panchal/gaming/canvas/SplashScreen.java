package com.panchal.gaming.canvas;

import java.io.File;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JWindow;

import jaco.mp3.player.MP3Player;
// pixabay website for download sound 
// 3 party api/web service JACoMP3 for playing the sound besause java not have any sound library
public class SplashScreen extends JWindow { // for full size like no minimize x button 
	private JLabel label =new JLabel();//for put the image 
	private MP3Player player;
	private void playSound() {
		player=new MP3Player(SplashScreen.class.getResource("sound.mp3.mp3"));
		player.play();
	}
	public SplashScreen() {
		setSize(1200,900);
		Icon icon =new ImageIcon(SplashScreen.class.getResource("spalsh.jpg"));//Icon interface and ImageIcon is class 
		label.setIcon(icon);
		this.add(label);// is frame me add kr do is label ko
		setLocationRelativeTo(null);
		setVisible(true);
		playSound();
		try {
			Thread.sleep(10000);
			setVisible(false);
			dispose();
			player.stop();//stop bg music 
			GameFrame obj=new GameFrame();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		SplashScreen screen =new SplashScreen();
	}

}

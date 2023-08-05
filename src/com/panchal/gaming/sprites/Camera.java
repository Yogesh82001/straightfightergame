package com.panchal.gaming.sprites;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.panchal.gaming.canvas.Board;

public class Camera extends Sprite {
	private int moveBack=0;
	public Camera() {
		x=11;
		y=300;
		w=1400;
		h=900;
		try {
			image=ImageIO.read(Board.class.getResource("bg.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void move() {
		outOfScreen();
		if(moveBack==1) {
			speed=10;
		}
		else if(moveBack==2) {
			speed =-10;
		}
		x=x+speed;
	}
	public void outOfScreen() {
		if(x<=10) {//no move allow left 
			moveBack=1;
		}
		else if(x>=3000-1400) {//no move allow right 
			moveBack=2;
		}
		else {// both move allow 
			moveBack=0;
		}
//		if(x>=3000-1400||x<=10) {
//			speed=0;              fault ho gya tha yha  
//		}
	}
	@Override
	public BufferedImage defaultImage() {
		// TODO Auto-generated method stub
		BufferedImage Image=image.getSubimage(x,y,w,h);
		return Image;
	}

}

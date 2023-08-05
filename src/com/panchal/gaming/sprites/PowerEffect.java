package com.panchal.gaming.sprites;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class PowerEffect extends Sprite {
	PowerEffect(int x,int y,BufferedImage img){
		this.image=img;
		this.x=x;
		this.y=y;
		h=100;
		w=100;
		speed=50;
	}

	@Override
	public BufferedImage defaultImage() {
		// TODO Auto-generated method stub
		return image.getSubimage(6,3105,73,49);
	}
	public void printPower(Graphics pen) {
		pen.drawImage(defaultImage(),x,y,w,h,null);
		move();
	}


}

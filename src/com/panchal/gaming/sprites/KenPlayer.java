package com.panchal.gaming.sprites;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class KenPlayer extends Sprite{
	private BufferedImage [] damageImages=new BufferedImage[6];
	
	public KenPlayer() throws IOException {
		x = GWIDTH-500;
	
		y=FLOOR-h;
		speed=0;
		image =ImageIO.read(KenPlayer.class.getResource(KEN_IMAGE));
		loadDamageImage();
		
	}
	private void loadDamageImage() {
		damageImages[0]=image.getSubimage(3,127,33,54);
		damageImages[1]=image.getSubimage(40,131,36,54);
		damageImages[2]=image.getSubimage(77,131,37,54);
		damageImages[3]=image.getSubimage(108,132,39,54);
		damageImages[4]=image.getSubimage(149,131,45,54);
		damageImages[5]=image.getSubimage(196,129,59,54);
		
	}
	public BufferedImage damageImage() {
		if(imageIndex >= 5) {
			imageIndex =0;
			currentMove=STANDING;
		}
		BufferedImage img=damageImages[imageIndex];
		imageIndex++;
		return img;
		
	}
	@Override
	public BufferedImage defaultImage() {
		if(currentMove==DAMAGE) {
			return damageImage();
			
		}
	BufferedImage subimage=	image.getSubimage(68,5,33,59);// big image to sub small image 
	return subimage;
	}

}

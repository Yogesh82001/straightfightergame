package com.panchal.gaming.sprites;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class RyuPlayer extends Sprite  {
//	private int x;
//	private int y;
//	private int w;
//	private int h;
//	private BufferedImage image;
	private BufferedImage walkImages[]=new BufferedImage[6];
	private BufferedImage standingImages[]=new BufferedImage[8];
	private BufferedImage kickImages[]=new BufferedImage[6];
	private BufferedImage punchImages[]=new BufferedImage[6];
	private BufferedImage powerEffectImages [] =new BufferedImage[13];
	public RyuPlayer() throws IOException{
		x=300;
	
		y=FLOOR-h-20;
		speed=0;
		currentMove=STANDING;
		image =ImageIO.read(RyuPlayer.class.getResource(RYU_IMAGE));
		loadWalkImages();
		loadstandingImages();
		loadKickImages();
		loadPunchImages();
		loadPowerEffectImages();
	}
		public void jump() {
			if(!isjump) {
				force=DEFAULT_FORCE;
				y=y+force;
				isjump=true;
			}
		
		}
		public void fall() {
			if(y>=(FLOOR-h)) {
				isjump=false;
			return;
			}
			force=force+GRAVITY;
			y=y+force;
			
		}
	private void loadWalkImages() {
		walkImages[0]=image.getSubimage(62,232,74,107);
		walkImages[1]=image.getSubimage(141,230,74,107);
		walkImages[2]=image.getSubimage(223,228,74,107);
		walkImages[3]=image.getSubimage(296,229,74,107);
		walkImages[4]=image.getSubimage(368,231,74,107);
		walkImages[5]=image.getSubimage(449,223,74,107);
	}
	private void loadstandingImages() {
		standingImages[0]=image.getSubimage(11,1,74,107);
		standingImages[1]=image.getSubimage(84,2,74,107);
		standingImages[2]=image.getSubimage(164,4,74,107);
		standingImages[3]=image.getSubimage(241,2,74,107);
		standingImages[4]=image.getSubimage(315,6,74,107);
		standingImages[5]=image.getSubimage(389,4,74,107);
		standingImages[6]=image.getSubimage(458,4,74,107);
		standingImages[7]=image.getSubimage(531,7,74,107);
	}
	private void loadKickImages() {
		kickImages[0]=image.getSubimage(35,1042,74,107);
		kickImages[1]=image.getSubimage(118,1039,74,107);
		kickImages[2]=image.getSubimage(200,1042,122,107);
		kickImages[3]=image.getSubimage(322,1042,76,107);
		kickImages[4]=image.getSubimage(403,1046,76,107);
		kickImages[5]=image.getSubimage(483,1044,100,107);
		
	}
	private void loadPunchImages() {
		punchImages[0]=image.getSubimage(22,815,76,107);
		punchImages[1]=image.getSubimage(106,817,76,107);
		punchImages[2]=image.getSubimage(185,816,122,107);
		punchImages[3]=image.getSubimage(305,818,87,107);
		punchImages[4]=image.getSubimage(400,814,114,107);
		punchImages[5]=image.getSubimage(514,818,82,107);

	}
	private void loadPowerEffectImages() {
		powerEffectImages[0]=image.getSubimage(41,1592,90,103);
		powerEffectImages[1]=image.getSubimage(136,1592,98,103);
		powerEffectImages[2]=image.getSubimage(239,1596,98,103);
		powerEffectImages[3]=image.getSubimage(11,1700,136,103);
		powerEffectImages[4]=image.getSubimage(140,1697,110,103);
		powerEffectImages[5]=image.getSubimage(242,1692,124,103);
		powerEffectImages[6]=image.getSubimage(366,1697,102,103);
		powerEffectImages[7]=image.getSubimage(466,1698,133,103);
		powerEffectImages[8]=image.getSubimage(2,1804,121,103);
		powerEffectImages[9]=image.getSubimage(122,1805,121,103);
		powerEffectImages[10]=image.getSubimage(244,1807,130,103);
		powerEffectImages[11]=image.getSubimage(374,1810,112,103);
		powerEffectImages[12]=image.getSubimage(487,1806,150,107);
	}
	private BufferedImage powerEffectImage() {
		if(imageIndex > 12) {
			imageIndex =0;
			currentMove=STANDING;
		}
		BufferedImage img=powerEffectImages[imageIndex];
		imageIndex++;
		return img;
	}
	private BufferedImage walkImage() {
		if(imageIndex > 5) {
			imageIndex =0;
			currentMove=STANDING;
		}
		BufferedImage img=walkImages[imageIndex];
		imageIndex++;
		return img;
	}
	private BufferedImage kickImage() {
		if(imageIndex > 5) {
			imageIndex =0;
			currentMove=STANDING;
			isAttacking=false;
		}
		BufferedImage img=kickImages[imageIndex];
		imageIndex++;
		return img;
	}
	private BufferedImage punchImage() {
		if(imageIndex > 5) {
			imageIndex =0;
			currentMove=STANDING;
			isAttacking=false;
		}
		BufferedImage img=punchImages[imageIndex];
		imageIndex++;
		return img;
	}
	
		
	
	private BufferedImage standingImage() {
		if(imageIndex > 7) {
			imageIndex =0;
		}
		BufferedImage img=standingImages[imageIndex];
		imageIndex++;
		return img;
	}
	private ArrayList<PowerEffect> powers =new ArrayList<>();
	
	public ArrayList<PowerEffect> getPowers(){
		return powers;
	}
	public void addPower() {
		// TODO Auto-generated method stub
		powers.add(new PowerEffect(x+w,y+h/2-70,image));
	}
	
	
	@Override
	public BufferedImage defaultImage() {
//	BufferedImage subimage=	image.getSubimage(184, 123, 76, 100	);// big image to sub small image 
//	return subimage;
		 if(currentMove==WALK) {
			return walkImage();
		}
		else if(currentMove==PUNCH) {
			return punchImage();
		}
		else if(currentMove==KICK) {
			return kickImage();
		}
		else if(currentMove==POWEREFFECT) {
			return powerEffectImage();
		}
		else {
			return standingImage();
		}
		}
	
	
	public void printPlayer(Graphics pen) {
		pen.drawImage(defaultImage(),x,y,w,h,null);
	}
	

}

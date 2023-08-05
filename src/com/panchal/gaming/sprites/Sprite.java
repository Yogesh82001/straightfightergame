package com.panchal.gaming.sprites;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.panchal.gaming.utils.GameConstants;
import com.panchal.gaming.utils.PlayerConstants;

public abstract class Sprite implements GameConstants ,PlayerConstants{
	protected int x;
	protected  int y;
	protected  int w;
	protected  int h;
	protected int speed;
	protected int imageIndex;
	protected int currentMove;
	protected int force;
	protected boolean isjump;
	protected boolean isCollide;
	protected boolean isAttacking;
	protected int health;
	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	
	public boolean isAttacking() {
		return isAttacking;
	}
	public void setAttacking(boolean isAttacking) {
		this.isAttacking = isAttacking;
	}
	public boolean isCollide() {
		return isCollide;
	}
	public void setCollide(boolean isCollide) {
		this.isCollide = isCollide;
	}
	public Sprite() {
		h=200;
		w=200;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getW() {
		return w;
	}
	public void setW(int w) {
		this.w = w;
	}
	public int getH() {
		return h;
	}
	public void setH(int h) {
		this.h = h;
	}
	protected  BufferedImage image;
	public abstract BufferedImage defaultImage(); 
	public int getCurrentMove() {
		return currentMove;
	}
	public void setCurrentMove(int currentMove) {
		this.currentMove = currentMove;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public void move() {
		x=x+speed;
	}
	public void printPlayer(Graphics pen) {
		pen.drawImage(defaultImage(),x,y,w,h,null);
	}
}
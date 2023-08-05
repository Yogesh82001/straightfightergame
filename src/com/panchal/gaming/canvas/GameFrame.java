package com.panchal.gaming.canvas;

import java.io.IOException;

import javax.swing.JFrame;

import com.panchal.gaming.utils.GameConstants;

public class GameFrame extends JFrame implements GameConstants{
	public GameFrame() throws IOException {
		setResizable(false);// window size can't be change 
		setTitle(TITLE);//for set the title
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//for close the window
		setSize(GWIDTH,GHEIGHT);//weight and height of window 
		setLocationRelativeTo(null);//for mid of screen 
		Board board =new Board();// for Board calling --> constructor call
		add(board);//Board added in frame (window)
		setVisible(true);// for show the window 
		
	}
}                                                      
//	public static void main(String[] args) {
//		try {
//			GameFrame obj=new GameFrame ();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//} spanshcreen work now 
	

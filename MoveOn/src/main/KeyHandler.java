package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
	
	public boolean upPressed, downPressed, leftPressed, rightPressed,enterPressed;
	//Debug
	boolean showDebugText = Boolean.FALSE;
	
	GamePanel gp;
	
	public KeyHandler(GamePanel gp) {
		this.gp = gp;
	}

	@Override
	public void keyTyped(KeyEvent e) {		
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		// Title state
		if(gp.gameState == gp.titleState){
			titleState(code);
		}
		// play state
		else if(gp.gameState == gp.playState){
			playState(code);
		}
		// Pause state
		else if(gp.gameState == gp.pauseState){
			pauseState(code);
			
		}
		// Dialogue state
		else if(gp.gameState == gp.dialougeState){
			dialogueState(code);
		}		
		//CHARACTER STATE
		else if(gp.gameState == gp.characterState) {
			characterState(code);
		}
			
	}
	
	public void titleState(int code) {

		if(gp.ui.titleScreenState ==0){
			if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
			gp.ui.commandNum--;
			if(gp.ui.commandNum<0){
				gp.ui.commandNum=2;
			}
		}
		if(code == KeyEvent.VK_S
		|| code == KeyEvent.VK_DOWN) {
			gp.ui.commandNum++;
			if(gp.ui.commandNum>2){
				gp.ui.commandNum=0;
			}	
		}
		if(code == KeyEvent.VK_ENTER) {
			if(gp.ui.commandNum==0){
				gp.ui.titleScreenState =1;
			}	
			if(gp.ui.commandNum==1){
				// gp.gameState = add later
			}	
			if(gp.ui.commandNum==2){
				System.exit(0);
			}	
		}
		}
		else if(gp.ui.titleScreenState ==1){
		if(code == KeyEvent.VK_W 
			|| code == KeyEvent.VK_UP) {
			gp.ui.commandNum --;
			if(gp.ui.commandNum<0){
				gp.ui.commandNum=3;
			}
		}
		if(code == KeyEvent.VK_S
				|| code == KeyEvent.VK_DOWN) {
			gp.ui.commandNum ++;
			if(gp.ui.commandNum>3){
				gp.ui.commandNum=0;
			}	
		}
		if(code == KeyEvent.VK_ENTER) {
			if(gp.ui.commandNum==0){
				System.err.println("fighter stuff!");
				gp.gameState = gp.playState;
//				gp.playMusic(0);
			}	
			if(gp.ui.commandNum==1){
				System.err.println("thief stuff!");
				gp.gameState = gp.playState;
//				gp.playMusic(0);
			}	
			if(gp.ui.commandNum==2){
				System.err.println("sorcerer stuff!");
				gp.gameState = gp.playState;
//				gp.playMusic(0);
			}	
			if(gp.ui.commandNum==3){
				gp.ui.titleScreenState = 0;
				gp.ui.commandNum=0;
			}
		}}

	
	}

	public void playState(int code) {

		
		if(code == KeyEvent.VK_W 
			|| code == KeyEvent.VK_UP) {
		upPressed = Boolean.TRUE;
	}
	if(code == KeyEvent.VK_S
			|| code == KeyEvent.VK_DOWN) {
		downPressed = Boolean.TRUE;		
	}
	if(code == KeyEvent.VK_A
			|| code == KeyEvent.VK_LEFT) {
		leftPressed = Boolean.TRUE;
	}
	if(code == KeyEvent.VK_D
			|| code == KeyEvent.VK_RIGHT) {
		rightPressed = Boolean.TRUE;
	}
	if(code == KeyEvent.VK_P) {
		gp.gameState= gp.pauseState;
		
	}
	if(code == KeyEvent.VK_ENTER) {
		enterPressed = true;
		
	}
	if(code == KeyEvent.VK_C) {
		gp.gameState = gp.characterState;
	}
	
	if(code == KeyEvent.VK_T) {
		if(!showDebugText) {
			showDebugText = Boolean.TRUE;
		}else {
			showDebugText = Boolean.FALSE;
		}
	}
	
	if(code == KeyEvent.VK_R) {
		gp.tileM.loadMap("/maps/worldV2.txt");
	}
	
	}
	
	public void pauseState(int code) {
		if(code == KeyEvent.VK_P) {
			gp.gameState= gp.playState;
			
		}
	}
	
	public void dialogueState(int code) {
		if(code == KeyEvent.VK_ENTER) {
			gp.gameState= gp.playState;
			
		}
	}
	
	public void characterState(int code) {
		if(code == KeyEvent.VK_C) {
			gp.gameState = gp.playState;
		}
		
		if(code == KeyEvent.VK_W) {
			if(gp.ui.slotRow!=0) {
				gp.ui.slotRow--;
				gp.playSoundEffect(9);
			}
			
		}
		
		if(code == KeyEvent.VK_A) {
			if(gp.ui.slotCol != 0) {
				gp.ui.slotCol--;
				gp.playSoundEffect(9);
			}
			
		}
		
		if(code == KeyEvent.VK_S) {
			
			if(gp.ui.slotRow!=3) {
				gp.ui.slotRow++;
				gp.playSoundEffect(9);
			}
			
		}
		
		if(code == KeyEvent.VK_D) {
			if(gp.ui.slotCol!=4) {
				gp.ui.slotCol++;
				gp.playSoundEffect(9);
			}
			
		}


	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		int code = e.getKeyCode();
		if(code == KeyEvent.VK_W
				|| code == KeyEvent.VK_UP) {
			upPressed = Boolean.FALSE;
		}
		if(code == KeyEvent.VK_S
				|| code == KeyEvent.VK_DOWN) {
			downPressed = Boolean.FALSE;		
		}
		if(code == KeyEvent.VK_A
				|| code == KeyEvent.VK_LEFT) {
			leftPressed = Boolean.FALSE;
		}
		if(code == KeyEvent.VK_D
				|| code == KeyEvent.VK_RIGHT) {
			rightPressed = Boolean.FALSE;
		}
		
	}

}

package tile_interactive;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import entity.Entity;
import main.GamePanel;

public class InteractiveTile extends Entity{
	
	GamePanel gp;
	public boolean destructible = Boolean.FALSE;
	
	public InteractiveTile(GamePanel gp, int col, int row) {
		super(gp);
		this.gp = gp;
	}
	public boolean isCorrectItem(Entity entity) {
		boolean isCorrectItem = Boolean.FALSE;
		
		return isCorrectItem;
	}
	
	public void playSE() {
		
	}
	
	public InteractiveTile getDestroyedForm() {
		InteractiveTile tile = null;
		return tile;
	}
	
	
	public void update() {
		if(invincible) {
			invincibleCounter++;
			if(invincibleCounter > 20) {
				invincible = Boolean.FALSE;
				invincibleCounter = 0;
			}
		}
	}
	
	public void draw(Graphics2D g2) {
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;
        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX 
                && worldX - gp.tileSize < gp.player.worldX + gp.player.screenX 
                && worldY + gp.tileSize > gp.player.worldY - gp.player.screenY 
                && worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
        	
            g2.drawImage(down1, screenX, screenY, gp.tileSize,gp.tileSize,null);
        }
	}

}

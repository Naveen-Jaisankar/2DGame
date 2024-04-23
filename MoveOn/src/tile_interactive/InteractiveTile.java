package tile_interactive;

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

}

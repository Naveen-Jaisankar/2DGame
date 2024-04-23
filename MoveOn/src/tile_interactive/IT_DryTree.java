package tile_interactive;

import entity.Entity;
import main.GamePanel;

public class IT_DryTree extends InteractiveTile{
	
	GamePanel gp;

	public IT_DryTree(GamePanel gp, int col, int row) {
		super(gp,col,row);
		this.gp = gp;
		
		this.worldX = gp.tileSize*col;
		this.worldY = gp.tileSize*row;
		
		down1 = setup("/tiles_interactive/drytree",gp.tileSize,gp.tileSize);
		destructible = Boolean.TRUE;
		life =3;
	}
	
	public boolean isCorrectItem(Entity entity) {
		boolean isCorrectItem = Boolean.FALSE;
		
		if(entity.currentWeapon.type == type_axe) {
			isCorrectItem = Boolean.TRUE;
		}
		return isCorrectItem;
	}
	
	public void playSE() {
		gp.playSoundEffect(11);	
	}
	
	public InteractiveTile getDestroyedForm() {
		InteractiveTile tile = new IT_Trunk(gp, worldX/gp.tileSize, worldY/gp.tileSize);
		return tile;
	}

}

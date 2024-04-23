package object;

import com.google.gson.Gson;

import entity.Entity;
import main.GamePanel;

public class OBJ_Potion_Red extends Entity{
    GamePanel gp;
    int value= 5;

	public OBJ_Potion_Red(GamePanel gp) {
		super(gp);
        this.gp=gp;
		type=type_consumable;
		name = "Red Potion";
		down1 = setup("/objects/potion_red",gp.tileSize,gp.tileSize);
		description = "[" + name + "]\nHeals your life by "+value+".";
	}
    public void use(Entity entity){
        gp.gameState = gp.dialougeState;
        gp.ui.currentDialogue = "You consumed the "+name+" !\n"+
        "Your life has been increased by "+ value+".";
        entity.life+=value;
        if(gp.player.life> gp.player.maxLife){
            gp.player.life = gp.player.maxLife;
        }
        gp.playSoundEffect(2);
    }

}

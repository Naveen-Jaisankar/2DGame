package object;
import entity.Entity;
import main.GamePanel;

public class OBJ_Chest extends Entity {
	
	// GamePanel gp;
    public OBJ_Chest(GamePanel gp){
    	super(gp);
        name = "Chest";
        down1 = setup("/objects/chest",gp.tileSize,gp.tileSize);
        setDialogue();
        
    }
    public void setDialogue(){
		dialogues[0] = "Hello!, Lad.";
		dialogues[1] = "You are at home now";
		dialogues[2] = "You need to go to school and you have four possible transportation";
		dialogues[3] = "Choose the ones that leave you the least carbon footprint!";
        dialogues[4] = "Good luck on you.";
	}
    
}

package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Axe  extends Entity{

    public OBJ_Axe(GamePanel gp) {
        super(gp);
        type=type_axe;
        name = "Woodcutter's axe";
        down1 = setup("/objects/axe", gp.tileSize, gp.tileSize);
        attackValue=2;
        attackArea.height = 30;
        attackArea.width = 30;
        description = "[" + name + "]\nA bit rusty but \ncan still cut some trees!.";
    }

}

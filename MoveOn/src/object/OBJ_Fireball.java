package object;

import java.awt.Color;

import entity.Entity;
import entity.Projectile;
import main.GamePanel;
import tile_interactive.IT_Trunk;
import tile_interactive.InteractiveTile;

public class OBJ_Fireball extends Projectile {
    GamePanel gp;

    public OBJ_Fireball(GamePanel gp) {
        super(gp);
        this.gp = gp;

        name = "Fireball";
        speed = 5;
        maxLife = 80;
        life = maxLife;
        attack=2;
        useCost = 2;
        alive = false;

        getImage();
    }

    public void getImage(){
        up1= setup("/projectile/fireball_up_1", gp.tileSize, gp.tileSize);
        up2= setup("/projectile/fireball_up_2", gp.tileSize, gp.tileSize);
        down1= setup("/projectile/fireball_down_1", gp.tileSize, gp.tileSize);
        down2= setup("/projectile/fireball_down_1", gp.tileSize, gp.tileSize);
        left1= setup("/projectile/fireball_left_1", gp.tileSize, gp.tileSize);
        left2= setup("/projectile/fireball_left_1", gp.tileSize, gp.tileSize);
        right1= setup("/projectile/fireball_right_1", gp.tileSize, gp.tileSize);
        right2= setup("/projectile/fireball_right_1", gp.tileSize, gp.tileSize);

    }
    public boolean haveResource(Entity user){
        boolean haveResource = false;
        if(user.mana>0){
            haveResource=true;
        }
        return haveResource;
        
    }

    public void subtractResource(Entity user){
        user.mana -= useCost;
    }
    
    public InteractiveTile getDestroyedForm() {
		InteractiveTile tile = new IT_Trunk(gp, worldX/gp.tileSize, worldY/gp.tileSize);
		return tile;
	}
	
	public Color getParticleColor() {
		Color color = new Color(240,50,0);
		return color;
	}
	
	public int getParticleSize() {
		int size = 10; //pixels
		return size;
	}
	
	public int getParticleSpeed() {
		int speed =1;
		return speed;
	}
	
	public int getParticleMaxLife() {
		int maxLife = 20;
		return maxLife;
	}

}

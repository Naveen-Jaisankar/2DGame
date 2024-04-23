package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Config {
    GamePanel gp;
    public Config(GamePanel gp){

        this.gp = gp;
    }
    public void saveConfig(){
            try (BufferedWriter bw = new BufferedWriter(new FileWriter("config.txt"))) {
                // full screen
                if(gp.fullScreenOn == true){
                    bw.write("On");
                }
                if(gp.fullScreenOn == false){
                    bw.write("Off");
                }
                bw.newLine();

                // music volume
                bw.write(String.valueOf(gp.music.volumeScale));
                bw.newLine();

                // sound effects volume
                bw.write(String.valueOf(gp.soundEffects.volumeScale));
                bw.newLine();

                bw.close();

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
          
    }

    public void loadConfig(){
        try (BufferedReader br = new BufferedReader(new FileReader("config.txt"))) {
            String s = br.readLine();
            

            // full screen
            if(s.equals("On")){
                gp.fullScreenOn = true;
            }
            if(s.equals("Off")){
                gp.fullScreenOn = false;
            }

            // music
            s = br.readLine();
            gp.music.volumeScale = Integer.parseInt(s);

            // sound effects
            s = br.readLine();
            gp.soundEffects.volumeScale = Integer.parseInt(s);

            br.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}

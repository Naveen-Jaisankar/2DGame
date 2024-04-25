package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class GameResults {

    GamePanel gp;
    public GameResults(GamePanel gp){

        this.gp = gp;
    }
    public void saveResults(){
            try (BufferedWriter bw = new BufferedWriter(new FileWriter("gameResults.txt"))) {
                // full screen
                // gp.ui.currentDialogue
                for(String line : gp.ui.currentDialogue.split("\n")){
                    bw.write(String.valueOf(line));
                    bw.newLine();
                }

                bw.close();

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
          
    }


}




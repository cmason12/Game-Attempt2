/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package levelattempt2;

import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 *
 * @author cmason12
 */
public class NewDialogue {
    private Text dialogue;
    private HBox holderBox = new HBox();
    
    NewDialogue( String text){
        dialogue = new Text();
    holderBox.setMaxWidth(700);
    holderBox.setMaxHeight(200);
    //dialogue.setFill(Color.Black);
    //holderBox.setStyle("fx-background: black;");/* +
/*
"    -fx-stroke-dash-array: 12 2 4 2;" +
"    -fx-stroke-dash-offset: 6;" +
"    -fx-stroke-line-cap: butt;");*/
    dialogue.setText(text);
    dialogue.setFill(Color.WHITE);
    dialogue.setFont(Font.font("Verdana", FontWeight.BOLD, 32));
    
    DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(5.0);
        dropShadow.setColor(Color.BLACK);
    dialogue.setEffect(dropShadow);
    holderBox.getChildren().add(dialogue);
    dialogue.setTranslateY(-100);
    
    
    }
    
    public void setText(String s){
        dialogue.setText(s);
    }
    public HBox ret(){
        return holderBox;
    }
}

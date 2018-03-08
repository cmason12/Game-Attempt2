/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package levelattempt2;

import java.io.PrintStream;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author cmason12
 */
public class WindowOptions {
    private ImageView exit = new ImageView("http://cdn.mysitemyway.com/etc-mysitemyway/icons/legacy-previews/icons/3d-transparent-glass-icons-alphanumeric/068009-3d-transparent-glass-icon-alphanumeric-crossing.png");
    private ImageView min = new ImageView("http://cdn.mysitemyway.com/etc-mysitemyway/icons/legacy-previews/icons/3d-transparent-glass-icons-symbols-shapes/016926-3d-transparent-glass-icon-symbols-shapes-minimize.png");
    
    WindowOptions(Stage primaryStage){
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(5.0);
        dropShadow.setColor(Color.RED);
        
        exit.setFitWidth(20);
        exit.setFitHeight(20);
        min.setFitWidth(20);
        min.setFitHeight(20);
        exit.setTranslateY(-625);
        exit.setTranslateX(780);
        min.setTranslateY(-645);
        min.setTranslateX(760);
        exit.setCursor(Cursor.HAND);
        min.setCursor(Cursor.HAND);
        
         exit.setOnMouseClicked((MouseEvent event) -> {
             //stg.save();
             if(Level1.isActive){
                 try{
                     PrintStream p = new PrintStream("Level1.txt");
                     //i.
                     // w H
                     // x y
                     // link
                     
                     for (int i = 0; i < Level1.floors.size(); i++){
                         p.println((i+1) + ": ");
                         p.println(Level1.floors.get(i).getWidth() +
                                 " " + Level1.floors.get(i).getHeight());
                         p.println(Level1.floors.get(i).getTX() +
                                 " " + Level1.floors.get(i).getTY());
                         p.println(Level1.floors.get(i).imgPath);
                         p.println(false);
                         p.println();
                     }
                     p.println(";)");
                     p.close();
                 }catch(Exception e){}
             }
             Platform.exit();
        });
        
        exit.setOnMouseEntered((MouseEvent event) -> {
            exit.setEffect(dropShadow);
        });
        exit.setOnMouseExited((MouseEvent event) -> {
            exit.setEffect(null);
        });
        
        
        min.setOnMouseClicked((MouseEvent event) -> {
            primaryStage.setIconified(true);
        });
        
        min.setOnMouseEntered((MouseEvent event) -> {
            min.setEffect(dropShadow);
        });
        min.setOnMouseExited((MouseEvent event) -> {
            min.setEffect(null);
        });
        
    }
    
    public ImageView retMin(){
        return min;
    }
    
    public ImageView retExit(){
        return exit;
    }
}

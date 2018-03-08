/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package levelattempt2;

import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 *
 * @author cmason12
 */
public class Player {
    private static StackPane hitPane = new StackPane();
    public static Rectangle hitBox = new Rectangle();
    private final ImageView PLAYER = new ImageView("/Resources/player.gif");
    private static ImageView p = new ImageView("/Resources/player.gif");
    private static ImageView g = new ImageView("/Resources/glitch.gif");
    private static ImageView pg = new ImageView("/Resources/glitch.gif");
    private static boolean canDrag = false;
   
    private static double orgSceneX, orgSceneY;
    private static double orgTranslateX, orgTranslateY;
    
    
    
    
    
    Player(boolean cD){
        //p.setScaleY(0);
        hitBox.setWidth(40);
        hitBox.setHeight(65);
        hitBox.setFill(Color.RED);
        hitBox.setOpacity(0);
        hitPane.setPickOnBounds(false);
        hitPane.getChildren().addAll(p, hitBox);
        
        hitBox.setTranslateX(-365);
        hitBox.setTranslateY(200);
        canDrag = cD;
        p.setScaleX(-1);
        //p.setRotate(270);
        p.setFitHeight(80);
        p.setFitWidth(65);
        
        
        p.setTranslateX(-371);
        p.setTranslateY(200);
        p.setCursor(Cursor.HAND);
        pg.setScaleX(-1);
        //p.setRotate(270);
        pg.setFitHeight(80);
        pg.setFitWidth(65);
        
        
        pg.setTranslateX(-371);
        pg.setTranslateY(200);
        pg.setCursor(Cursor.HAND);
        
        
        if(canDrag){
            p.setOnMousePressed(DragEvent.PlayerOnMousePressedEventHandler);
            p.setOnMouseDragged(DragEvent.PlayerOnMouseDraggedEventHandler);
        }
        
        p.setOnMouseReleased((MouseEvent event) -> {
           Level1.fall.setCycleCount(Timeline.INDEFINITE);
           Level1.fall.play();
        });
        
    

 // }
    }
    
    public static ImageView getGlitch(){
        return g;
    }
    
    public static ImageView getPlayer(){
        return p;
    }
    
    public static void getPlayerJump(){
        p.setOpacity(.9);
        p.setImage(new Image("/Resources/glitch.gif"));
    }
    
    public static void getPlayerGround(){
        p.setOpacity(1);
        p.setImage(new Image("/Resources/player.gif"));
    }
    public static StackPane getPlayerWHit(){
        return hitPane;
    }
    
    public static boolean setCanDrag (){
        canDrag = !canDrag;
        if(canDrag){
            p.setOnMousePressed(DragEvent.PlayerOnMousePressedEventHandler);
            p.setOnMouseDragged(DragEvent.PlayerOnMouseDraggedEventHandler);
            hitBox.setOnMousePressed(DragEvent.rectP);
            hitBox.setOnMouseDragged(DragEvent.rectD);
        }else{
            hitBox.setOnMousePressed(DragEvent.rectP);
            hitBox.setOnMouseDragged(DragEvent.rectD);
            p.setOnMousePressed(null);
            p.setOnMouseDragged(null);
        }

        
        return canDrag;
    }
    
    public static void reset(){
        hitBox.setTranslateX(-365);
        hitBox.setTranslateY(200);
        p.setTranslateX(-371);
        p.setTranslateY(200);
    }
  
}

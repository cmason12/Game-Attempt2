/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package levelattempt2;

import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author cmason12
 */
public class DragEvent {
    private static double xOffset = 0;
    private static double yOffset = 0;
    private static double orgSceneX, orgSceneY;
    private static double orgTranslateX, orgTranslateY;
    
   public static EventHandler<MouseEvent> PlayerOnMousePressedEventHandler = 
        new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent t) {
            orgSceneX = t.getSceneX();
            orgSceneY = t.getSceneY();
            //-390.0 L_X
            // 228.0 L_Y
            // 378.0 H_X
            // -235.0 H_Y
  
            
            orgTranslateX = ((ImageView)(t.getSource())).getTranslateX();
            orgTranslateY = ((ImageView)(t.getSource())).getTranslateY();
        }
    };
    
    public static EventHandler<MouseEvent> PlayerOnMouseDraggedEventHandler = 
        new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent t) {
            double offsetX = t.getSceneX() - orgSceneX;
            double offsetY = t.getSceneY() - orgSceneY;
            double newTranslateX = orgTranslateX + offsetX;
            double newTranslateY = orgTranslateY + offsetY;
            
            System.out.println(Level1.checkFloorCollision());
            if(t.getSceneX() > 30.0 && t.getSceneX() < 780.0){
          
                if (!Level1.checkFloorCollision())
                    ((ImageView)(t.getSource())).setTranslateX(newTranslateX);
            
            } 
            if(t.getSceneY() < 560.0 && t.getSceneY() > 80.0){
                
                ((ImageView)(t.getSource())).setTranslateY(newTranslateY);
                Player.hitBox.setTranslateY(Player.getPlayer().getTranslateY()-5);
                Player.hitBox.setTranslateX(Player.getPlayer().getTranslateX()+5);
                
            }
            
            

        }
    };
    
    public static EventHandler<MouseEvent> rectP = 
        new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent t) {
            orgSceneX = t.getSceneX();
            orgSceneY = t.getSceneY();
            orgTranslateX = ((Rectangle)(t.getSource())).getTranslateX();
            orgTranslateY = ((Rectangle)(t.getSource())).getTranslateY();
        }
    };
    
    public static EventHandler<MouseEvent> rectD = 
        new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent t) {
            double offsetX = t.getSceneX() - orgSceneX;
            double offsetY = t.getSceneY() - orgSceneY;
            double newTranslateX = orgTranslateX + offsetX;
            double newTranslateY = orgTranslateY + offsetY;
            
            ((Rectangle)(t.getSource())).setTranslateX(newTranslateX);
            ((Rectangle)(t.getSource())).setTranslateY(newTranslateY);
            System.out.println(newTranslateX);
            System.out.println(newTranslateY);

        }
    };
    
  
}

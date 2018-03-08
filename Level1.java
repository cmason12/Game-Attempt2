/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package levelattempt2;

import static com.sun.applet2.preloader.event.ConfigEvent.STATUS;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import static levelattempt2.TitleScreen.backHolder;
import static levelattempt2.TitleScreen.backImage;

/**
 *
 * @author cmason12
 */
public class Level1 {
    private static StackPane root = new StackPane();
    private Scene scene2;
    protected static  ImageView backImage = new ImageView("/Resources/giphy.gif");
    protected  static VBox backHolder = new VBox();
    public static ArrayList<Floor> floors = new ArrayList<Floor>();
    protected static boolean isActive = false;
    protected static int moveRight = 0;
    public static boolean start = false;
    private static int jumpCount = 0;
    private boolean justLeft = false;
    protected static int floorDisplacement = 0;
    private static int timesDir = 0;
    //+ right - left
    private static int momentum = 0;
    private static int numItMon = 0;
    private static boolean falling = true;
    private static int mont = 0;
    //private Rectangle bottomRect = new Rectangle();
    private static int numKeyPress = 0;
    
    //KeyCombination cntrlZ = new KeyCodeCombination(KeyCode.UP, KeyCodeCombination.);
    
    public static Timeline fall = new Timeline(new KeyFrame(Duration.seconds(.004), new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
         int count = 0;
         boolean intersects = false;
        int i = 0;
        
        if(Player.hitBox.getTranslateY() > 300.0){
                Player.reset();
                resetFloors();
                
            }
        
            for (i = 0; i < floors.size() && !intersects; i++)
                intersects = Player.hitBox.getBoundsInParent().intersects(floors.get(i).getFloor().getBoundsInParent());
                      //  ){
          if (!intersects) {
            Player.hitBox.setTranslateY(Player.hitBox.getTranslateY()+1);
            Player.getPlayer().setTranslateY(Player.getPlayer().getTranslateY()+1);
            Player.getPlayer().setTranslateX(Player.hitBox.getTranslateX()-5);
        }else {
            Player.hitBox.setTranslateY(Player.hitBox.getTranslateY()-1);
            Player.getPlayer().setTranslateY(Player.getPlayer().getTranslateY()-1);
            
           // Player.getPlayer().setTranslateX(Player.hitBox.getTranslateX()-5);
            fall.stop();
            jumpCount = 0;
            falling = false;
            Player.getPlayerGround();
            Player.getPlayer().setRotate(0);
          }
          
       
        }
        }));  
    
    private static void moveAllFloorsX(int x){
        
        for(int i = 0; i < floors.size(); i++){
            floors.get(i).getFloor().setTranslateX(
            floors.get(i).getFloor().getTranslateX() + x);
        }
    }
    
    public static Timeline mon = new Timeline(new KeyFrame(Duration.seconds(.016), new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
       // if (numItMon != 0){
            if(mont > 0){
                right(1);
                mont--;
            }
            else if(mont < 0){
                left(1);
                mont++;
            }
            else
                mon.stop();
        //if (numItMon == 0)
        //mon.stop();
                 }
    
        }));  
    static int canPressKey = 0;
    public static Timeline canPress = new Timeline(new KeyFrame(Duration.seconds(.001), new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
       // if (numItMon != 0)
            canPressKey++;
            if (canPressKey == 20) canPress.stop();
        }
        }));  
    public static void startStage(){
        falling = true;
        fall.setCycleCount(Timeline.INDEFINITE);
        mon.setCycleCount(Timeline.INDEFINITE);
        canPress.setCycleCount(Timeline.INDEFINITE);
        
        canPress.play();
        mon.play();
        fall.play();
    }
    public static void addFloor(Floor newFloor){
        root.getChildren().add(newFloor.getFloor());
    }
    
    public static void removeFloor(int width, int height, int transx, int transy){
        for (int i = 0; i < floors.size(); i++){
            boolean allEqual = true;
            allEqual = (floors.get(i).getTX() == transx);
                if (allEqual){
                    allEqual = (floors.get(i).getTY() == transy);
                        if (allEqual){
                            allEqual = (floors.get(i).getFloor().getWidth() == width);
                                if (allEqual){
                                    allEqual = (floors.get(i).getFloor().getHeight() == height);
                                    if (allEqual){
                                        root.getChildren().remove(floors.get(i).getFloor());
                                        floors.remove(i);
                                    }
                                }
                        }
                }
        }
    }
    
    public static void resetFloors(){
        for (int i = 0; i < floors.size(); i++){
            floors.get(i).getFloor().setTranslateX(
                    floors.get(i).getFloor().getTranslateX() 
                            + -1* floorDisplacement);
            
        }
        floorDisplacement = 0;
    }
    public static boolean checkFloorCollision(){
        boolean intersects = false;
        for (int i = 0; i < floors.size(); i++){
            intersects = Player.hitBox.getBoundsInParent().intersects(
                    floors.get(i).getFloor().getBoundsInParent());
            if(intersects)break;
        }
        return intersects;
    }
    
    private static void right(int disp){
        System.out.println(floorDisplacement );
                 boolean collide = false;
                 falling = true;
                 fall.play();
                 
                 if (((int)Player.hitBox.getTranslateX()) < 65|| 
                             (floorDisplacement + 1974) <= 0){
                     if (Player.hitBox.getTranslateX() < 345)Player.hitBox.setTranslateX(Player.hitBox.getTranslateX()+10);
                     else Platform.exit();
                 } else{
                     moveAllFloorsX(-disp);
                     floorDisplacement -= disp;
                 }
                 collide = checkFloorCollision();
                 
                 if (collide){
                     if (((int)Player.hitBox.getTranslateX()) < 65||
                             (floorDisplacement + 1974) <= 0){
                         Player.hitBox.setTranslateX(Player.getPlayer().getTranslateX()+5);
                         //Player.hitBox.setTranslateX(Player.hitBox.getTranslateX()-10);
                     } else {
                         moveAllFloorsX(+disp);
                         floorDisplacement += disp;
                         
                     }
                 }
                 else
                 {
                     Player.getPlayer().setRotate(1);
                    // Player.hitBox.setTranslateY(Player.getPlayer().getTranslateY()+5);
                    // Player.hitBox.setTranslateX(Player.getPlayer().getTranslateX()+5);
                   //  Player.hitBox.setTranslateY(Player.getPlayer().getTranslateY()+5);
                    // if(justLeft)Player.getPlayer().setTranslateX(Player.getPlayer().getTranslateX()-10);
                     Player.getPlayer().setScaleX(-1);
                     Player.hitBox.setScaleX(1);
                     if(mon.getStatus()== Animation.Status.STOPPED){
                         
                         if (mont >= 0 ) mont += 1;
                         else mont = 0;
                         //momentum = +1;
                         
                     }
                     if (((int)Player.hitBox.getTranslateX()) < 65 || 
                             (floorDisplacement + 1974) <= 0){
                         if (Player.hitBox.getTranslateX() < 345) {
                             Player.getPlayer().setTranslateX(Player.getPlayer().getTranslateX()+disp);
                             //moveRight +=10;
                         }
                     } 
                     else {
                         moveRight += disp;
                         moveAllFloorsX(-disp);
                         floorDisplacement -= disp;
                     }
        
                     
                 }
                 
            //your code for moving the ship
           /* Player.getPlayer().setTranslateX(Player.getPlayer().getTranslateX()-5);
                    /*for (int i = 0; i < floors.size(); i++){
                        floors.get(i).getFloor().setTranslateX(floors.get(i).getFloor().getTranslateX()-5);
                    }
                moveRight+= 5;*/
    }
    
    private static void left(int disp){
        boolean collide = false;
                 falling = true;
                  fall.play();
                 Player.hitBox.setTranslateX(Player.hitBox.getTranslateX()-disp);
                 collide = checkFloorCollision();
                 
                 if (collide || Player.hitBox.getTranslateX() <= -356){
                     Player.hitBox.setTranslateX(Player.hitBox.getTranslateX()+disp);
                     
                 }
                 else
                 {
                     if(mon.getStatus()== Animation.Status.STOPPED){
                        
                         
                         if (mont <= 0 ) mont -= 1;
                         else mont = 0;
                         
                     }
                     Player.getPlayer().setRotate(-1);
                    // Player.hitBox.setTranslateY(Player.getPlayer().getTranslateY()+5);
                     //if(!justLeft)Player.getPlayer().setTranslateX(Player.getPlayer().getTranslateX()-10);
                     //Player.hitBox.setTranslateY(Player.getPlayer().getTranslateY()-5);
                     Player.hitBox.setTranslateX(Player.getPlayer().getTranslateX()-disp -5);
                     Player.getPlayer().setScaleX(1);
                     //Player.hitBox.setScaleX(-1);
                     if(Player.getPlayer().getTranslateX() > -356) Player.getPlayer().setTranslateX(Player.getPlayer().getTranslateX()-disp);
                     //justLeft = true;
                 }
    }
    Level1(Stage primaryStage){
        isActive = true;
        PreDefMenuBar mBar = new PreDefMenuBar(primaryStage, false);
        WindowOptions winOp = new WindowOptions(primaryStage);
        backHolder.setTranslateY(35);
        backHolder.setAlignment(Pos.TOP_LEFT);
        backHolder.getChildren().addAll(backImage,mBar.retMB(), 
                winOp.retExit(), winOp.retMin());
        
       
        
  
        
        NewDialogue newD = new NewDialogue("Welcome to your first stage ;)\n"
                + "Press Any Key to Continue........");
        root.getChildren().addAll(backHolder,Player.hitBox, Player.getPlayer(), newD.ret());
        try{Scanner inFile = new Scanner(new File("Level1.txt"));
        
        while (inFile.hasNext()){
            String temp;
            temp = inFile.next();
            System.out.println(temp);
            if (temp.equals(";)")) break;
            int w = Integer.parseInt(inFile.next());
            System.out.println(w);
            int h = Integer.parseInt(inFile.next());
            System.out.println(h);
            int tX = Integer.parseInt(inFile.next());
            System.out.println(tX);
            int tY = Integer.parseInt(inFile.next());
            System.out.println(tY);
            String path = inFile.next();
            System.out.println(path);
            String  cD = inFile.next();
            
            boolean drag;
            drag = cD.equals("true");
            Floor fl1 = new Floor(w, h, path, drag);
            fl1.setPos(tX, tY);
            floors.add(fl1);
           
            root.getChildren().add(fl1.getFloor());
            
        }
        inFile.close();
        System.out.println("Worked?");
        } catch(Exception e){}
        
        scene2 = new Scene(root, 800, 600);
        backImage.setFitHeight(scene2.getHeight());
        backImage.setFitWidth(scene2.getWidth());
        
        // 65 mid
        //356 left end

        primaryStage.setScene(scene2);
        scene2.setOnKeyPressed((KeyEvent event) -> {
            if(numKeyPress == 0){
                newD.setText("Move Left or Right \nContinously to gain\nMomentum"
                        + ". Press up to release \n(Will Propel You In The \n"
                        + "Direction of Choice)...");
                numKeyPress++;
            }else if(numKeyPress == 1){
                newD.setText("Feel Free to use any \nGlitch to Your Advantage\n"
                        + "And Enjoy, or Dont... \nYou're A Person...");
                numKeyPress++;
            }else if(numKeyPress == 2){
                newD.setText("...??Right??...");
                numKeyPress++;
            } else {
                if(numKeyPress == 3) {
                    startStage();
                    start = true;
                    newD.setText("");
                    root.getChildren().remove(newD);
                    numKeyPress++;
                }
            if(canPressKey == 20){
                canPressKey = 0;
                canPress.play();
            Player.hitBox.setTranslateY(Player.getPlayer().getTranslateY()-5);
            Player.hitBox.setTranslateX(Player.getPlayer().getTranslateX()+5);
            
             if( event.getCode() == KeyCode.RIGHT) {
              //   Player.hitBox.setTranslateX(Player.getPlayer().getTranslateX()+5);
           if(true){
               right(10);      
           event.consume();
           }
                } 
            if(event.getCode() == KeyCode.UP) {
                if(start){
                    event.consume();
                    if (jumpCount < 2){
                        Player.getPlayerJump();
                        mon.play();
                        Player.getPlayer().setTranslateY(
                            Player.getPlayer().getTranslateY()-200);
                        Player.hitBox.setTranslateY(
                            Player.hitBox.getTranslateY()-200);
                        //fall.setCycleCount(Timeline.INDEFINITE);
                        falling = true;
                        fall.play();
                    }
                    jumpCount++;
                    
                   
                } 
                }else if(event.getCode() == KeyCode.LEFT) {
                  if(true){
                      left(10);
                  
                  event.consume();
                  }
            }
            else{
                System.out.println(event.toString());
            };
            
            
            }
            }
         });
        
    }
}

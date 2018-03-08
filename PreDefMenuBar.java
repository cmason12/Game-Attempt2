/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package levelattempt2;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import static levelattempt2.Level1.floors;

/**
 *
 * @author cmason12
 */
public class PreDefMenuBar {
   private MenuBar mBar = new MenuBar();
   private Menu fileMenu = new Menu("File");
   private MenuItem startItem = new MenuItem("Start");
   private Menu addMenu = new Menu("Add");
   private Menu optMenu = new Menu("Options");
   private MenuItem dragItem = new MenuItem("Drag\n-----------------");
   private MenuItem floorItem = new MenuItem("New Floor");
   private MenuItem floorDragItem = new MenuItem("DragAllFloor: Off");
   private MenuItem dragPItem = new MenuItem("DragPlayer: Off");
   private double xOffset = 0;
   private double yOffset = 0;
   private Stage getInput = new Stage();
   public static boolean dragAllFloor = false;
  //  

    PreDefMenuBar(Stage primaryStage, boolean setVisible){
        mBar.setStyle("-fx-border-color:grey; -fx-background-color: #92a29cff;");
        if(!setVisible) {
            fileMenu.setVisible(false);
            addMenu.setVisible(false);
            optMenu.setVisible(false);
        }
        
        fileMenu.getItems().addAll(startItem);
        addMenu.getItems().add(floorItem);
        optMenu.getItems().addAll(dragItem, dragPItem, floorDragItem);
        mBar.getMenus().addAll(fileMenu, addMenu, optMenu);
        mBar.getMenus().add(new Menu(" "));
        mBar.setTranslateY(-600);
        
        mBar.setCursor(Cursor.HAND);
        mBar.setOnMousePressed((MouseEvent event) -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        mBar.setOnMouseDragged((MouseEvent event) -> {
            primaryStage.setX(event.getScreenX() - xOffset);
            primaryStage.setY(event.getScreenY() - yOffset);
        });
        
        floorItem.setOnAction((ActionEvent event) -> {
            dragAllFloor = true;
            getInput = new Stage();
            getInput.initModality(Modality.APPLICATION_MODAL);
            getInput.initOwner(primaryStage);
            VBox root2 = new VBox();
            HBox widthBox = new HBox();
            HBox heightBox = new HBox();
            
            
            Label lblWidth = new Label("Width:  ");
            TextField txtWidth = new TextField();
            widthBox.getChildren().addAll(lblWidth, txtWidth);
            Label lblHeight = new Label("Height: ");
            TextField txtHeight = new TextField("");
            
            
            heightBox.getChildren().addAll(lblHeight, txtHeight);
            Button btnSb = new Button("Submit");
            Button btnCan = new Button("Cancel");
            HBox btnBox = new HBox();
            Scene scene2 = new Scene(root2, 200, 80);
            
            btnBox.getChildren().addAll(btnSb, btnCan);
            root2.getChildren().addAll(widthBox, heightBox, btnBox);
            getInput.setTitle("New Rect");
            getInput.setScene(scene2);
            getInput.show();
            
            btnSb.setOnAction((ActionEvent event1) -> {
                if(Level1.isActive){
                Floor fl1 = new Floor(Integer.parseInt(txtWidth.getText()),
                        Integer.parseInt(txtHeight.getText()), 
                        "http://pixa.club/bundles/core/images/old-tv.png", 
                        true);
            floors.add(fl1);
            Level1.addFloor(fl1);
            getInput.close();
            }
            });
            btnCan.setOnAction((ActionEvent event1) -> {
                getInput.close();
                
                
            });
            
        });
        
         dragPItem.setOnAction((ActionEvent event) -> {
             boolean enabled = Player.setCanDrag();
             System.out.println(enabled);
             
             if (enabled) dragPItem.setText("DragPlayer: On");
             else dragPItem.setText("DragPlayer: Off");
         });
         floorDragItem.setOnAction((ActionEvent event) -> {
             boolean enabled = !dragAllFloor;
             dragAllFloor = !dragAllFloor;
             
             if (enabled) floorDragItem.setText("DragAllFloor: On");
             else floorDragItem.setText("DragAllFloor: Off");
         });
         
         startItem.setOnAction((ActionEvent event) -> {
            // fileMenu.setVisible(false);
             //addMenu.setVisible(false);
             //optMenu.setVisible(false);
             
             if(Level1.isActive){
                 Level1.start = true;
                 Level1.startStage();
             }
         });
    }
    
    public MenuBar  retMB(){
        return mBar;
    }
}

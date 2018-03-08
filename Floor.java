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
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import static levelattempt2.Level1.floorDisplacement;

/**
 *
 * @author cmason12
 */
//https://i.pinimg.com/originals/ca/06/06/ca06067d04fb6ce789096a013baf2405.gif
//https://designshack.net/wp-content/uploads/mytextures-19.jpg
public class Floor {
    private Rectangle childFloor;
    private ImagePattern floorFill;
    protected String imgPath;
    private int translateX = 0;
    private int translateY = 0;
    
    private int coordX = 0;
    private int coordY= 0; // Relative to the player 
    //not Stage 
    private ContextMenu context = new ContextMenu();
    private MenuItem deleteItem = new MenuItem("Delete");
    Floor (int width, int height, String temp, boolean canDrag){
        DropShadow dropShadow = new DropShadow();
        //dropShadow.setOffsetX(6.0);
        //dropShadow.setOffsetY(4.0);
        dropShadow.setRadius(5.0);
        dropShadow.setColor(Color.WHITE);
        imgPath = temp;
        
        
        childFloor = new Rectangle();
        childFloor.setWidth(width);
        childFloor.setHeight(height);
        floorFill = new ImagePattern(new Image(temp));
        childFloor.setFill(floorFill);
        childFloor.setEffect(dropShadow);
        if(canDrag){
            childFloor.setOnMousePressed(DragEvent.rectP);
            childFloor.setOnMouseDragged(DragEvent.rectD);
        }
        
        childFloor.setOnMouseExited((MouseEvent event) -> {
             translateX = (int)childFloor.getTranslateX() + -1*  Level1.floorDisplacement;
            translateY = (int)childFloor.getTranslateY();
            System.out.println("TranslateX: " + translateX);
            System.out.println("TranslateY: " + translateY);
         });
        childFloor.setOnMouseEntered((MouseEvent event) -> {
             setCanDrag();
         });
        
        
    }
    
    public void setCanDrag (){
        if(PreDefMenuBar.dragAllFloor){
            childFloor.setOnMousePressed(DragEvent.rectP);
            childFloor.setOnMouseDragged(DragEvent.rectD);
            childFloor.setCursor(Cursor.HAND);
            ContextMenu contextMenu = new ContextMenu();
 
        MenuItem deleteItem = new MenuItem("Delete");
        deleteItem.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                if(Level1.isActive){
                    Level1.removeFloor((int)childFloor.getWidth(), 
                    (int)childFloor.getHeight(), translateX, translateY);
                }
            }
        });
        MenuItem editItem = new MenuItem("Edit");
        editItem.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                if(Level1.isActive){
                    editRect();
                }
            }
        });
        contextMenu.getItems().addAll(editItem, deleteItem);
        childFloor.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {
 
            @Override
            public void handle(ContextMenuEvent event) {
 
                contextMenu.show(childFloor, event.getScreenX(), event.getScreenY());
            }
        });
        }else{
            childFloor.setOnContextMenuRequested(null);
            childFloor.setOnMousePressed(null);
            childFloor.setOnMouseDragged(null);
            childFloor.setCursor(Cursor.DEFAULT);
        }

    }
    
    public Rectangle getFloor(){
        return childFloor;
    }
    public void setPos(int x, int y){
        childFloor.setTranslateX(x);
        childFloor.setTranslateY(y);
        translateX = x;
        translateY = y;
    }
    
    public int getWidth(){
        return (int)childFloor.getWidth();
    }
    
    public int getHeight(){
        return (int)childFloor.getHeight();
    }
    
    public int getTX(){
        return translateX;
    }
    
    public int getTY(){
        return translateY;
    }
    private  void editRect(){
        Stage editStage = new Stage();
        Pane root = new Pane();
        VBox parentBox = new VBox();
        
        Label lblTrans = new Label("Translate-----");
        HBox xBox = new HBox();
        Label lblxTrans = new Label("X: ");
        TextField txtxTrans = new TextField(translateX+"");
        xBox.getChildren().addAll(lblxTrans, txtxTrans);
        
        HBox yBox = new HBox();
        Label lblyTrans = new Label("Y: ");
        TextField txtyTrans = new TextField(translateY+"");
        yBox.getChildren().addAll(lblyTrans, txtyTrans);
        
        Label lblWidth = new Label("Width-----");
        TextField txtWidth = new TextField(((int)childFloor.getWidth()) + "");
        Label lblHeight = new Label("Height-----");
        TextField txtHeight = new TextField(((int)childFloor.getHeight()) + "");
        
        HBox btnBox = new HBox();
        Button btnSub = new Button ("Submit");
        Button btnCanc = new Button("Cancel");
        btnBox.getChildren().addAll(btnSub, btnCanc);
        parentBox.getChildren().addAll(lblTrans, xBox,
                yBox, lblWidth, txtWidth, lblHeight,
                txtHeight, new Label(" "), btnBox);
        root.getChildren().add(parentBox);
        Scene scene = new Scene(root, 200,200);
        editStage.setScene(scene);
        editStage.setTitle("Edit Floor");
        editStage.show();
        
        btnSub.setOnMouseClicked((MouseEvent event) -> {
             translateX = Integer.parseInt(txtxTrans.getText());
             translateY = Integer.parseInt(txtyTrans.getText());
             childFloor.setTranslateX(translateX+ Level1.floorDisplacement);
             childFloor.setTranslateY(translateY);
             childFloor.setWidth(Integer.parseInt(txtWidth.getText()));
             childFloor.setHeight(Integer.parseInt(txtHeight.getText()));
             editStage.close();
         });
        btnCanc.setOnMouseClicked((MouseEvent event) -> {
             editStage.close();
         });
    }
}

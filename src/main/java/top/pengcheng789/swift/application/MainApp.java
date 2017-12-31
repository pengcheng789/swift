/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package top.pengcheng789.swift.application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import java.io.IOException;
/**
 *
 * @author chance
 */
public class MainApp extends Application{
    @Override
    public void start (Stage stage){     
        Scene index = indexScene();
        stage.setTitle("登录");
        stage.setScene(index);
        stage.show();
    }
    
    public static void main(String[] args){
        launch(args);
    }
    
    Scene indexScene(){
        Parent root;
        Scene index;
        try{
            root = FXMLLoader.load(getClass().getResource("/asset/windows/fxml/index.fxml"));
            index = new Scene(root, 700, 500);
            return index;
        }catch(IOException e){
            System.out.println("使用的“fxml”存在异常！");
            System.out.println(e);
        } 
        return null;
    }
}

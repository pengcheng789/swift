/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package top.pengcheng789.java.swiftPen.windows;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.text.Text;
import top.pengcheng789.java.swiftPen.network.Network;

/**
 *
 * @author chance
 */
public class IndexController {
    @FXML private Button loginButton;
    @FXML private Button logoutButton;
    String ipAddress="172.16.144.3";
    String macAddress="01-23-45-67-89-AB";
    @FXML TextField usernameFXML;
    @FXML PasswordField passwordFXML;
    @FXML Text messageFXML;
    Network network = new Network("", "", this.ipAddress, this.macAddress);
    Timer brathe = new Timer();
    
    @FXML 
    protected void loginAction(ActionEvent event){
        String username = usernameFXML.getText();
        String password = passwordFXML.getText();
        if(username.isEmpty() && password.isEmpty())
            return;
        else {
            network.setUsername(username);
            network.setPassword(password);
        }
        
        try {
            messageFXML.setText(this.network.online());
	} catch (IOException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
            System.out.println("连接超时");
            System.exit(0);
	}
        
	brathe.schedule(new TimerTask(){
        @Override
        public void run(){
		    try {
                network.brathe();
		    } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
		    }
        }
	}, 30000, 30000);
        
        this.loginButton.setDisable(true);
        this.logoutButton.setDisable(false);
    }
    
    @FXML 
    protected void logoutAction(ActionEvent event){
        try {
            brathe.cancel();
            network.offline();
	} catch (IOException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
            System.out.println("注销超时");
	}
        
        messageFXML.setText("注销成功。");
        this.loginButton.setDisable(false);
        this.logoutButton.setDisable(true);
    }   
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.mangement.controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author JAVA
 */
public class PreferenceController implements Initializable {

    @FXML
    private TextField severPref;
    @FXML
    private Spinner<Integer> portPref;
    @FXML
    private TextField userPref;
    @FXML
    private TextField passPref;
    @FXML
    private Button saveBt;
    @FXML
    private Button closeBt;

    private Preferences pref;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
        pref = Preferences.userRoot().node("libdb");
        String host = pref.get("host", "localhost");
        int port = pref.getInt("port", 3306);
        String username = pref.get("username", "root");
        String password = pref.get("password", "");
        
        
        SpinnerValueFactory<Integer> intFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(3200, 3500, 3306);
        portPref.setValueFactory(intFactory);
        portPref.editableProperty().setValue(Boolean.TRUE);

        severPref.setText(host);
        userPref.setText(username);
        passPref.setText(password);
    
    }

    @FXML
    private void savePref(ActionEvent event) {
    
    String host = severPref.getText();
    int port = portPref.getValue();
    String username = userPref.getText();
    String password = passPref.getText();
    
    pref.put("host", host);
    pref.putInt("port", port);
    pref.put("username", username);
    pref.put("password", password);
    
    }

    @FXML
    private void closeWin(ActionEvent event) {
    
    Stage stage = (Stage) closeBt.getScene().getWindow();
    stage.close();
        
    }
    
}

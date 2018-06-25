
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.mangement.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import library.mangement.Database.Database;
import library.mangement.model.Member;
import library.mangement.model.MemberDAO;

/**
 * FXML Controller class
 *
 * @author JAVA
 */
public class AddmembersController implements Initializable {

    @FXML
    private JFXButton closewindow;
    @FXML
    private JFXButton savebt;
    @FXML
    private JFXTextField ID;
    @FXML
    private JFXTextField Name;
    @FXML
    private JFXTextField Address;
    @FXML
    private JFXTextField Mobile;

    MemberDAO memberDAO;
    
    /**
     * Initializes the controller class.
     */
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        memberDAO = new MemberDAO();
    }    

    @FXML
    private void closeaddmembers(ActionEvent event) {
    
        Stage  stage = (Stage) closewindow.getScene().getWindow();
        
        stage.close();
   
}

    @FXML
    private void saveToDB(ActionEvent event) throws SQLException  {
        
        String id = ID.getText();
        String name = Name.getText();
        String address = Address.getText();
        String mobile = Mobile.getText();
        
        Member member = new Member(id,name,address,mobile);
    
        if(id.isEmpty() | name.isEmpty() | address.isEmpty() | mobile.isEmpty()){
        
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROE");
        alert.setContentText("YOU FROGET SOMETHING");
        alert.setHeaderText("");
        alert.show();
        return;
        }
        
        memberDAO.saveMemberToDB(member);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("SUCCESS");
        alert.setContentText("Member added");
        alert.setHeaderText("");
        alert.show();
        
    }
}
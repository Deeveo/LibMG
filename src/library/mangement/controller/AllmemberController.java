/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.mangement.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import library.mangement.Database.Database;
import library.mangement.model.Member;
import library.mangement.model.MemberDAO;

/**
 * FXML Controller class
 *
 * @author JAVA
 */
public class AllmemberController implements Initializable {

    @FXML
    private TableView<Member> tbView;
    @FXML
    private TableColumn<?, ?> id;
    @FXML
    private TableColumn<?, ?> name;
    @FXML
    private TableColumn<?, ?> address;
    @FXML
    private TableColumn<?, ?> mobile;
    @FXML
    private MenuItem deleteMenu;
    
    MemberDAO memberDAO;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        memberDAO = new MemberDAO();
        
        ObservableList list = null;
        
        try {
            list = memberDAO.showAllMemebr();
        } catch (SQLException ex) {
            Logger.getLogger(AllmemberController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        mobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
                
        tbView.getItems().addAll(list);
    }    

    @FXML
    private void deleteItem(ActionEvent event) throws SQLException {
    
     Member member = tbView.getSelectionModel().getSelectedItem();
     tbView.getItems().remove(member);
    
     memberDAO.removeMember(member.getId());
     
    }
}

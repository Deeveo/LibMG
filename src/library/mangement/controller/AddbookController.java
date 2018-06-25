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
import library.mangement.model.Book;
import library.mangement.model.BookDAO;


/**
 * FXML Controller class
 *
 * @author JAVA
 */
public class AddbookController implements Initializable {

    @FXML
    private JFXButton closeAddbook;
    @FXML
    private JFXTextField bookID;
    @FXML
    private JFXTextField booktitle;
    @FXML
    private JFXTextField bookpublisher;
    @FXML
    private JFXButton savebt;
    @FXML
    private JFXTextField bookauthor;
    
    BookDAO bookDAO;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bookDAO = new BookDAO();
    }    
 @FXML
    private void saverToDB(ActionEvent event) throws SQLException {
        
        String id = bookID.getText();
        String title = booktitle.getText();
        String author = bookauthor.getText();
        String publisher = bookpublisher.getText();
        
        Book book = new Book(id,title,author,publisher);
        
        if(id.isEmpty() | title.isEmpty() | author.isEmpty() | publisher.isEmpty()){
        
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROE");
        alert.setContentText("YOU FROGET SOMETHING");
        alert.setHeaderText("");
        alert.show();
        return;
        
        }
    
            bookDAO.saveBookToDB(book);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("SUCCESS");
            alert.setContentText("Book added");
            alert.setHeaderText("");
            alert.show();      
        
        

        
    }
    
    @FXML
    private void closeaddbook(ActionEvent event) {
        
        Stage stage = (Stage) closeAddbook.getScene().getWindow();
        
        stage.close();
        
    }

   
}

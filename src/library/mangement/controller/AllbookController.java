/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.mangement.controller;

import java.net.URL;
import java.sql.SQLException;
import static java.util.Collections.list;
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
import library.mangement.model.Book;
import library.mangement.model.BookDAO;

/**
 * FXML Controller class
 *
 * @author JAVA
 */
public class AllbookController implements Initializable {

    @FXML
    private TableColumn<Book, String> id;
    @FXML
    private TableColumn<Book, String> title;
    @FXML
    private TableColumn<Book, String> author;
    @FXML
    private TableColumn<Book, String> publisher;
    
    @FXML
    private TableView<Book> tbView;
    
    @FXML
    private TableColumn<Book, Boolean> available;
    @FXML
    private MenuItem Deletemenu;

    BookDAO bookDAO;
    @FXML
    private MenuItem Editmenu;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        bookDAO = new BookDAO();
        
        ObservableList list = null;
        try {
            list = bookDAO.showAllBook();
        } catch (SQLException ex) {
            }
        
        
        
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        author.setCellValueFactory(new PropertyValueFactory<>("author"));
        publisher.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        available.setCellValueFactory(new PropertyValueFactory<>("available"));
        
        tbView.getItems().addAll(list);
        
    }    

    @FXML
    private void deleteItem(ActionEvent event) throws SQLException {
        
        
        Book book = tbView.getSelectionModel().getSelectedItem();
        tbView.getItems().remove(book);
       
        bookDAO.removeBook(book.getId());
        
    }

    @FXML
    private void editItem(ActionEvent event) {
       
        
    }
    
     
}

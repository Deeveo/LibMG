/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.mangement.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import library.mangement.model.Book;
import library.mangement.model.BookDAO;
import library.mangement.model.Issue;
import library.mangement.model.IssueDAO;
import library.mangement.model.Member;
import library.mangement.model.MemberDAO;

/**
 *
 * @author JAVA
 */
public class HomeController implements Initializable {
    
    private Label label;
    @FXML
    private MenuItem closeItem;
    @FXML
    private JFXButton showBook;
    @FXML
    private JFXButton showallMembers;
    @FXML
    private JFXButton openaddmembersWindow;
    @FXML
    private JFXButton addBookBtn;
    
   
    @FXML
    private JFXTextField searchBook;
    @FXML
    private Label bookTitle;
    @FXML
    private Label bookAuthor;
    @FXML
    private Label bookPublisher;
    @FXML
    private Label bookAvailable;
    
    @FXML
    private JFXTextField searchMember;
    @FXML
    private Label memberName;
    @FXML
    private Label memberMobile;
    @FXML
    private Label memberAddress;
    
    BookDAO bookDAO;
    MemberDAO memberDAO;
    IssueDAO issueDAO;
    
    @FXML
    private JFXButton issueBt;
    @FXML
    private JFXTextField searchbookID;
    @FXML
    private Label issueTitle;
    @FXML
    private Label issueAuthor;
    @FXML
    private Label issueDate;
    @FXML
    private Label issueName;
    @FXML
    private Label issueMobile;
    @FXML
    private Label issueCount;
    @FXML
    private JFXButton submitBt;
    @FXML
    private JFXButton renewBt;
    @FXML
    private MenuItem prefItem;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bookDAO = new BookDAO();
        memberDAO = new MemberDAO();
        issueDAO = new IssueDAO();
    }    

    @FXML
    private void closeWindow(ActionEvent event) {
        
        Stage stage = (Stage) showBook.getScene().getWindow();
        
        stage.close();
        
    }

    @FXML
    private void showBookWindow(ActionEvent event) throws IOException {
       
     createWindow("/library/mangement/view/allbook.fxml");
        
    }

    @FXML
    private void showallMembers(ActionEvent event) throws IOException{
       
        createWindow("/library/mangement/view/allmember.fxml");
    }
        

    @FXML
    private void openmembersWindow(ActionEvent event) throws IOException {
   
        createWindow("/library/mangement/view/addmembers.fxml");
    
    }

    @FXML
    private void openAddBookWindow(ActionEvent event) throws IOException {
        
       createWindow("/library/mangement/view/addbook.fxml");
    }

   public void createWindow(String url) throws IOException {
   
   Parent root = FXMLLoader.load(getClass().getResource(url));
        
             Stage stage = new Stage();
            
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
       
   } 

    @FXML
    private void searchBook(ActionEvent event) throws SQLException {
        
        String  bookID = searchBook.getText();
        
        Book book = bookDAO.searchBook(bookID);
        
        if(book==null){
        
          Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setTitle("ERROR");
          alert.setHeaderText("");
          alert.setContentText("Book Not Found");
          alert.show();
            
          return;
        }
        
        bookTitle.setText("Title\t\t\t-\t"+book.getTitle());
        bookAuthor.setText("Author\t\t-\t"+book.getAuthor());
        bookPublisher.setText("Publisher\t\t-\t"+book.getPublisher());
        String status = book.isAvailable()?"YES":"NO";
        
      /*  if(book.isAvailable()){
            status = "YES";
        }else{
            status = "NO";
        }*/
        
        bookAvailable.setText("Acailable\t\t-\t"+status);
 
        
    }

    @FXML
    private void searchMember(ActionEvent event) throws SQLException {
    
    String memberID = searchMember.getText();
    Member member = memberDAO.searchMember(memberID);

    if(member==null){
    
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("ERROR");
    alert.setHeaderText("");
    alert.setContentText("Member Not Foud");
    alert.show();

    return;
    
    }
    
        memberName.setText("Name\t\t-\t"+member.getName());
        memberAddress.setText("Address\t\t-\t"+member.getAddress());
        memberMobile.setText("Mobile\t\t-\t"+member.getMobile());
    
    }

    @FXML
    private void issue(ActionEvent event) {
        
         String bookID = searchBook.getText();
         String memberID = searchMember.getText();
         
         Issue issue = new Issue(bookID,memberID);
         
        try {
            issueDAO.issueBook(issue);
            bookDAO.updateBook(false, bookID);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("SUCCES");
            alert.setContentText("ADDED");
            alert.setHeaderText("");
            alert.show();
         
        } catch (SQLException ex) {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("ERROR");
//            alert.setContentText("CAN'T ADD");
//            alert.setHeaderText("");
//            alert.show();
              
        
        }
         
    }

    @FXML
    private void issuebookID(ActionEvent event) throws SQLException {
        
        String bookID = searchbookID.getText();
        Issue issue = issueDAO.issueBook(bookID);
        
        if(issue==null) {
           Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("FAIL");
            alert.setContentText("BOOK CAN NOT FIND");
            alert.setHeaderText("");
            alert.show();
            return;
        
        }
        
        Book book = bookDAO.searchBook(bookID);
        Member member = memberDAO.searchMember(issue.getMemberID());
        
        issueTitle.setText("Title\t\t-"+book.getTitle());
        issueName.setText("Name\t\t-"+member.getName());
        issueAuthor.setText("Author\t-"+book.getAuthor());
        issueMobile.setText("Member\t\t-"+member.getMobile());
        issueCount.setText("Count\t\t-"+issue.getRenewCount());
       
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/YY");
        String dateStr = dateFormat.format(issue.getIssueDate());
        issueDate.setText("Date\t\t-"+dateStr);
        
        
        
    }

    @FXML
    private void submitIssue(ActionEvent event) throws SQLException {
      
        String issueSub = searchbookID.getText();
        try {
            issueDAO.submitIssue(issueSub);
            bookDAO.updateBook(true, issueSub);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("SUCCES");
            alert.setContentText("SUBMITED");
            alert.setHeaderText("");
            alert.show();
        } catch (SQLException ex) {
            
        }
        
    }

    @FXML
    private void renewIssue(ActionEvent event) {
        
        String renewBt = searchbookID.getText();
        try {
            issueDAO.renewIssue(renewBt);
        } catch (SQLException ex) {
            }
        
    }

    @FXML
    private void prefWin(ActionEvent event) throws IOException {
        
        createWindow("/library/mangement/view/preference.fxml");
    
        
    }
    
    
    
}


    
    


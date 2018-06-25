/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.mangement.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import library.mangement.Database.Database;

/**
 *
 * @author JAVA
 */
public class IssueDAO {

    public void issueBook(Issue issue) throws SQLException {

        Connection conn = Database.getInstance().getConnnection();
    
        String sql = "insert into libdb.issue (book_id,member_id,issue_date,renew_count) value(?,?,curDate(),0)";
    
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, issue.getBookID());
        stmt.setString(2, issue.getMemberID());
        stmt.execute();
        
    }

    public Issue issueBook(String bookID) throws SQLException {
        
        Connection conn = Database.getInstance().getConnnection();
    
        Issue issue = null;
    
        String sql = "select * from libdb.issue where book_id=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, bookID);
        ResultSet result = stmt.executeQuery();

    while(result.next()) {
   
        String memberID = result.getString("member_id");
        Date issueDate = result.getDate("issue_date");
        int renewCon = result.getInt("renew_count");
       
        issue = new Issue (bookID,memberID,issueDate,renewCon);
       }
        return issue;
    }

    public void submitIssue(String issueSub) throws SQLException {
        
        Connection conn = Database.getInstance().getConnnection();
    
        String sql = "delete from libdb.issue where book_id=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, issueSub);
        stmt.execute();
    
    }

    public void renewIssue(String renewBt) throws SQLException {
        
        Connection conn = Database.getInstance().getConnnection();
        String sql = "update libdb.issue set renew_count=renew_count+1 where book_id=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, renewBt);
        stmt.execute();
    
    }
   }
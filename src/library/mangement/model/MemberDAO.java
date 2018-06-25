/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.mangement.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import library.mangement.Database.Database;

/**
 *
 * @author JAVA
 */
public class MemberDAO {
    
     public void saveMemberToDB(Member member) throws SQLException {
         
    Connection conn = Database.getInstance().getConnnection();
    
    String sql = "insert into libdb.member (id,name,address,mobile) values(?,?,?,?)";
    
    PreparedStatement stmt = conn.prepareStatement(sql);
    stmt.setString(1, member.getId());
    stmt.setString(2, member.getName());
    stmt.setString(3, member.getAddress());
    stmt.setString(4, member.getMobile());
    stmt.execute();
        
    }

   
    public ObservableList<Member> showAllMemebr() throws SQLException {
        
    Connection conn = Database.getInstance().getConnnection();
    
    String sql = "select * from libdb.member";
    
    Statement stmt = conn.createStatement();
    ResultSet rs = stmt.executeQuery(sql);
    
     ObservableList<Member> memberList = FXCollections.observableArrayList();
    
     while(rs.next()) {
     
     String id = rs.getString("id");
     String name = rs.getString("name");
     String address = rs.getString("address");
     String mobile = rs.getString("mobile");
     
     Member member = new Member(id,name,address,mobile);
     
     memberList.add(member);
     }
    return memberList;
    }

   

    public void removeMember(String id) throws SQLException {
        
    Connection conn = Database.getInstance().getConnnection();
       
    String sql = "delete from libdb.member where id=?";
    PreparedStatement stmt = conn.prepareStatement(sql);
    
    stmt.setString(1, id);
    stmt.execute();
    }  

    public Member searchMember(String memberID) throws SQLException {
       
     Member member = null;
        
    Connection conn = Database.getInstance().getConnnection();
    
    String sql = "select * from libdb.member where id=?";
    PreparedStatement stmt = conn.prepareStatement(sql);
    stmt.setString(1, memberID);
   ResultSet result = stmt.executeQuery();

   while(result.next()) {
   
       String name = result.getString("name");
       String address = result.getString("address");
       String mobile = result.getString("mobile");
               
       member = new Member(memberID,name,address,mobile);
       
              }
        return member;
   
    }
        
}

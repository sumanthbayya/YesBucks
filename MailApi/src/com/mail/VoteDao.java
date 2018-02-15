package com.mail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mail.ConnectionProvider;
import com.model.Vote;

public class VoteDao {
	public static void insertVote(Vote c) {
		
		try {
			Connection c1 = ConnectionProvider.getConnection();	
			
			PreparedStatement p = c1.prepareStatement("insert into vote(username,vote) values(?,?)");
			p.setString(1, c.getUsername().trim());
			p.setString(2, c.getVote().trim());
			p.executeUpdate();
			c1.commit();
			p.close();
			System.out.println("vote data added successfully"+c.getUsername());

			} catch (SQLException e) {
			e.printStackTrace();

			}

	}
	
		public static void insertVoteToRemote(Vote c) {
		
		try {
			Connection c1 = ConnectionRemote.getConnection();	
			
			PreparedStatement p = c1.prepareStatement("insert into vote(username,vote) values(?,?)");
			p.setString(1, c.getUsername().trim());
			p.setString(2, c.getVote().trim());
			p.executeUpdate();
			c1.commit();
			p.close();
			System.out.println("vote data added successfully"+c.getUsername());

			} catch (SQLException e) {
			e.printStackTrace();

			}

	}
		public static List<Vote> getListData() {
			List<Vote> ldata = new ArrayList<Vote>();
			int ix = 0;
			try {
				Connection c = ConnectionRemote.getConnection();
				Statement s = (Statement) c.createStatement();
				ResultSet rs = s.executeQuery("select * from vote");
				while (rs.next()) {
					Vote d = new Vote();
					
					
					d.setUsername(rs.getString(1));
					d.setVote(rs.getString(2));
					
					ldata.add(d);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
			

			return ldata;

		}
		public static String updateData(Vote d) {
			try {
				
				
				Connection c = ConnectionRemote.getConnection();
				PreparedStatement ps = c.prepareStatement("update vote set vote='" + d.getVote() + "' where username = '"+d.getUsername()+"'");
				System.out.println(ps);
				ps.executeUpdate();
				
				c.commit();
				return "suc";
			} catch (SQLException e) {
				e.printStackTrace();
				return "fail";
			}

			// TODO Auto-generated method stub

		}
		
	
}

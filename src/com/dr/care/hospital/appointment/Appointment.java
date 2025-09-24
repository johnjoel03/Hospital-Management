package com.dr.care.hospital.appointment;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.dr.care.hospital.customdesign.customcolors;



public class Appointment {
	

	public void BookAppointments() {
		String Disease_Name ,DateOfAppointment;
		 String Patient_Email = null;
		String Patient_Name;
		int Doctor_Id;
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "Root");
			System.out.println("Enter Patient Name");
			Scanner sc= new Scanner(System.in);
			Patient_Name=sc.next();
			System.out.println("Enter Your Disease");
			Disease_Name=sc.next();
			System.out.println("Enter Doctor ID");
			Doctor_Id=sc.nextInt();
			System.out.println("Date Of Appointment (YYYY/MM/DD)");
			DateOfAppointment=sc.next();
			System.out.println("Enter Patient Email");
			Patient_Email=sc.next();
			System.out.println("Enter Patient Id");
			
			int Patient_id = sc.nextInt();
			
			String sql="insert into appointment( Patient_Name, Disease_Name,Doctor_Id,DateOfAppointment,Patient_Email,Patient_id)values(?,?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, Patient_Name);
			ps.setString(2, Disease_Name);
			ps.setInt(3, Doctor_Id);
			ps.setString(4, DateOfAppointment);
			ps.setString(5, Patient_Email);
			ps.setInt(6, Patient_id);
			
			int i = ps.executeUpdate();
			if(i==1)
			{   
				 String host="smtp.gmail.com";   // Types of gmail sending hostname []
				  final String user="johnjoelraj2018@gmail.com";//change accordingly  
				  final String password="gxjc yvsw vaop mukp";//change accordingly  
				    
				 
				String to=Patient_Email;//change accordingly  
				  
				   //Get the session object  
				  Properties props = new Properties();
				  props.put("mail.smtp.auth", "true");
				  props.put("mail.smtp.starttls.enable", "true");
				  props.put("mail.smtp.host", "smtp.gmail.com");
				  props.put("mail.smtp.port", "587");
				   Session session = Session.getDefaultInstance(props,  
				    new javax.mail.Authenticator() {  
				      protected PasswordAuthentication getPasswordAuthentication() {  
				    return new PasswordAuthentication(user,password);  
				      }  
				    });  
				  
				   //Compose the message  
				    try {  
				     MimeMessage message = new MimeMessage(session);  
				     message.setFrom(new InternetAddress(user));  
				     message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
				     message.setSubject("CARE HOSPITAL -");  
				     message.setText("Dear "+Patient_Name+",\n You're Appointment is Successfully Completed in CARE HOSPITAL.\n Please ready for Health Checkup\n\n\n\n\n Thanks\n CARE HOSPITAL\n +91 7036894577 ");  
				       
				    //send the message  
				     Transport.send(message);  
				  
				     System.out.println("message sent successfully...");  
				   
				     } catch (MessagingException e) {e.printStackTrace();}	
				System.out.println("Doctor Appointment is Successfully Completed");
			}
			else
			{
				System.out.println("Doctor Appointment is Failed");
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	public void ViewAppointments(int id)
	{
		
		 
			   try
			   {
				   Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "Root");
				   String sql1="select * from appointment where Doctor_Id='"+id+"'";
				   Statement st=con.createStatement();
				   ResultSet rs=st.executeQuery(sql1);
				   System.out.println(customcolors.BLUE_BACKGROUND_BRIGHT+customcolors.WHITE_BOLD_BRIGHT+"Patient Name\t\tDisease Name \tDateOfAppointment\tPatient_Email\t\t\t\t Doctor_Id \t Patient Id"+customcolors.RESET);
				  while(rs.next())
				   {
					   System.out.println(rs.getString(1)+"\t\t\t"+rs.getString(2)+"\t\t"+rs.getString(4)+"\t\t"+rs.getString(5)+"\t\t\t"+rs.getInt(2)+"\t"+rs.getInt(6));
					
				    }
				   
			   }
			   catch(Exception e)
			   {
				   e.printStackTrace();
			   }
		   
	}
	public void ViewAppointments1()
	{
		
		 
			   try
			   {
				   Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "Root");
				   String sql1="select * from appointment";
				   Statement st=con.createStatement();
				   ResultSet rs=st.executeQuery(sql1);
				   System.out.println(customcolors.BLUE_BACKGROUND_BRIGHT+customcolors.WHITE_BOLD_BRIGHT+"Patient Name\t\tDisease Name \tDateOfAppointment\tPatient_Email\t\t\t\t Doctor_Id \t Patient Id"+customcolors.RESET);
				  while(rs.next())
				   {
					  System.out.println(rs.getString(1)+"\t\t\t"+rs.getString(2)+"\t\t"+rs.getString(4)+"\t\t"+rs.getString(5)+"\t\t\t"+rs.getInt(3)+"\t"+rs.getInt(6));
					
				   }
				   
			   }
			   catch(Exception e)
			   {
				   e.printStackTrace();
			   }
		   
	}

	
}

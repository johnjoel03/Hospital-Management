package com.dr.care.hospital.doctor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

import com.dr.care.hospital.appointment.Appointment;
import com.dr.care.hospital.customException.DoctorResourceException;
import com.dr.care.hospital.customdesign.customcolors;

public class Doctorn {
	Scanner din;
	public  void login()
	{
		System.out.println(customcolors.BLUE_ITALIC+"Doctor Login Here.."+customcolors.RESET);
		din = new Scanner(System.in);
		String name,password,prsc;
		int Patient_id = 0;
		System.out.println("Enter Doctor Name");
		name = din.next();
		System.out.println("Enter Doctor Password");
		password = din.next(); 
		
		int check = 0;
		try
		{
			
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "Root");
			String sql="select * from  doctors";
			
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next())
			{
				if(name.equalsIgnoreCase(rs.getString(2)) && password.equalsIgnoreCase(rs.getString(6)))	
				{
					int id=rs.getInt(1);
					System.out.println(id);
	
					System.out.println("Enter Doctor Id");
					int Doctor_Id = din.nextInt(); 
					
					
					if(id==Doctor_Id)
					{
						System.out.println(customcolors.BLUE_BOLD+"*** Your Appointments *******"+customcolors.RESET);
						//Appointment app=new Appointment();
						//app.ViewAppointments(id);
						String sql1="select * from  appointment where Doctor_Id="+Doctor_Id+" and status='Success'";
						
						Statement st1 = con.createStatement();
						ResultSet rs1 = st1.executeQuery(sql1);
					    System.out.println(customcolors.BLUE_BACKGROUND_BRIGHT+customcolors.WHITE_BOLD_BRIGHT+"Patient Name\t\tDisease Name \tDateOfAppointment\tPatient_Email\t\t Doctor_Id \t Patient Id"+customcolors.RESET);
						while(rs1.next())
						{
							 System.out.println(rs1.getString(2)+"\t\t\t"+rs1.getString(3)+"\t\t"+rs1.getString(4)+"\t\t"+rs1.getString(5)+"\t\t\t"+rs1.getString(6)+"\t"+rs1.getInt(7));
						}
						
						String Doctor_Name,Specialization,Patient_Name,Disease,Prescription;
						int patient_id,Doctor_id;
						
						System.out.println(customcolors.BLUE_BOLD+"******Enter Your Presciption"+customcolors.RESET);
						System.out.println("Enter Patient id");
					     patient_id = din.nextInt();
					    System.out.println("Enter Patient Name");
						 Patient_Name = din.next();
						System.out.println("Enter Doctor Id");
						Doctor_id = din.nextInt();
						System.out.println("Enter Doctor Name");
						Doctor_Name = din.next();
						System.out.println("Enter Doctor Specialization ");
					    Specialization = din.next();
						System.out.println("Enter Patient Disease");
						Disease = din.next();
						System.out.println("Enter Patient Email");
						String Patient_Email = din.next();
						System.out.println("Enter your Presciption");
						 Prescription = din.next();
						
						 Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "Root");
						String sql2 = "insert into prescription (patient_id,Patient_Name,Doctor_id,Doctor_Name,Specialization,Disease,Patient_Email,Prescription)values(?,?,?,?,?,?,?,?)";
						 PreparedStatement ps=con.prepareStatement(sql2);
						 ps.setInt(1, patient_id);
						 
						ps.setString(2, Patient_Name);
						ps.setInt(3, Doctor_id);
						ps.setString(4, Doctor_Name);
						ps.setString(5, Specialization);
						ps.setString(6, Disease);
						ps.setString(7, Patient_Email);
						ps.setString(8, Prescription);
						int i = ps.executeUpdate();
						if(i==1)
						{   
							 String host="smtp.gmail.com";   // Types of gmail sending hostname []
							  final String user="johnjoelraj2018@gmail.com";//change accordingly  
							  final String password1="gxjc yvsw vaop mukp";//change accordingly  
							    
							 
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
							    return new PasswordAuthentication(user,password1);  
							      }  
							    });  
							  
							   //Compose the message  
							    try {  
							     MimeMessage message = new MimeMessage(session);  
							     message.setFrom(new InternetAddress(user));  
							     message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
							     message.setSubject("CARE HOSPITAL -");  
							     message.setText("Hello "+Patient_Name+",\n  Your Checkup is Completed ,Your desease is "+Disease+".\nPlease "+Prescription+" \n\n Thanks for visited our Hospital..\n CARE HOSPITAL\n +1800 9898 3333  ");  
							       
							    //send the message  
							     Transport.send(message);  
							  
							     System.out.println(customcolors.BLUE+"message sent successfully...");  
							   
							     } catch (MessagingException e) {e.printStackTrace();}	
							System.out.println("Prescription sent successfully"+customcolors.RESET);
						}
						
						else
						{
							System.out.println("prescription sent failed ");
						}
					}
					else
					{
						
						throw new DoctorResourceException("Doctor You have choose wrong resource");
						
					}
					check=1;	
				}
			}	
			if(check==0)
			{
				System.out.println("invalid credintials");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void viewDoctors()
	{
		 try
		   {
			 Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "Root");
			   String sql1="select * from doctors";
			   Statement st=con.createStatement();
			   ResultSet rs=st.executeQuery(sql1);
			   System.out.println(customcolors.BLUE_BACKGROUND_BRIGHT+customcolors.WHITE_BOLD_BRIGHT+"Doctor id\tname\tSpecialization\tAvailableFrom\tAvailableTo"+customcolors.RESET);
			   while(rs.next())
			   {
				   System.out.println(rs.getInt(1)+"\t\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getString(4)+"\t"+rs.getString(5));
			   }
		   }
		   catch(Exception e)
		   {
			   e.printStackTrace();
		   }
	}
	public void addDoctors()
	{
		 try
		   {
			 Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "Root");
			   String dname,dspec;
			   String password;
			   String avto,avfrom;
			   Scanner din = new Scanner(System.in);
			   System.out.println("Enter Doctor Name");
			   dname = din.next();
			   System.out.println("Enter Doctor Specalization");
			   dspec = din.next();
			   System.out.println("Enter Availability From Time");
			   avfrom=din.next();
			   System.out.println("Enter Availability To Time");
			   avto = din.next();
			   System.out.println("Enter Doctor Password");
			   password=din.next();
			   String sql="insert into doctors(doctor_name,Specialization,availableFrom, availableTo,password) values(?,?,?,?,?)";
			   PreparedStatement ps = con.prepareStatement(sql);
			   ps.setString(1, dname);
			   ps.setString(2, dspec);
			   ps.setString(3,avfrom );
			   ps.setString(4,avto);
			   ps.setString(5, password);
			   int i = ps.executeUpdate();
			  if(i>0)
			  {
				  System.out.println("Doctor Added Success");
			  }
			  else
			  {
				  System.out.println("Doctor Adding Failed");
			  }
		   }
		   catch(Exception e)
		   {
			   e.printStackTrace();
		   }
	}
}

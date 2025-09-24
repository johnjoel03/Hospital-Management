package com.dr.care.hospital.patient;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
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

public class Patientn {
	private static final String UPDATE = null;
	String name,gender;
	int age;
	 public void addPatient(){
	      
	        try
			{
	        	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "Root");
	        	 Scanner din=new Scanner(System.in);
	        	 String firstname,lastname,mobileno,email,disease,availtime;
	        	 int did=0,pid=0,i;
				 System.out.println("Enter Your FirstName");
				 firstname = din.next();
				 System.out.println("Enter Your lastname");
				 lastname = din.next();
				 System.out.println("Enter Your mobileno");
				 mobileno = din.next();
				 System.out.println("Enter Your email");
				 email = din.next();
				 System.out.println("Enter Your disease");
				 disease = din.next();
				 //retrive doctor
				
					  // Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/hospital", "root", "4577");
					   String sql1="select * from doctors";
					   Statement st=con.createStatement();
					   ResultSet rs=st.executeQuery(sql1);
					   System.out.println(customcolors.RED_BOLD+"Doctor id\tname\tSpecialization\tAvailableFrom\tAvailableTo"+customcolors.RESET);
					   while(rs.next())
					   {
						   System.out.println(rs.getInt(1)+"\t\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getString(4)+"\t"+rs.getString(5));
					   }
				   
				  System.out.println("Choose Doctor Id");
				  did=din.nextInt();
				  
				 
						   	
											   String sql="insert into regpatient(firstname,lastname,Mobileno,email,disease,Status) values(?,?,?,?,?,?)";
												  PreparedStatement ps = con.prepareStatement(sql);
												  ps.setString(1, firstname);
												   ps.setString(2, lastname);
												   ps.setString(3, mobileno);
												   ps.setString(4, email);
												   ps.setString(5, disease);
												   ps.setString(6, "Approved");
												   i = ps.executeUpdate();
												   if(i==1)
														{  
													   String host="smtp.gmail.com";   // Types of gmail sending hostname []
														  final String user="johnjoelraj2018@gmail.com";//change accordingly  
														  final String password="gxjc yvsw vaop mukp";//change accordingly  
														    
														 
														String to=email;//change accordingly  
														  
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
														     message.setText("MR/MS "+firstname+",\n WELCOME TO CARE HOSPITAL. \n\n Your registration has been completed successfully. \n\n THANKYOU \n CARE HOSPITAL \n +1800 7676 2222.");  
														       
														    //send the message  
														     Transport.send(message);  
														  
														     System.out.println(customcolors.BLUE+"message sent successfully...");  
														   
														     } catch (MessagingException e) {e.printStackTrace();}
														   	System.out.println("Details Submitted Successfully"); 
											   System.out.println("Appointment is booked Success"+customcolors.RESET);
										   }
								 
							
			}
							
			
			catch(Exception e)
			{
				e.printStackTrace();
			}
	 }
	 public void viewPatient() {
		 try
		   {
			 Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "Root");
			  // String sql1="select * from patients";
			   String sql1="select * from  regpatient";
			   
			   Statement st=con.createStatement();
			   ResultSet rs=st.executeQuery(sql1);
			   System.out.println(customcolors.BLUE_BACKGROUND_BRIGHT+customcolors.WHITE_BOLD_BRIGHT+" pid\tfirstname\tlastname\t Mobileno\tdisease\tstatus"+customcolors.RESET);
			   while(rs.next())
			   {
				   System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t\t"+rs.getString(3)+"\t\t"+rs.getString(4)+"\t"+rs.getString(6)+"\t"+rs.getString(7));
			   }
		   }
		   catch(Exception e)
		   {
			   e.printStackTrace();
		   }
	 }
	  public static void patientprocess() throws SQLException
	 {
		  
		  String choice2="y";
		  int option;
		  Scanner din = new Scanner(System.in);
		
			
			while(choice2.equalsIgnoreCase("y"))
			{
				System.out.println("1. Patient Register");
				System.out.println("2. View Doctors");
				System.out.println("3. Patient Appointment Bookings");
				System.out.println("4. Cancel Appointment");
		      
		        
		        
				System.out.println(customcolors.RED_BOLD+"Enter your choice: "+customcolors.RESET);
				option = din.nextInt();
				switch(option)
				{
				case 1:
					
					 String firstname,lastname,mobileno,email,disease,availtime;
					 System.out.println("Enter Your FirstName");
					 firstname = din.next();
					 System.out.println("Enter Your lastname");
					 lastname = din.next();
					 System.out.println("Enter Your mobileno");
					 mobileno = din.next();
					 System.out.println("Enter Your email");
					 email = din.next();
					 System.out.println("Enter Your disease");
					 disease = din.next();

					 try
					   {
						 Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "Root");
						   String sql="insert into regpatient(firstname,lastname,Mobileno,email,disease) values(?,?,?,?,?)";
						   PreparedStatement ps = con.prepareStatement(sql);
						   ps.setString(1, firstname);
						   ps.setString(2, lastname);
						   ps.setString(3, mobileno);
						   ps.setString(4, email);
						   ps.setString(5, disease);
						   int i = ps.executeUpdate();
						  if(i>0)
						  {
							  System.out.println(customcolors.GREEN_BOLD+"Your Registration Success"+customcolors.RESET);
						  }
						  else
						  {
							  System.out.println("Your Registration Failed");
						  }
					   }
					   catch(Exception e)
					   {
						   e.printStackTrace();
					   }
					break;
				case 2:
					
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
					break;
				case 3:
					
				
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
						System.out.println("Enter Patient id");
						int Patient_id = sc.nextInt();
						
						String sql="insert into appointment( Patient_Name, Disease_Name,Doctor_Id,DateOfAppointment,Patient_Email,Patient_id,status)values(?,?,?,?,?,?,?)";
						PreparedStatement ps = con.prepareStatement(sql);
						ps.setString(1, Patient_Name);
						ps.setString(2, Disease_Name);
						ps.setInt(3, Doctor_Id);
						ps.setString(4, DateOfAppointment);
						ps.setString(5, Patient_Email);
						ps.setInt(6, Patient_id);
						ps.setString(7, "Pending");
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
							  
							     System.out.println(customcolors.BLUE+"message sent successfully...");  
							   
							     } catch (MessagingException e) {e.printStackTrace();}	
							System.out.println("Doctor Appointment is Successfully Completed");
						}
						else
						{
							System.out.println(customcolors.RED+"Doctor Appointment is Failed"+customcolors.RESET);
						}
						
					} catch (SQLException e) {
						
						e.printStackTrace();
					}
					break;
				case 4:
					
						Scanner din1=new Scanner(System.in);
						System.out.println("Enter patient id for cancel appointment");
						int Patient_id=din1.nextInt();
						Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "Root");
						Statement st=con.createStatement();
						String sql2= "UPDATE appointment set status = 'CANCEL ' WHERE Patient_id ="+Patient_id+"";
						int i=st.executeUpdate(sql2);
						if(i>0)
						{
							System.out.println("Cancelled appoiment sucessfully");
						}
					break;
				}
				 System.out.println(customcolors.RED+"Do You want to Continue Press Y or Exit Press N"+customcolors.RESET);
				  choice2 = din.next();
					
			}
	
			
			}
			 
			}
			
	 
	 



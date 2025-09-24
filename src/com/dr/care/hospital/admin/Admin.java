package com.dr.care.hospital.admin;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.dr.care.hospital.appointment.Appointment;
import com.dr.care.hospital.customdesign.customcolors;
import com.dr.care.hospital.doctor.Doctorn;
import com.dr.care.hospital.hms.HospitalManagementSystem2;
import com.dr.care.hospital.patient.Patientn;

public class Admin {

	public static void adminLogin() throws SQLException {
		int option;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "Root");
			String username,password;
			System.out.println("Enter Your UserName");;
			
			Scanner din = new Scanner(System.in);
			username = din.next();
			System.out.println("Enter Your Password");
			password = din.next();
			String sql="select * from admin where username='"+username+"' and password='"+password+"'";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if(rs.next())
			{
				System.out.println(customcolors.BLUE_BACKGROUND_BRIGHT+customcolors.WHITE_BOLD_BRIGHT+"  Welcome Admin  "+"\t\t"+customcolors.RESET );
				
				String choice1="y";
				while(choice1.equalsIgnoreCase("y"))
				{
					System.out.println("1. Add Patient");
			        System.out.println("2. View Patients   ");
			        System.out.println("3. Add Doctor      ");
			        System.out.println("4. View Doctors    ");
			        System.out.println("5. Book Appointment");
			        System.out.println("6. View Appointment");
			        System.out.println("7. Main Menu");
			        System.out.println(customcolors.PURPLE_BOLD+"Enter your choice: "+customcolors.RESET);
					option=din.nextInt();
					  switch(option){
			          case 1:
			              // Add Patient
			        	  System.out.println("Add Patient Here");
			        	  Patientn p1=new Patientn();
			        	  p1.addPatient();
			              
			              break;
			          case 2:
			              // View Patient
			        	  System.out.println("View Patient Here");
			        	  Patientn p2=new Patientn();
			              p2.viewPatient();
			              break;
			          case 3:
			        	  //add doctor
			        	  System.out.println("Add Doctors");
			        	  Doctorn d1=new Doctorn ();
			              d1.addDoctors();
			              break;
			          case 4:
			              // View Doctors
			        	  System.out.println("View Doctors");
			        	  Doctorn d2=new Doctorn ();
			              d2.viewDoctors();
			              break;
			          case 5:
			              // Book Appointment
			        	 System.out.println("Book Appointments");
			        	 Appointment a1=new Appointment();
			             a1.BookAppointments();
			              break;
			          case 6:
			              // view Appointment
			        	 System.out.println("View Appointments");
			        	 Appointment a2=new Appointment();
			             a2.ViewAppointments1();
			              break;
			          case 7:
			        	   //Main Menu
			        	 // HospitalManagementSystem2 h2= new HospitalManagementSystem2();
			        	  HospitalManagementSystem2.main(null);
			              break;
			              
			              
			          case 8:
			              System.out.println("THANK YOU! FOR USING  CARE HOSPITAL MANAGEMENT SYSTEM!!");
			              return;
			          default:
			              System.out.println("Enter valid choice!!!");
			              break;
					  }
					  System.out.println(customcolors.RED_BOLD+"Do You want to Continue Press Y or Exit Press N"+customcolors.RESET);
					  choice1 = din.next();
				}
				
			}
			
			else
			{
				System.out.println("Login Failed ....");
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

	



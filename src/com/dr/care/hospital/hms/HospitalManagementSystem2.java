package com.dr.care.hospital.hms;
import java.sql.SQLException;
import java.util.Scanner;

import com.dr.care.hospital.admin.Admin;
import com.dr.care.hospital.customdesign.customcolors;
import com.dr.care.hospital.dbconnect.DbConnect;
import com.dr.care.hospital.doctor.Doctorn;
import com.dr.care.hospital.patient.Patientn;

public class HospitalManagementSystem2 {

	public static void main(String[] args) throws SQLException {
		int option;
		String choice;
		boolean connection = DbConnect.getConnection();
		if(connection==true)
		{
			System.out.println("Connected");
		}
		String choice1="y";
		while(choice1.equalsIgnoreCase("y"))
		{
			System.out.println(customcolors.BLUE_BACKGROUND_BRIGHT+customcolors.WHITE_BOLD_BRIGHT+" 😉 Welcome To Care Hospital 😉"+"\t"+customcolors.RESET );

			System.out.println(customcolors.BLACK_BOLD+" 1. Admin Login         ");
			System.out.println(" 2. Patient Register    ");
			System.out.println(" 3. Doctor Login        ");
			System.out.println(" 4. Exit                "+customcolors.RESET);

			System.out.println(customcolors.BLUE_BACKGROUND_BRIGHT+customcolors.WHITE_BOLD_BRIGHT+"Enter Your Option "+"\t\t"+customcolors.RESET);
			Scanner din = new Scanner(System.in);
			option = din.nextInt();
			switch(option)
			{
			case 1:Admin a = new Admin();
				   a.adminLogin();
				break;
			case 2: Patientn pat = new Patientn();
					pat.patientprocess();
				break;
			case 3:
					Doctorn dn=new Doctorn();
					dn.login();
					
			case 4:
				System.exit(1);
			}
			System.out.println("Do You want to Continue Press y or exit Press N");
			choice= din.next();
		}
	}


	}



package employeeMYSQL;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class EmployeeMySql {
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	Statement st;
	public EmployeeMySql() throws FileNotFoundException, SQLException, IOException {
		con=MyConnection.getMyConnection();
	}
	public void insertEmpData(int emp_id,String emp_name,int emp_age,String desiganation,String emp_Department, double emp_Salary) throws SQLException {
		pstmt=con.prepareStatement("insert into employeedetails values(?,?,?,?,?,?)");
		pstmt.setInt(1, emp_id);
		pstmt.setString(2, emp_name);
		pstmt.setInt(3, emp_age);
		pstmt.setString(4, desiganation);
		pstmt.setString(5, emp_Department);
		pstmt.setDouble(6, emp_Salary);
		int n =pstmt.executeUpdate();
		System.out.println(n+"record is inserted");
	}

	
	public void readEmpRecord() throws SQLException {
		st=con.createStatement();
		rs=st.executeQuery("select*from employeedetails");
		while(rs.next())
		{
			System.out.print(rs.getInt(1)+"\t");
			System.out.print(rs.getString(2)+"\t");
			System.out.print(rs.getInt(3)+"\t");
			System.out.print(rs.getString(4)+"\t");
			System.out.print(rs.getString(5)+"\t");
			System.out.print(rs.getDouble(6)+"\t");
			System.out.println();

		}

	}
	public void updateEmpRecord(int emp_id,String emp_name,int emp_age,String desiganation,String emp_Department, double emp_Salary) throws SQLException {
		String s ="update employeedetails set emp_name=?,emp_age=?,desiganation=?,emp_Department=?,emp_Salary=? where emp_id=?";
		pstmt =con.prepareStatement(s);
		pstmt.setString(1, emp_name);
		pstmt.setInt(2, emp_age);
		pstmt.setString(3, desiganation);
		pstmt.setString(4, emp_Department);
		pstmt.setDouble(5, emp_Salary);
		pstmt.setInt(6, emp_id);

		
		int i=pstmt.executeUpdate();
		System.out.println(i+"record updated");
}
	public void deleteEmpRecord(int emp_id) throws SQLException
	{
		String s= "delete from employeedetails where emp_id=?";
		pstmt =con.prepareStatement(s);
		pstmt.setInt(1, emp_id);
		
		int r=pstmt.executeUpdate();
		System.out.println(r+"deleted successfully");


	}
	public void selectEmpData(int emp_id) throws SQLException {
		String s="select*from employeedetails where emp_id=?";
		pstmt=con.prepareStatement(s);
		pstmt.setInt(1, emp_id);
		rs=pstmt.executeQuery();
		while(rs.next())
		{
			System.out.println(rs.getInt(1)+"\t");
			System.out.println(rs.getString(2)+"\t");
			System.out.println(rs.getInt(3)+"\t");
			System.out.println(rs.getString(4)+"\t");
			System.out.println(rs.getString(5)+"\t");
			System.out.println(rs.getDouble(6)+"\t");
			System.out.println();
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub
		int ch;
		int ch1; 
		Scanner sc=new Scanner(System.in);
		EmployeeMySql a=new EmployeeMySql();
		do {
			System.out.println("**************Wellcome To Employee DataBase Management System************");
			System.out.println("---->press\n 1. For Save Employee into the table  \n 2.For read all Employee from the table \n 3. update Employee details on the table \n 4. delete Employee on the basis of Id \n 5. Search Employee on the basis of id  ");
			
			ch=sc.nextInt();
			switch (ch)
			{
			case 1:
				int emp_id,emp_age;
				String emp_name,desiganation,emp_Department;
				Double emp_Salary;
				
				
				System.out.println("Please enter emp_id ,emp_name ,emp_age,desiganation,emp_Department,emp_Salary----");
				emp_id=sc.nextInt();
				emp_name=sc.next();
				emp_age=sc.nextInt();
				desiganation=sc.next();
				emp_Department=sc.next();
				emp_Salary=sc.nextDouble();
				
				a.insertEmpData(emp_id, emp_name, emp_age,desiganation,emp_Department,emp_Salary);
				a.readEmpRecord();
				System.out.println("Inserted Successfully");
				break;
			case 2:
				a.readEmpRecord();
			break;
			case 3:
				
				
				System.out.println("enter id which you want to update----- :");
				emp_id =sc.nextInt();
				System.out.println("enter the emp_name,emp_age ,desiganation,emp_Department,emp_Salary-----:");
				emp_name=sc.next();
				emp_age=sc.nextInt();
				desiganation=sc.next();
				emp_Department=sc.next();
				emp_Salary=sc.nextDouble();
				
				a.updateEmpRecord(emp_id, emp_name, emp_age,desiganation,emp_Department,emp_Salary);
				a.readEmpRecord();
				break;
			
			case 4:
				System.out.println("enter emp_id-----");
				emp_id=sc.nextInt();
				a.deleteEmpRecord(emp_id);
				a.readEmpRecord();
				break;
			case 5:
				System.out.println("enter emp_id:-----");
				emp_id=sc.nextInt();
				a.selectEmpData(emp_id);
				
				break;
			
				default:
					System.out.println("invalid choice");
						}
			System.out.println("do you want to continue(y/n)");
			ch1=sc.next().charAt(0);
		}while(ch1=='y'||ch1=='n');
	}

}

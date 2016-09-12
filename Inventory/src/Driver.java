import java.sql.*;
import java.util.Scanner;

public class Driver {
	
		
	public static void main(String[] args) {
		
		try{
			
			Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/Inventory?autoReconnect=true&useSSL=false", "root", "******");
			Statement myStmt = connect.createStatement();
			ResultSet resultSet = myStmt.executeQuery("Select * from Information_Schema.Tables where Table_Name = 'Products'");
			if(resultSet.next()){
				myStmt.executeUpdate("Drop Table Products");
			}
			myStmt.executeUpdate("Create Table Products (ID int NOT NULL AUTO_INCREMENT, name VARCHAR(20), price DOUBLE, category VARCHAR(20), rating DOUBLE, PRIMARY KEY (ID))");									
			Scanner scan = new Scanner(System.in);
			boolean quit = false;
			while(!quit){
				System.out.println("Welcome to you Inventory! What do you want to do?");
				System.out.println("1 - Add \n2 - Delete \n3 - Update \n4 - Sort by name \n5 - Sort by rating \n6 - View Inventory \n7 - Quit");
				int value = scan.nextInt();
				switch(value){
					case 1 : 
						System.out.println("Enter your product's");
						System.out.print("Name: ");
						String name = scan.next();
						System.out.print("Price: ");
						double price = scan.nextDouble();
						System.out.print("Category: ");
						String category = scan.next();
						System.out.print("Rating: ");
						double rating = scan.nextDouble();
						myStmt.executeUpdate("Insert into Products (name, price, category, rating) Values("+"'"+name+"',"+price+",'"+category+"',"+rating+")");
						break;
					case 2 :
						System.out.print("Please enter the name of the Product you want to Delete: ");
						String nameDelete = scan.next();
						myStmt.executeUpdate("Delete from Products where name="+"'"+nameDelete+"'");
						break;
					case 3 :
						System.out.print("What product do you want to update?: ");
						String nameUpdate = scan.next();
						System.out.print("Price: ");
						double priceUpdate = scan.nextDouble();
						System.out.print("Rating: ");
						double ratingUpdate = scan.nextDouble();
						myStmt.executeUpdate("Update Products Set price="+priceUpdate+",rating="+ratingUpdate+"where name="+"'"+nameUpdate+"'");						
						break;
					case 6:
						ResultSet viewResult = myStmt.executeQuery("Select * from Products");
						while(viewResult.next()){
							System.out.println(viewResult.getString("ID")+" "+viewResult.getString("name")+" "+viewResult.getString("price")+" "+viewResult.getString("category")+" "+viewResult.getString("rating"));
						}
						if(!viewResult.first()){
							System.out.println("No Products in Inventory");
						}
						break;	
					case 7:
						
						quit = true;
						
				}
			}
			scan.close();

			
//			myStmt.executeUpdate("Insert into Products (name, price, category, rating) Values('Table', 10.40, 'Furniture', 3.2)");
//			ResultSet result = myStmt.executeQuery("Select * from Products");
//			while(result.next()){
//				System.out.println(result.getString("ID")+" "+result.getString("Name")+" "+result.getString("price")+" "+result.getString("category")+" "+result.getString("rating"));
//			}
				
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		

	}

}

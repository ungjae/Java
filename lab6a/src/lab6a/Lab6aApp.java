package lab6a;

/**
 * <b>Title:</b> Lab 6a:<br>
 * <b>Filename:</b> Lab6aApp.java<br>
 * <b>Date Written:</b> November 11, 2018<br>
 * <b>Due Date:</b> November 11, 2018<br>
 *
 * <p>
 * <b>Description:</b><br>
 * Driver to test the unorderedArrayList class methods on 
 * Product objects which are loaded from a database
 * also created methods to find and remove a product from a given
 * unorderedArrayList of products
 * </p>
 *
 * @author Ung Jae Yun
 */

import java.sql.*;
import java.util.Arrays;

public class Lab6aApp {
	public static void main(String[] args) {
		
		UnorderedArrayList<Product> list1 = new UnorderedArrayList<>();
		UnorderedArrayList<Product> list2 = new UnorderedArrayList<>(5);
		
		Product[] products = getData();
		
		for (int i = 0; i < products.length; i++) {
			list2.add(products[i]);
		}
		
		// test the testSearch method on an existing item
		System.out.println(list2+"\n");
		Product product1 = new Product();
		product1.setId("264j45");
		System.out.println(testSearch(product1, list2));
		
		// test the testSearch method on a non-existing item
		Product product2 = new Product();
		product2.setId("344d97");
		System.out.println(testSearch(product2, list2));
		
		// test unorderedArrayList functions
		System.out.println(list2.indexOf(product1));
		System.out.println(list2.lastIndexOf(product1));
		System.out.println(list2.contains(product1) + "\n");
		
		// test unorderedArrayList functions on empty list
		System.out.println(list1.indexOf(product1));
		System.out.println(list1.lastIndexOf(product1));
		System.out.println(list1.contains(product1) + "\n");
				
		// test testRemove function
		System.out.println(list2);
		testRemove(new Product("355d98",1,1), list2);
		System.out.println(list2);
		
		// test unorderedArrayList remove function
		System.out.println(list2.remove(new Product("456u78",1,1)) + "\n"); // beginning of list
		System.out.println(list2);
		
		System.out.println(list2.remove(new Product("653o09",1,1)) + "\n"); // end of list
		System.out.println(list2);
		
		System.out.println(list1.add(new Product("344d97",1,1)) + "\n"); // list of size 1
		System.out.println(list1.remove(new Product("344d97",1,1)) + "\n");
		
		// test remove function on an empty list
		System.out.println(list1.remove(new Product("344d97",1,1)) + "\n"); 
		
		// Question 17
		// I do not receive errors when commenting out the equals method in the Product class
		// since it then calls the equals method from generic comparable object
		// however we do not get the desired outputs since the equals method the code is calling
		// is generic and does not know to compare the product IDs when looking at products
		// hence giving us a null or false when we try to compare two different products
	}

	/**
	 * getData method -- gets the products from an SQLite database
	 * @return the an array of products
	 */
	public static Product[] getData(){		
		Statement stmt = null;
		int records = 0;
		Product[] products = null; 
		try {
			Class.forName("org.sqlite.JDBC");
			Connection c = DriverManager.getConnection("jdbc:sqlite:C:/Users/ungja/eclipse-workspace/lab6a/products.db");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");			

			stmt = c.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT * FROM products;");
			ResultSetMetaData rsmd = rs.getMetaData();			
			
			for(int i = 1; i <= rsmd.getColumnCount(); i++)	{
				System.out.print(String.format("%-12s", rsmd.getColumnLabel(i)) + "\t");
				System.out.print(rsmd.getColumnTypeName(i) + "\t");
				System.out.println(rsmd.getPrecision(i));
			}
			
			rs = stmt.executeQuery("select count (*) AS totalRecords from products");
			int totalRecords = rs.getInt("totalRecords");
			System.out.println("Records: " + totalRecords);
			
			rs = stmt.executeQuery("SELECT * FROM products;");
			if(rs != null){
				products = new Product[totalRecords];
				while (rs.next()) {
					String prodId = rs.getString("prodId");
					int quantity = rs.getInt("quantity");
					double price = rs.getFloat("price");

					System.out.println(String.format("%3s %-6s %3d %6.2f",
							records, prodId, quantity, price));	
					products[records++] = new Product(prodId, quantity, price);
				}
				System.out.println();
			}
			
			stmt.close();
			c.commit();
			c.close();
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch(SQLException se){
			System.err.println(se.getClass().getName() + ": " + se.getMessage());
		}
		return products;
	}
	/**
	 * search for an item in a given list
	 * @param searchItem the object we are looking for
	 * @param ual the unorderedArrayList that we are looking into find searchItem
	 * @return string stating where the searchItem is if found, or 'cannot find' if not found
	 */
	public static String testSearch(Product searchItem, UnorderedArrayList<Product> ual) {
		String str = "Cannot find Product " + searchItem.getId() + "\n";
		int i = ual.indexOf(searchItem);
		if (i > 0) {
			str = "Product " + searchItem.getId() + " is at index " + i + "\n";
		}
		
		return str;
	}
	/**
	 * removes searchItem from the given unorderedArrayList ual
	 * @param searchItem the object we are trying to remove
	 * @param ual the unorderedArrayList we are trying to remove searchItem from
	 * @return <i>null</i> if not found <i>searchItem</i> if found
	 */
	public static void testRemove(Product searchItem, UnorderedArrayList<Product> ual) {
		ual.remove(searchItem);
	}
}
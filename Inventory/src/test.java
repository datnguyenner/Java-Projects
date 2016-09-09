import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import java.io.File;
import java.util.Scanner;

import org.junit.Before;

public class test {
	
	Inventory sut = new Inventory();
	List<Product> inventory = new ArrayList<>();
	@Before
	public void init() throws Exception{
		Scanner scan = new Scanner(new File("C:/Users/Dat/OneDrive/Documents/products.txt"));
		while (scan.hasNextLine()){
			String productLine = scan.nextLine();
			String[] productProperties = productLine.split(" ");
			int id = Integer.parseInt(productProperties[0]);
			String name = productProperties[1];
			double price = Double.parseDouble(productProperties[2]);
			String category = productProperties[3];
			double rating = Double.parseDouble(productProperties[4]);
			inventory.add(new Product(name,price,id,category,rating));
		}
		scan.close();
		for(Product prod:inventory){
			sut.add(prod);
		}
	}
	
	@Test
	public void testadd(){
		
		assertThat(sut.getInventory().size(), is(6));
	
		sut.add(new Product("Pot", 7.99, 7, "Kitchen", 4.0));
		sut.add(new Product("Knife Set", 9.99, 8, "Kitchen", 3.5));
		sut.add(new Product("Speakers", 19.99, 9, "Electronic", 4.0));
		sut.add(new Product("Nike Slippers", 14.99, 10, "Apparel", 4.5));

		assertThat(sut.getInventory().size(), is(10));
	}
	
	@Test
	public void test_search_name(){
		assertThat(sut.search("Table").length, is(2));
		assertThat(sut.search("Spoons").length, is(0));
		assertThat(sut.search("Laptop").length, is(1));		
		assertThat(Arrays.toString(sut.search("Mouse")), is("[ID:5 Name:Mouse Price:6.0 Category:Electronic Rating:4.0]"));

	}
	@Test
	public void test_search_price(){
		assertThat(sut.search(6.00).length, is(2));
		assertThat(sut.search(100.00).length, is(0));
		assertThat(sut.search(10.40).length, is(1));		
		assertThat(Arrays.toString(sut.search(500.00)), is("[ID:6 Name:Laptop Price:500.0 Category:Electronic Rating:3.7]"));
	}
	
	@Test
	public void test_search_id(){		
		assertThat(sut.search(1).toString(), is("ID:1 Name:Table Price:10.4 Category:Furniture Rating:3.2"));
		assertThat(sut.search(111).toString(), is(""));
		assertThat(sut.search(4).toString(), is("ID:4 Name:USB Price:5.0 Category:Electronic Rating:4.0"));
	}
	
	@Test
	public void test_remove_by_id(){	
		sut.removeProduct(4);
		assertThat(sut.getInventory().size(), is(5));
		sut.removeProduct(4);
		assertThat(sut.getInventory().size(), is(5));

	}
	
	@Test
	public void test_sort_by_Rating(){	
		
		sut.sortByRating();
		Product[] sorted = sut.getInventory().toArray(new Product[sut.getInventory().size()]);
		assertThat(sorted[0].toString(), is("ID:3 Name:Table Price:6.0 Category:Furniture Rating:2.2"));
		assertThat(sorted[5].toString(), is("ID:4 Name:USB Price:5.0 Category:Electronic Rating:4.0"));
		System.out.println(sut.getInventory());

	}
	
	@Test
	public void test_overview(){	
		
		sut.overview();

	}

}

import java.util.*;


public class Inventory {
	
	Set<Product> inventory = new HashSet<Product>();
	
	public Set<Product> getInventory() {
		return inventory;
	}

	public void add(Product product){
		inventory.add(product);
	}
	
	public Product[] search(String name){
		List<Product> foundProducts = new ArrayList<>();
		for(Product prod:inventory){
			if(prod.getName().equals(name)){
				foundProducts.add(prod);
			}
		}
		if(foundProducts.size()==0){
			System.out.println(name+" product is not in Inventory");
			return new Product[0];
		}else{
			return foundProducts.toArray(new Product[foundProducts.size()]);
		}		
	}
	public Product[] search(double price){
		List<Product> foundProducts = new ArrayList<>();
		for(Product prod:inventory){
			if(prod.getPrice()==price){
				foundProducts.add(prod);
			}
		}
		if(foundProducts.size()==0){
			System.out.println(price+"There are no product with price "+price+" in Inventory");
			return new Product[0];
		}else{
			return foundProducts.toArray(new Product[foundProducts.size()]);
		}
	}
	public Product search(int id){
		for(Product prod:inventory){
			if(prod.getId()==id){
				return prod;
			}
		}
		return new Product();
	}
	public void removeProduct(int id){
		Set<Product> newInventory = new HashSet<Product>();
		for(Product prod:inventory){
			if(prod.getId()!=id){
				newInventory.add(prod);
			}
		}
		inventory.clear();
		inventory = newInventory;
	}
	
	public void sortByRating(){
		
		Set<Product> sortedInventory = new TreeSet<Product>(new ratingComparator());
		sortedInventory.addAll(inventory);
		this.inventory = sortedInventory;		
	}
	
	public void overview(){
		Map<String, TreeMap<String,Integer>> categories = new TreeMap<>();
		for(Product prod:inventory){
			String category = prod.getCategory();
			if(categories.containsKey(category)){
				TreeMap<String,Integer> productQuantities = categories.get(category);
				if(productQuantities.containsKey(prod.getName())){
					productQuantities.put(prod.getName(), productQuantities.get(prod.getName())+1);
				}else{
					productQuantities.put(prod.getName(), 1);
				}
				categories.put(category, productQuantities);				
			}else{
				TreeMap<String,Integer> productQuantities = new TreeMap<>();
				productQuantities.put(prod.getName(), 1);
				categories.put(category, productQuantities);				
			}
		}
		System.out.println(categories);
	}
	
}

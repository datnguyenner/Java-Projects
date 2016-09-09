
public class Product {
	
	private String name;
	private double price;
	private int id;
	private String category;
	private double rating;
	
	public double getRating() {
		return rating;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Product(String name, double price, int id, String category, double rating){
		this.name=name;
		this.price=price;
		this.id=id;
		this.category=category;
		this.rating=rating;

	}
	public Product(){
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override 
	public String toString(){
		if(this.id==0 || this.name.equals(null)){
			return "";
		}
		return "ID:"+this.id+" Name:"+this.name+" Price:"+this.price+" Category:"+this.category+" Rating:"+this.rating;
	}

}

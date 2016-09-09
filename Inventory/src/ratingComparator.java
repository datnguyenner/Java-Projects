import java.util.Comparator;

public class ratingComparator implements Comparator<Product>{

	@Override
	public int compare(Product o1, Product o2) {
		if(o1.getRating()>o2.getRating()){
			return 1;
		}else if(o1.getRating()<o2.getRating()){
			return -1;
		}else
			return o1.getName().compareTo(o2.getName());
	}

}

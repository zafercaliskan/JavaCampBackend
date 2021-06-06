package kodlamaio.northwind.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.northwind.business.abstracts.ProductService;
import kodlamaio.northwind.dataAccess.abstracts.ProductDao;
import kodlamaio.northwind.entities.concretes.Product;

@Service // Bu class projede servis görevi göreceğini belirtiyoruz.
public class ProductManager implements ProductService {

	private ProductDao productDao;
	
	//@Autowired ile productDao'nun instance olabilecek bir sınıf üretir. (newler) ve soyut productDao'ya
	//somutunu atar. Spring tarafında tanımdır. Bunu bizim yerimize yapar. 
	@Autowired //Java dünyasında çok popüler.
	public ProductManager(ProductDao productDao) {
		super();
		this.productDao = productDao;
	}

	@Override
	public List<Product> getAll() {

		return this.productDao.findAll();
	}
}
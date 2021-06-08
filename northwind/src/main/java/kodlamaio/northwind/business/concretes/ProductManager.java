package kodlamaio.northwind.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.northwind.business.abstracts.ProductService;
import kodlamaio.northwind.core.utilities.results.DataResult;
import kodlamaio.northwind.core.utilities.results.Result;
import kodlamaio.northwind.core.utilities.results.SuccessDataResult;
import kodlamaio.northwind.core.utilities.results.SuccessResult;
import kodlamaio.northwind.dataAccess.abstracts.ProductDao;
import kodlamaio.northwind.entities.concretes.Product;

@Service // Bu class projede servis görevi göreceğini belirtiyoruz.
public class ProductManager implements ProductService { //Asıl işi yaptığımız yer.

	private ProductDao productDao;
	
	//@Autowired ile productDao'nun instance olabilecek bir sınıf üretir. (newler) ve soyut productDao'ya
	//somutunu atar. Spring tarafında tanımdır. Bunu bizim yerimize yapar. 
	@Autowired //Java dünyasında çok popüler. productDao'u implemente eden somut sınıfı bulur onu newleyip atamasını yapar.
	public ProductManager(ProductDao productDao) {  //constructor injection: bir baımlılığı constructor üzerinden injekte etmek. 
		super();
		this.productDao = productDao;
	}

	@Override
	public DataResult<List<Product>> getAll() {
		return new SuccessDataResult<List<Product>>(this.productDao.findAll(), "Data listelendi"); 
		//Burada newlemenin bir sakıncası yoktur. Çünkü burası bir bağımlılık oluşturmaz. 
		//Burada SuccessDataResult bize bir entity'nin dönüşünü anlatır. //Entity'e özeldir. Entity'in newlenmesinin bir sakıncası yoktur. Newlenebilir.
	}

	@Override
	public Result add(Product product) {
		this.productDao.save(product);
		return new SuccessResult("Ürün eklendi");
	}
}
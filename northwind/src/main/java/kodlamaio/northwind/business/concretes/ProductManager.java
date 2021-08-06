package kodlamaio.northwind.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import kodlamaio.northwind.business.abstracts.ProductService;
import kodlamaio.northwind.core.utilities.results.DataResult;
import kodlamaio.northwind.core.utilities.results.Result;
import kodlamaio.northwind.core.utilities.results.SuccessDataResult;
import kodlamaio.northwind.core.utilities.results.SuccessResult;
import kodlamaio.northwind.dataAccess.abstracts.ProductDao;
import kodlamaio.northwind.entities.concretes.Product;
import kodlamaio.northwind.entities.dtos.ProductWithCategoryDto;

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
	
	@Override
	public DataResult<Product> getByProductName(String productName) {
		return new SuccessDataResult<Product>(this.productDao.getByProductName(productName),"Data listelendi");	
	}

	@Override
	public DataResult<Product> getByProductNameAndCategoryId(String productName, int categoryId) {
		//business codes

		return new SuccessDataResult<Product>(this.productDao.getByProductNameAndCategory_CategoryId(productName,categoryId), "Data listelendi");
	}

	@Override
	public DataResult<List<Product>> getByProductNameOrCategoryId(String productName, int categoryId) {
		return new SuccessDataResult<List<Product>>(this.productDao.getByProductNameOrCategory_CategoryId(productName, categoryId), "Data listelendi");
	}

	@Override
	public DataResult<List<Product>> getByCategoryIdIn(List<Integer> categories) {
		return new SuccessDataResult<List<Product>>(this.productDao.getByCategoryIn(categories), "Data listelendi");
	}

	@Override
	public DataResult<List<Product>> getByProductNameContains(String productName) {
		return new SuccessDataResult<List<Product>>(this.productDao.getByProductNameContains(productName),"Data listelendi");
	}

	@Override
	public DataResult<List<Product>> getByProductNameStartsWith(String productName) {
		return new SuccessDataResult<List<Product>>(this.productDao.getByProductNameStartsWith(productName),"Data listelendi");
	}

	@Override
	public DataResult<List<Product>> getByNameAndCategory(String productName, int categoryId) {
		return new SuccessDataResult<List<Product>>(this.productDao.getByNameAndCategory(productName,categoryId),"Data listelendi");
	}

	@Override
	public DataResult<List<Product>> getAll(int pageNo, int pageSize) {
		//Pageable versiyonu var findAll'ın. Bizden pageable istiyor. 
		Pageable pageable = PageRequest.of(pageNo-1, pageSize); //Burasıda pageno ve page size ister.
		//Burada -1 dememizin sebebi page 0 dan başladığı için.
		return new SuccessDataResult<List<Product>>(this.productDao.findAll(pageable).getContent()); 
		//Pageable kullandığımızda page döndürdüğü için onu atamak için getContent dememiz gerekiyor.
	}

	@Override
	public DataResult<List<Product>> getAllSorted() {
		//Sort nesnesi tanımlıyoruz. Direction yani hangi yönde sıralayayım. asc artan desc azalan. asc ->artan  desc -> düşen. 
		//Bunu parametre olarakda alabiliriz. //Productname göre dort eder.
		Sort sort = Sort.by(Sort.Direction.DESC,"productName");
		return new SuccessDataResult<List<Product>>(this.productDao.findAll(sort),"Başarılı");
	}
	
	@Override
	public DataResult<List<ProductWithCategoryDto>> getProductWithCategoryDetails() {
		return new SuccessDataResult<List<ProductWithCategoryDto>>(this.productDao.getProductWithCategoryDetails(), "Data listelendi");
	}
}
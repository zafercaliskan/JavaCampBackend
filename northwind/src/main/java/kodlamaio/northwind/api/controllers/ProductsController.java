package kodlamaio.northwind.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import kodlamaio.northwind.business.abstracts.ProductService;
import kodlamaio.northwind.core.utilities.results.DataResult;
import kodlamaio.northwind.core.utilities.results.Result;
import kodlamaio.northwind.entities.concretes.Product;
import kodlamaio.northwind.entities.dtos.ProductWithCategoryDto;

@RestController // Sen bir controllersın demek. Her iş yapan sınıflara anatayon ekledik. Entity, Service etc. Java olmayanlarda tanısın diye RestController tanımlarız.
@RequestMapping("/api/products") // Farklı controllerlar olabilir. //http://localhost:8080/api/products bir istek gelirse onu karşılayacak budur demiş oluyoruz.
@CrossOrigin
public class ProductsController {
	private ProductService productService;

	// @Autowired
	//productService lazım diyor. Projeyi tarıyor. Kim productService'i implements etmiş bakıyor. productService'i productmanager implement ettiğini buluyor. 
	//Arka planda ProductManager'ı newliyor. Onu newlerken productDao'ya ihtiyaç duyuyor onuda newliyor. Newlenmiş productmanager'i alt satırdaki
	//ProductService productService'e yerleştiriyor. Bizim newlememize gerek kalmıyor. Buradaki problem birden fazla somut sınıf varsa @Autowired patlar. 
	//Onu ilerleyen derslerde handle edeceğiz. Java dünyasıonda oturmuş sistem böyle. Solid zafiyeti var burada.

	@Autowired
	public ProductsController(ProductService productService) {
		//productService bir interface. Tek başına bir anlamı yok. Fakat referans tutucu olması itibariyle bu adam manager'ı tutabilir. 
		//Çünkü kendisinden implemente edilen classları tutabilir.  @Autowired da tüm projeyi gezer o interface'i implementye eden classı managerı bulur 
		//onu newler o referansı productService' arkadaşına atar.
		super();
		this.productService = productService;
	}

	@GetMapping("/getall") // http://localhost:8080/api/products/getall'e istek gelince burası çalışır.
	public DataResult<List<Product>> getAll() {
		return this.productService.getAll();
	}
	
	@GetMapping("/getProductWithCategoryDetails")
	public DataResult<List<ProductWithCategoryDto>> getProductWithCategoryDetails(){
		return this.productService.getProductWithCategoryDetails();
	}

	@PostMapping("/add")
	public Result add(@RequestBody Product product) {
		return this.productService.add(product);
	}

	@GetMapping("/getByProductName")
	public DataResult<Product> getByProductName(@RequestParam String productName) {
		// Yapılan isteğin parametrelerine bak. Bu isimde bir parametre olacak onu oku demiş oluyoruz.
		return this.productService.getByProductName(productName);
	}

	// @RequestParam içerisinde belirttik çünkü swaggerda sıralaması yanlış gelebiliyor. 
	// Yanlış gelince isteği yanlış oluşturuyor. Hataya neden oluyor o yüzden.
	@GetMapping("/getByProductNameAndCategoryId")
	public DataResult<Product> getByProductNameAndCategoryId(@RequestParam("productName") String productName, @RequestParam("categoryId") int categoryId) {
		return this.productService.getByProductNameAndCategoryId(productName, categoryId);
	}

	@GetMapping("/getByProductNameContains")
	public DataResult<List<Product>> getByProductNameContains(@RequestParam String productName) {
		return this.productService.getByProductNameContains(productName);
	}

	@GetMapping("/getAllByPage")
	DataResult<List<Product>> getAll(int pageNo, int pageSize) {
		return this.productService.getAll(pageNo, pageSize);
	}

	@GetMapping("/getAllDesc")
	public DataResult<List<Product>> getAllSorted() {
		return this.productService.getAllSorted();
	}
}
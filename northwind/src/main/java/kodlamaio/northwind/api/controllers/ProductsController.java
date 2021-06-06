package kodlamaio.northwind.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.northwind.business.abstracts.ProductService;
import kodlamaio.northwind.entities.concretes.Product;

@RestController  //Sen bir controllersın demek. Her iş yapan sınıflara anatayon ekledik. Entity, Service etc.
@RequestMapping("/api/products") //Farklı controllerlar olabilir. //http://localhost:8080/api/products bir istek gelirse onu karşılayacak budur demiş oluyoruz.
public class ProductsController {
	private ProductService productService;
	
	//@Autowired
	//productService lazım diyor. Projeyi tarıyor. Kim productService'i implements etmiş bakıyor. productService'i productmanager implement ettiğini buluyor. 
	//Arka planda ProductManager'ı newliyor. Onu newlerken productDao'ya ihtiyaç duyuyor onuda newliyor. Newlenmiş productmanager'i alt satırdaki
	//ProductService productService'e yerleştiriyor. Bizim newlememize gerek kalmıyor. Buradaki problem birden fazla somut sınıf varsa @Autowired patlar. 
	//Onu ilerleyen derslerde handle edeceğiz. Java dünyasıonda oturmuş sistem böyle. Solid zafiyet, var burada.

	@Autowired
	public ProductsController(ProductService productService) {		
		super();
		this.productService = productService;
	}

	@GetMapping("/getall") //http://localhost:8080/api/products/getall'e istek gelince burası çalışır.
	public List<Product> getAll(){
		return this.productService.getAll();
	}
}
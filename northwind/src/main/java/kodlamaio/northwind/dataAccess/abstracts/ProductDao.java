package kodlamaio.northwind.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kodlamaio.northwind.entities.concretes.Product;
//Interface interface'i extend eder.
public interface ProductDao extends JpaRepository<Product,Integer>{
	//getBy görünce tablolara bakıyor. Tablodaki ilgili kolona göre where koşulu oluşturuyor.
	Product getByProductName(String productName);	
	
	//findBy getBy aynıdır. İkiside olur. ProductName 1. kolon CategoryId 2. kolon. //Bizim yerimize "and" operatörü olan where koşulu yazıyor.
	Product getByProductNameAndCategory_CategoryId(String productName, int categoryId); 	
	
	 //Burada hangi veri türünü döndürdüğümüz önemli. Yukarıda tek data getirirken burada birden fazla getirir.
	//"Or" operatörü olan where koşulu yazar. Yani 2 kolondan her hangi biriyle eşleşen koşul doğru olan alanları getirir.
	//Burada products da şuan category alanı yok. onu yorum satırı yapmıştık. Category'in içindeki category'e göre mapliyor.
	//O yüzden 2 defa categoryid olunca kızacağı için yorum satırı yapmıştık. Burada Category dediğimizde categoryinin primary alanına göre sorgular.
	//Ürünler kategoryle hangi alan üzerinden ilşkilendiriliyorsa ona göre arar. Oda primary key.
	List<Product> getByProductNameOrCategory_CategoryId(String productName, int categoryId);	
	  
	//In birden fazla category id gönderip eşlesen tüm dataları getirebiliriz.	  
	List<Product> getByCategoryIn(List<Integer> categories); 
	  
	List<Product> getByProductNameContains(String productName);
	  
	//Ürün isimleri verilen parametreye göre başlayan dataları getirir.
	List<Product> getByProductNameStartsWith(String productName);
	  
	//Burada veritabanımız Product objemiz gibi düşünmeliyiz. Çünkü kolon isimlerini ona göre yazıyoruz. postgrede nasıl olduguyla ilgilenmiyoruz.
	@Query("From Product where productName=:productName and category.categoryId=:categoryId")  //Burada veritabanında nasıl yazdığını unut. 	  
	//Burada entities'de Product.javada nasıl verdiysen öyle kullanarak yazacaksın. select * 'a da gerek yok from ile başlıyoruz.	  
	List<Product> getByNameAndCategory(String productName, int categoryId);	  
	  
	//select * from products where product_name=bisey and categoryId=bisey -> bu sql sorgusu	
}

/// JpaRepository her nesneyle çalışabilecek bir yapıda. Yapılandırmaya göre çalışır.
//JpaRepository ne yapıyor? Verdiğin veri tipi için entity anatasyonu ile süslenmiş nesne için yani 
//Product için. Primary key alanınıda ver ki sorguları ve intelisence'i ona göre ayarlar.
//Primary key alanı integer olduğu için interger veririz.
//*** Product için crud operasyonlarımız hazır *** 

//Burada JpaRepository'ı extension'ı vasıtasıyla aslında hangi entity(tablo)'ya hangi id veri tipiyle 
//Sorguların hazırlanması gerektiğini Repository'e söylemiş oluyoruz
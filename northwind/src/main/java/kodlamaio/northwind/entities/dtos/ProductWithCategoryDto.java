package kodlamaio.northwind.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//Tek tablo içinde kullanılabilir. Sadece belirli alanları getirmek istediğimizde.
public class ProductWithCategoryDto {	
	private int id;
	private String productName;
	private String categoryName;
}
package mshaik.meru.online.pricing;

import java.util.*;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import mshaik.meru.online.pricing.config.CloudConfig;
import mshaik.meru.online.pricing.entities.ProductPrice;
import mshaik.meru.online.pricing.repository.ProductPriceRepository;
import mshaik.meru.online.pricing.exception.ResourceNotFoundException;

@RestController
public class ProductPriceController 
{
	@Autowired
	private ProductPriceRepository repo;
	
	@Autowired
	CloudConfig  cfg;
		
	//Retrieving Product Price
	@GetMapping("/meru/product/price/{prodid}")
	@HystrixCommand(fallbackMethod="retrieveDefaultPrice")
	public ProductPrice retrieveProductPrice(@PathVariable long prodid) 
	{			
		if ( cfg.getTaxadjpct() == null )
			cfg.setTaxadjpct("0");

		if ( cfg.getMaxtaxpct() == null )
			cfg.setMaxtaxpct("20");
			
		System.out.println("CloudConfig is Tax Adj=<" + cfg.getTaxadjpct() + ">" + ",Max=<" + cfg.getMaxtaxpct() + ">");
		double taxadjust = Double.parseDouble(cfg.getTaxadjpct());
		double maxtaxpct = Double.parseDouble(cfg.getMaxtaxpct());
		System.out.print("CloudConfig After integer conversion Tax=<" + taxadjust + ">" + ",Max=<" + maxtaxpct + ">");		

		Optional<ProductPrice> price =  repo.findById(prodid);
		if(!price.isPresent())
		{
			throw new ResourceNotFoundException("Product id-" + prodid);
		}
		
		//Tax Adjustment -- Business Logic implementation
		ProductPrice NewPrice =  price.get();
		
		//Calculating new Tax with adjustment
		double i = NewPrice.getTax() + taxadjust ;
		double newtax = i >  maxtaxpct ? maxtaxpct : i;
		NewPrice.setTax(newtax);		
		
		return NewPrice;
	}

	public ProductPrice retrieveDefaultPrice(long prodid) 
	{
		return new ProductPrice(prodid, 2999L, 8);
	}
	

	//Modifying Product Price
	@PostMapping("/meru/product/price/{prodid}")   
	public ProductPrice modifyProductPrice(@RequestBody ProductPrice chgprice) 
	{	
		System.out.print("CloudConfig Params=" + cfg.toString());
		Optional<ProductPrice> priceopt =  repo.findById(chgprice.getProdId());
		if(!priceopt.isPresent())
		{
			throw new ResourceNotFoundException("Product id-" + priceopt.get().getProdId());
		}
		
		//get the existing object and assign to new price
		//then modify stuff as we given
		ProductPrice newprice =  priceopt.get();
		newprice.setPrice(chgprice.getPrice());
		newprice.setTax(chgprice.getTax());

		return repo.save(newprice);
	}

	 //Add Product Price
	@PostMapping("/meru/product/price") 
	public ProductPrice addProductPrice(@Valid @RequestBody ProductPrice prodprice) 
	{		
		ProductPrice price =  repo.save(prodprice);
		return price;
	}
	
	 //Delete Product Price
	@DeleteMapping("/meru/product/price/{prodid}")
	public void deleteProductPrice(@PathVariable long prodid) 
	{		
		Optional<ProductPrice> price =  repo.findById(prodid);
		if(!price.isPresent())
		{
			throw new ResourceNotFoundException("Product id-" + prodid);
		}
		repo.deleteById(prodid);
	}
}

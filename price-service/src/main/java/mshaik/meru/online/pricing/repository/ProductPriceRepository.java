package mshaik.meru.online.pricing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import mshaik.meru.online.pricing.entities.ProductPrice;

public interface ProductPriceRepository extends JpaRepository<ProductPrice, Long>
{

}
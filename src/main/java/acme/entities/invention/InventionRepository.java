
package acme.entities.invention;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;

@Repository
public interface InventionRepository extends AbstractRepository {

	@Query("select i from Invention i where i.id = :id")
	Invention findInventionById(int id);

	@Query("select s from Invention s where s.ticker = :ticker")
	Invention findInventionByTicker(String ticker);

	@Query("select count(p) from Part p where p.invention.id = :id")
	Integer findNumberOfPartsOfAInventionByInventionId(Integer id);

	@Query("select count(p) from Part p where p.invention.id = :id  and p.cost.currency != 'EUR' ")
	Integer countPartOfAnInventionWithCurrencyNotInEurosByInventionId(Integer id);

	@Query("select sum(p.cost.amount) from Part p where p.invention.id = :id")
	Double sumCostOfThePartsOfAInventionByInventionId(Integer id);

}

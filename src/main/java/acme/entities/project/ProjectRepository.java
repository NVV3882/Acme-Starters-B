
package acme.entities.project;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.campaign.Campaign;
import acme.entities.invention.Invention;
import acme.entities.strategy.Strategy;

@Repository
public interface ProjectRepository extends AbstractRepository {

	@Query("select p from Project p where p.id = :id")
	Project findProjectById(int id);

	@Query("select i from Invention i where i.project.id = :id")
	List<Invention> findInventionsByProjectId(int id);

	@Query("select c from Campaign c where c.project.id = :id")
	List<Campaign> findCampaignsByProjectId(int id);

	@Query("select s from Strategy s where s.project.id = :id")
	List<Strategy> findStrategiesByProjectId(int id);

	@Query("select p from Project p where p.title = :title")
	Project findProjectByTitle(String title);
}

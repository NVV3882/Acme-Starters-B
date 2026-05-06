package acme.entities.project;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.campaign.Campaign;
import acme.entities.invention.Invention;
import acme.entities.strategy.Strategy;
import acme.entities.project.InvolvedIn;

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

	@Query("select case when (count(i) > 0) then true else false end from Invention i where i.project.id = :id")
	boolean existsInventionsByProjectId(int id);

	@Query("select case when (count(c) > 0) then true else false end from Campaign c where c.project.id = :id")
	boolean existsCampaignsByProjectId(int id);

	@Query("select case when (count(s) > 0) then true else false end from Strategy s where s.project.id = :id")
	boolean existsStrategiesByProjectId(int id);

	@Query("select count(i) from Invention i where i.project.id = :id and (i.startMoment < :kickOff or i.endMoment > :closeOut)")
	long countOutOfRangeInventions(int id, java.util.Date kickOff, java.util.Date closeOut);

	@Query("select count(c) from Campaign c where c.project.id = :id and (c.startMoment < :kickOff or c.endMoment > :closeOut)")
	long countOutOfRangeCampaigns(int id, java.util.Date kickOff, java.util.Date closeOut);

	@Query("select count(s) from Strategy s where s.project.id = :id and (s.startMoment < :kickOff or s.endMoment > :closeOut)")
	long countOutOfRangeStrategies(int id, java.util.Date kickOff, java.util.Date closeOut);

	// New queries: check if there exists any associated component with draftMode = true
	@Query("select case when (count(i) > 0) then true else false end from Invention i where i.project.id = :id and i.draftMode = true")
	boolean existsInventionsWithDraftModeTrueByProjectId(int id);

	@Query("select case when (count(c) > 0) then true else false end from Campaign c where c.project.id = :id and c.draftMode = true")
	boolean existsCampaignsWithDraftModeTrueByProjectId(int id);

	@Query("select case when (count(s) > 0) then true else false end from Strategy s where s.project.id = :id and s.draftMode = true")
	boolean existsStrategiesWithDraftModeTrueByProjectId(int id);

	@Query("select i from InvolvedIn i where i.project.id = :id")
	Collection<InvolvedIn> findMembersByProjectId(int id);


}

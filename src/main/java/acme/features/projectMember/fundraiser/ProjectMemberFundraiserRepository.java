
package acme.features.projectMember.fundraiser;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.project.Project;
import acme.entities.strategy.Strategy;
import acme.realms.Fundraiser;

@Repository
public interface ProjectMemberFundraiserRepository extends AbstractRepository {

	@Query("select s.fundraiser from Strategy s where s.id = :strategyId")
	Fundraiser findFundraiserByStrategyId(int strategyId);

	@Query("select count(s) > 0 from Strategy s where s.id = :strategyId and s.draftMode = false")
	Boolean strategyIsPublished(int strategyId);

	@Query("SELECT f FROM Fundraiser f " + "WHERE f.userAccount IN (" + "  SELECT pm.member.userAccount FROM InvolvedIn pm " + "  WHERE pm.project.id = :projectId" + ")")
	Collection<Fundraiser> listFundraisersByProjectId(int projectId);

	@Query("select p from Project p where p.id=:projectId")
	Project findProjectById(int projectId);

	@Query("select f from Fundraiser f where f.id=:fundraiserId")
	Fundraiser findFundraiserById(int fundraiserId);

	@Query("select s from Strategy s where s.id=:strategyId")
	Strategy findStrategyById(int strategyId);

	@Query("select count(ii) > 0 from InvolvedIn ii where ii.project.id = :projectId and ii.member.userAccount.username = :username")
	Boolean isProjectMember(int projectId, String username);
}


package acme.features.projectMember.spokesperson;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.campaign.Campaign;
import acme.entities.project.Project;
import acme.realms.Spokesperson;

@Repository
public interface ProjectMemberSpokespersonRepository extends AbstractRepository {

	@Query("select c.spokesperson from Campaign c where c.id = :campaignId")
	Spokesperson findSpokespersonByCampaignId(int campaignId);

	@Query("select count(c) > 0 from Campaign c where c.id = :campaignId and c.draftMode = false")
	Boolean campaignIsPublished(int campaignId);

	@Query("SELECT s FROM Spokesperson s " + "WHERE s.userAccount IN (" + "  SELECT pm.member.userAccount FROM InvolvedIn pm " + "  WHERE pm.project.id = :projectId" + ")")
	Collection<Spokesperson> listAllSpokespersonsByProjectId(int projectId);

	@Query("select p from Project p where p.id=:projectId")
	Project findProjectById(int projectId);

	@Query("select s from Spokesperson s where s.id=:id")
	Spokesperson findSpokespersonById(int id);

	@Query("select c from Campaign c where c.id=:campaignId")
	Campaign findCampaignById(int campaignId);

	/*
	 * @Query("select p from Project p where p.managerId=:managerId")
	 * Collection<Project> listProjectsByManagerId(int managerId);
	 * 
	 */

	@Query("select count(ii) > 0 from InvolvedIn ii where ii.project.id = :projectId and ii.member.userAccount.username = :username")
	Boolean isProjectMember(int projectId, String username);
}

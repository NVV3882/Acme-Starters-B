
package acme.features.projectMember.inventor;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.invention.Invention;
import acme.entities.project.Project;
import acme.realms.Inventor;

@Repository
public interface ProjectMemberInventorRepository extends AbstractRepository {

	@Query("select i.inventor from Invention i where i.id = :inventionId")
	Inventor showInventorByInventionId(int inventionId);

	@Query("select i from Invention	i where i.id=:id")
	Invention getInvention(int id);

	@Query("SELECT i FROM Inventor i " + "WHERE i.userAccount IN (" + "  SELECT pm.member.userAccount FROM InvolvedIn pm " + "  WHERE pm.project.id = :projectId" + ")")
	Collection<Inventor> listInventorsByProjectId(int projectId);

	@Query("select p from Project p where p.id=:projectId")
	Project findProjectById(int projectId);

	@Query("select i from Inventor i where i.id=:id")
	Inventor findInventorById(int id);

	@Query("select count(ii) > 0 from InvolvedIn ii where ii.project.id = :projectId and ii.member.userAccount.username = :username")
	Boolean isProjectMember(int projectId, String username);
}

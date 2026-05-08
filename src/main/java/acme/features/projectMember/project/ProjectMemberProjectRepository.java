
package acme.features.projectMember.project;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.project.Project;

@Repository
public interface ProjectMemberProjectRepository extends AbstractRepository {

	@Query("select p from Project p where p.manager.id = :managerId")
	Collection<Project> findProjectsByManagerId(int managerId);

	@Query("select p from Project p where p.id = :id")
	Project findProjectById(int id);

	@Query("select count(ii) > 0 from InvolvedIn ii where ii.project.id = :projectId and ii.member.userAccount.username = :username")
	Boolean isProjectMember(int projectId, String username);

	@Query("select ii.project from InvolvedIn ii where ii.member.userAccount.username = :username")
	Collection<Project> findProjectsByUsername(String username);

}

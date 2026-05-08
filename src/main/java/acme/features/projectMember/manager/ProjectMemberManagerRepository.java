
package acme.features.projectMember.manager;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.project.Project;
import acme.realms.Manager;

@Repository
public interface ProjectMemberManagerRepository extends AbstractRepository {

	@Query("SELECT m FROM Manager m " + "WHERE m.userAccount IN (" + "  SELECT ii.member.userAccount FROM InvolvedIn ii " + "  WHERE ii.project.id = :projectId" + ")")
	Collection<Manager> listManagersByProjectId(int projectId);

	@Query("select m from Manager m where m.id = :managerId")
	Manager showManager(Integer managerId);

	@Query("select p from Project p where p.id=:projectId")
	Project findProjectById(int projectId);

	// Quiero una query que me diga si el usuario logueado es miembro del proyecto. Para ello, tengo que comprobar si el username que tengo esta en la tabla de involveIn, concretamente member.userAccount.username  y el project es igual
	@Query("select count(ii) > 0 from InvolvedIn ii where ii.project.id = :projectId and ii.member.userAccount.username = :username")
	Boolean isProjectMember(int projectId, String username);
}

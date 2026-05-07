
package acme.features.any.manager;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.project.Project;
import acme.realms.Manager;

@Repository
public interface AnyManagerRepository extends AbstractRepository {

	@Query("SELECT m FROM Manager m " + "WHERE m.userAccount IN (" + "  SELECT ii.member.userAccount FROM InvolvedIn ii " + "  WHERE ii.project.id = :projectId" + ")")
	Collection<Manager> listManagersByProjectId(int projectId);

	@Query("select m from Manager m where m.id = :managerId")
	Manager showManager(Integer managerId);

	@Query("select p from Project p where p.id=:projectId")
	Project findProjectById(int projectId);

}

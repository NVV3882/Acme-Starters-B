
package acme.features.any.projectmember;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.realms.Fundraiser;
import acme.realms.Inventor;
import acme.realms.Manager;
import acme.realms.ProjectMember;
import acme.realms.Spokesperson;

@Repository
public interface AnyProjectMemberRepository extends AbstractRepository {

	@Query("select pm from ProjectMember pm where pm.id = :id")
	ProjectMember findProjectMemberById(int id);

	@Query("select m from Manager m where m.userAccount.id = :userAccountId")
	Manager findManagerByUserAccountId(int userAccountId);

	@Query("select s from Spokesperson s where s.userAccount.id = :userAccountId")
	Spokesperson findSpokespersonByUserAccountId(int userAccountId);

	@Query("select f from Fundraiser f where f.userAccount.id = :userAccountId")
	Fundraiser findFundraiserByUserAccountId(int userAccountId);

	@Query("select i from Inventor i where i.userAccount.id = :userAccountId")
	Inventor findInventorByUserAccountId(int userAccountId);

}

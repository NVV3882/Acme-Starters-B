
package acme.features.authenticated.manager;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.components.principals.UserAccount;
import acme.client.repositories.AbstractRepository;
import acme.realms.Manager;

@Repository
public interface AuthenticatedManagerRepository extends AbstractRepository {

	@Query("select u from UserAccount u where u.id=:userAccountId")
	UserAccount findUserAccountById(int userAccountId);

	@Query("select m from Manager m where m.userAccount.id =:userAccountId")
	Manager findManagerByUserAccountId(int userAccountId);
}

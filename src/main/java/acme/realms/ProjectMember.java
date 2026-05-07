
package acme.realms;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Transient;

import acme.client.components.basis.AbstractRole;
import acme.client.components.basis.AbstractSquad;
import acme.client.components.validation.Mandatory;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ProjectMember extends AbstractSquad {

	// Serialisation identifier -----------------------------------------------

	private static final long serialVersionUID = 1L;

	// AbstractSquad interface ------------------------------------------------


	@Mandatory
	// @Valid  // HINT: inherited from the ancestor.
	@Transient
	@Override
	public Set<Class<? extends AbstractRole>> getMembers() {
		Set<Class<? extends AbstractRole>> result;

		result = Set.of(Manager.class, Spokesperson.class, Fundraiser.class, Inventor.class);

		return result;
	}

}


package acme.features.authenticated.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.principals.Authenticated;
import acme.client.helpers.PrincipalHelper;
import acme.client.services.AbstractService;
import acme.realms.Manager;

@Service
public class AuthenticatedManagerUpdateService extends AbstractService<Authenticated, Manager> {

	@Autowired
	AuthenticatedManagerRepository	repositorio;

	Manager							manager;


	@Override
	public void load() {
		int userAccountId = super.getRequest().getPrincipal().getAccountId();
		this.manager = this.repositorio.findManagerByUserAccountId(userAccountId);

	}
	@Override
	public void authorise() {
		boolean status = this.getRequest().getPrincipal().hasRealmOfType(Manager.class);
		super.setAuthorised(status);
	}

	@Override
	public void bind() {
		super.bindObject(this.manager, "position", "skills", "executive");
	}
	@Override
	public void validate() {
		super.validateObject(this.manager);
	}
	@Override
	public void execute() {
		this.repositorio.save(this.manager);
	}
	@Override
	public void unbind() {
		super.unbindObject(this.manager, "position", "skills", "executive");
	}
	@Override
	public void onSuccess() {
		if (super.getRequest().getMethod().equals("POST"))
			PrincipalHelper.handleUpdate();
	}
}

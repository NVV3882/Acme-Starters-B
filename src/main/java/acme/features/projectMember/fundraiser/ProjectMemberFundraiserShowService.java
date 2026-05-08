
package acme.features.projectMember.fundraiser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.strategy.Strategy;
import acme.realms.Fundraiser;
import acme.realms.ProjectMember;

@Service
public class ProjectMemberFundraiserShowService extends AbstractService<ProjectMember, Fundraiser> {

	@Autowired
	ProjectMemberFundraiserRepository	repositorio;

	Fundraiser					recaudador;
	Strategy					strategy;
	int							strategyId;


	@Override
	public void load() {
		if (super.getRequest().hasData("id", Integer.class)) {
			Integer id = super.getRequest().getData("id", Integer.class);
			this.recaudador = this.repositorio.findFundraiserById(id);
		}
	}

	@Override
	public void authorise() {
		if (this.recaudador == null)
			super.setAuthorised(false);
		else
			super.setAuthorised(true);

	}

	@Override
	public void unbind() {
		super.unbindObject(this.recaudador, "bank", "statement", "agent");
	}
}

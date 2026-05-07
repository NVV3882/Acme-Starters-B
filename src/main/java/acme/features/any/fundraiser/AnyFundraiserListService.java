
package acme.features.any.fundraiser;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.principals.Any;
import acme.client.services.AbstractService;
import acme.entities.project.Project;
import acme.realms.Fundraiser;

@Service
public class AnyFundraiserListService extends AbstractService<Any, Fundraiser> {

	@Autowired
	AnyFundraiserRepository	repositorio;

	Collection<Fundraiser>	fundraisers;

	int						projectId;


	@Override
	public void load() {
		this.projectId = super.getRequest().getData("projectId", Integer.class);

		this.fundraisers = this.repositorio.listFundraisersByProjectId(this.projectId);
	}

	@Override
	public void authorise() {
		Project project = this.repositorio.findProjectById(this.projectId);
		if (project.getDraftMode() != false)
			super.setAuthorised(false);
		else
			super.setAuthorised(true);
	}

	@Override
	public void unbind() {
		super.unbindObjects(this.fundraisers, "bank", "statement", "agent");
	}

}

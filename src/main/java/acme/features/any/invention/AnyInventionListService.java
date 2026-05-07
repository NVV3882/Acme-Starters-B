
package acme.features.any.invention;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.principals.Any;
import acme.client.services.AbstractService;
import acme.entities.invention.Invention;

@Service
public class AnyInventionListService extends AbstractService<Any, Invention> {

	@Autowired
	AnyInventionRepository	repositorio;

	Collection<Invention>	inventos;
	int						projectId;


	@Override
	public void load() {
		if (super.getRequest().hasData("projectId", Integer.class)) {
			Integer projectId = super.getRequest().getData("projectId", Integer.class);
			this.inventos = this.repositorio.listAllInventionsByProjectId(projectId);
		} else
			this.inventos = this.repositorio.listAllInventions();
	}

	@Override
	public void authorise() {
		super.setAuthorised(true);
	}

	@Override
	public void unbind() {
		super.unbindObjects(this.inventos, "ticker", "name", "description", "startMoment", "endMoment", "moreInfo", "draftMode", "monthsActive", "cost");
	}

}

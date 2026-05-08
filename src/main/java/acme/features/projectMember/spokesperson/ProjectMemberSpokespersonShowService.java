
package acme.features.projectMember.spokesperson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.campaign.Campaign;
import acme.realms.ProjectMember;
import acme.realms.Spokesperson;

@Service
public class ProjectMemberSpokespersonShowService extends AbstractService<ProjectMember, Spokesperson> {

	@Autowired
	ProjectMemberSpokespersonRepository	repositorio;

	Spokesperson					spokesperson;
	Campaign						campaign;
	int								campaignId;


	@Override
	public void load() {
		if (super.getRequest().hasData("id", Integer.class)) {
			Integer id = super.getRequest().getData("id", Integer.class);
			this.spokesperson = this.repositorio.findSpokespersonById(id);
		}

	}

	@Override
	public void authorise() {
		if (super.getRequest().hasData("id", Integer.class))
			if (this.spokesperson == null)
				super.setAuthorised(false);
			else
				super.setAuthorised(true);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.spokesperson, "cv", "achievements", "licensed");
	}
}

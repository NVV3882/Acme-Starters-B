
package acme.features.projectMember.fundraiser;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.project.Project;
import acme.realms.Fundraiser;
import acme.realms.ProjectMember;

@Service
public class ProjectMemberFundraiserListService extends AbstractService<ProjectMember, Fundraiser> {

	@Autowired
	ProjectMemberFundraiserRepository	repositorio;

	Collection<Fundraiser>		fundraisers;

	int							projectId;


	@Override
	public void load() {
		this.projectId = super.getRequest().getData("projectId", Integer.class);
		this.fundraisers = this.repositorio.listFundraisersByProjectId(this.projectId);
	}

	@Override
	public void authorise() {
		Project project = this.repositorio.findProjectById(this.projectId);
		String username = super.getRequest().getPrincipal().getUsername();
		if (this.repositorio.isProjectMember(this.projectId, username) && project != null)
			super.setAuthorised(true);
		else
			super.setAuthorised(false);
	}

	@Override
	public void unbind() {
		super.unbindObjects(this.fundraisers, "bank", "statement", "agent");
	}

}

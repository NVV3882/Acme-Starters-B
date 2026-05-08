
package acme.features.projectMember.inventor;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.project.Project;
import acme.realms.Inventor;
import acme.realms.ProjectMember;

@Service
public class ProjectMemberInventorListService extends AbstractService<ProjectMember, Inventor> {

	@Autowired
	ProjectMemberInventorRepository	repositorio;

	Collection<Inventor>		inventors;

	int							projectId;


	@Override
	public void load() {
		this.projectId = super.getRequest().getData("projectId", Integer.class);

		this.inventors = this.repositorio.listInventorsByProjectId(this.projectId);
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
		super.unbindObjects(this.inventors, "bio", "keyWords", "licensed");
	}

}

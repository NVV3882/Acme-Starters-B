
package acme.features.projectMember.spokesperson;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.project.Project;
import acme.realms.ProjectMember;
import acme.realms.Spokesperson;

@Service
public class ProjectMemberSpokespersonListService extends AbstractService<ProjectMember, Spokesperson> {

	@Autowired
	ProjectMemberSpokespersonRepository	repositorio;

	Collection<Spokesperson>		spokespersons;

	int								projectId;


	@Override
	public void load() {
		this.projectId = super.getRequest().getData("projectId", Integer.class);
		this.spokespersons = this.repositorio.listAllSpokespersonsByProjectId(this.projectId);
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
		super.unbindObjects(this.spokespersons, "cv", "achievements", "licensed");
	}

}


package acme.features.projectMember.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.project.Project;
import acme.realms.Manager;
import acme.realms.ProjectMember;

@Service
public class ProjectMemberManagerShowService extends AbstractService<ProjectMember, Manager> {

	@Autowired
	ProjectMemberManagerRepository	repositorio;

	Manager							manager;

	int								projectId;


	@Override
	public void load() {
		this.projectId = super.getRequest().getData("projectId", int.class);
		this.manager = this.repositorio.listManagersByProjectId(this.projectId).stream().findFirst().orElse(null);
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
		super.unbindObject(this.manager, "position", "skills", "executive");
	}

}

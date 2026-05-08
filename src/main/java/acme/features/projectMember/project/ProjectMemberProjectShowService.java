
package acme.features.projectMember.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.project.Project;
import acme.realms.ProjectMember;

@Service
public class ProjectMemberProjectShowService extends AbstractService<ProjectMember, Project> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private ProjectMemberProjectRepository	repository;

	private Project							project;

	// AbstractService interface ----------------------------------------------


	@Override
	public void load() {
		int id = super.getRequest().getData("id", int.class);
		this.project = this.repository.findProjectById(id);
	}

	@Override
	public void authorise() {
		String username = super.getRequest().getPrincipal().getUsername();
		boolean status = this.project != null && this.repository.isProjectMember(this.project.getId(), username);
		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.project, "title", "keyWords", "description", "kickOffMoment", "closeOutMoment", "momentOfPublication", "draftMode");
	}

}

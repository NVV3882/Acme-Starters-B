
package acme.features.projectMember.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.project.Project;
import acme.realms.Manager;
import acme.realms.ProjectMember;

@Service
public class ProjectMemberProjectCreateService extends AbstractService<ProjectMember, Project> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private ProjectMemberProjectRepository	repository;

	private Project							project;

	// AbstractService interface ----------------------------------------------


	@Override
	public void load() {
		Manager manager;

		manager = (Manager) super.getRequest().getPrincipal().getActiveRealm();

		this.project = super.newObject(Project.class);
		this.project.setDraftMode(true);
		this.project.setManager(manager);
	}

	@Override
	public void authorise() {
		super.setAuthorised(true);
	}

	@Override
	public void bind() {
		super.bindObject(this.project, "title", "keyWords", "description", "kickOffMoment", "closeOutMoment");
	}

	@Override
	public void validate() {
		super.validateObject(this.project);
	}

	@Override
	public void execute() {
		this.repository.save(this.project);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.project, "title", "keyWords", "description", "kickOffMoment", "closeOutMoment", "momentOfPublication", "draftMode");
	}

}

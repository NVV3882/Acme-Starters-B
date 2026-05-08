
package acme.features.projectMember.project;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.project.Project;
import acme.realms.ProjectMember;

@Service
public class ProjectMemberProjectListService extends AbstractService<ProjectMember, Project> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private ProjectMemberProjectRepository	repository;

	private Collection<Project>			projects;

	// AbstractService interface ----------------------------------------------


	@Override
	public void authorise() {
		super.setAuthorised(true);
	}

	@Override
	public void load() {
		String username = super.getRequest().getPrincipal().getUsername();
		this.projects = this.repository.findProjectsByUsername(username);
	}

	@Override
	public void unbind() {
		super.unbindObjects(this.projects, "title", "keyWords", "description", "kickOffMoment", "closeOutMoment", "momentOfPublication", "draftMode");
	}

}

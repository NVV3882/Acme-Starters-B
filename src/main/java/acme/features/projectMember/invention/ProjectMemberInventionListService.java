
package acme.features.projectMember.invention;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.invention.Invention;
import acme.entities.project.Project;
import acme.entities.project.ProjectRepository;
import acme.realms.ProjectMember;

@Service
public class ProjectMemberInventionListService extends AbstractService<ProjectMember, Invention> {

	@Autowired
	private ProjectRepository		repository;

	private Collection<Invention>	inventions;


	@Override
	public void authorise() {
		int projectId = super.getRequest().getData("projectId", int.class);
		Project project = this.repository.findProjectById(projectId);

		String username = super.getRequest().getPrincipal().getUsername();
		if (this.repository.isProjectMember(projectId, username) && project != null)
			super.setAuthorised(true);
		else
			super.setAuthorised(false);
	}

	@Override
	public void load() {
		int projectId = super.getRequest().getData("projectId", int.class);
		this.inventions = this.repository.findInventionsByProjectId(projectId);
	}

	@Override
	public void unbind() {
		super.unbindObjects(this.inventions, "ticker", "name", "description", "startMoment", "endMoment", "moreInfo", "draftMode", "monthsActive", "cost");
	}

}

package acme.features.manager.invention;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.invention.Invention;
import acme.entities.project.Project;
import acme.entities.project.ProjectRepository;
import acme.realms.Manager;

@Service
public class ManagerInventionListService extends AbstractService<Manager, Invention> {

	@Autowired
	private ProjectRepository	repository;

	private Collection<Invention>	inventions;

	@Override
	public void authorise() {
		int projectId = super.getRequest().getData("projectId", int.class);
		Project project = this.repository.findProjectById(projectId);
		int managerId = super.getRequest().getPrincipal().getActiveRealm().getId();
		boolean authorised = project != null && project.getManager() != null && project.getManager().getId() == managerId;
		super.setAuthorised(authorised);
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

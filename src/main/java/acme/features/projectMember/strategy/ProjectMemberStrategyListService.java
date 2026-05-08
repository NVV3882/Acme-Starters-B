
package acme.features.projectMember.strategy;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.project.Project;
import acme.entities.project.ProjectRepository;
import acme.entities.strategy.Strategy;
import acme.realms.ProjectMember;

@Service
public class ProjectMemberStrategyListService extends AbstractService<ProjectMember, Strategy> {

	@Autowired
	private ProjectRepository		repository;

	private Collection<Strategy>	strategies;


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
		this.strategies = this.repository.findStrategiesByProjectId(projectId);
	}

	@Override
	public void unbind() {
		super.unbindObjects(this.strategies, "ticker", "name", "description", "startMoment", "endMoment", "moreInfo", "draftMode", "monthsActive", "expectedPercentage");
	}

}

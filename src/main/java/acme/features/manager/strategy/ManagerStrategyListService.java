package acme.features.manager.strategy;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.project.Project;
import acme.entities.project.ProjectRepository;
import acme.entities.strategy.Strategy;
import acme.realms.Manager;

@Service
public class ManagerStrategyListService extends AbstractService<Manager, Strategy> {

	@Autowired
	private ProjectRepository	repository;

	private Collection<Strategy>	strategies;

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
		this.strategies = this.repository.findStrategiesByProjectId(projectId);
	}

	@Override
	public void unbind() {
		super.unbindObjects(this.strategies, "ticker", "name", "description", "startMoment", "endMoment", "moreInfo", "draftMode", "monthsActive", "expectedPercentage");
	}

}
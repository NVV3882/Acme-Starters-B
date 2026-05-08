
package acme.features.projectMember.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.strategy.Strategy;
import acme.entities.strategy.StrategyRepository;
import acme.realms.ProjectMember;

@Service
public class ProjectMemberStrategyShowService extends AbstractService<ProjectMember, Strategy> {

	@Autowired
	private StrategyRepository	repository;

	private Strategy			strategy;


	@Override
	public void load() {
		int id = super.getRequest().getData("id", int.class);
		this.strategy = this.repository.findStrategyById(id);
	}

	@Override
	public void authorise() {
		boolean authorised = this.strategy != null && this.strategy.getProject() != null && this.strategy.getProject().getManager() != null;
		super.setAuthorised(authorised);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.strategy, "ticker", "name", "description", "startMoment", "endMoment", "moreInfo", "draftMode", "monthsActive", "expectedPercentage");
	}

}

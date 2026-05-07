package acme.features.manager.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.strategy.Strategy;
import acme.entities.strategy.StrategyRepository;
import acme.realms.Manager;

@Service
public class ManagerStrategyShowService extends AbstractService<Manager, Strategy> {

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
		boolean authorised = this.strategy != null && this.strategy.getProject() != null
			&& this.strategy.getProject().getManager() != null
			&& this.strategy.getProject().getManager().getId() == super.getRequest().getPrincipal().getActiveRealm().getId();
		super.setAuthorised(authorised);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.strategy, "ticker", "name", "description", "startMoment", "endMoment", "moreInfo", "draftMode", "monthsActive", "expectedPercentage");
	}

}
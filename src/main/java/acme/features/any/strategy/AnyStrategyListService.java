
package acme.features.any.strategy;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.principals.Any;
import acme.client.services.AbstractService;
import acme.entities.strategy.Strategy;

@Service
public class AnyStrategyListService extends AbstractService<Any, Strategy> {

	@Autowired
	private AnyStrategyRepository	repository;

	private Collection<Strategy>	strategy;

	int								strategyId;


	@Override
	public void load() {
		if (super.getRequest().hasData("projectId", Integer.class)) {
			Integer projectId = super.getRequest().getData("projectId", Integer.class);
			this.strategy = this.repository.listAllStrategiesByProjectId(projectId);
		} else
			this.strategy = this.repository.listAllStrategies();
	}

	@Override
	public void authorise() {
		super.setAuthorised(true);
	}

	@Override
	public void unbind() {
		super.unbindObjects(this.strategy, "ticker", "name", "description", "startMoment", "endMoment", "moreInfo", "draftMode", "monthsActive", "expectedPercentage");
	}

}

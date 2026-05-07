
package acme.features.any.fundraiser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.principals.Any;
import acme.client.services.AbstractService;
import acme.entities.strategy.Strategy;
import acme.realms.Fundraiser;

@Service
public class AnyFundraiserShowService extends AbstractService<Any, Fundraiser> {

	@Autowired
	AnyFundraiserRepository	repositorio;

	Fundraiser				recaudador;
	Strategy				strategy;
	int						strategyId;


	@Override
	public void load() {
		if (super.getRequest().hasData("id", Integer.class)) {
			Integer id = super.getRequest().getData("id", Integer.class);
			this.recaudador = this.repositorio.findFundraiserById(id);
		} else {
			this.strategyId = super.getRequest().getData("strategyId", int.class);
			this.recaudador = this.repositorio.findFundraiserByStrategyId(this.strategyId);
			this.strategy = this.repositorio.findStrategyById(this.strategyId);
		}
	}

	@Override
	public void authorise() {
		if (super.getRequest().hasData("id", Integer.class))
			if (this.recaudador == null)
				super.setAuthorised(false);
			else
				super.setAuthorised(true);		
		else
			if (this.strategy == null)
				super.setAuthorised(false);
			
			else if (this.recaudador.getUserAccount().getUsername().equals(super.getRequest().getPrincipal().getUsername()) || this.strategy.getDraftMode() == false)
				super.setAuthorised(true);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.recaudador, "bank", "statement", "agent");
	}
}

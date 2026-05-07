
package acme.features.any.spokesperson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.principals.Any;
import acme.client.services.AbstractService;
import acme.entities.campaign.Campaign;
import acme.realms.Spokesperson;

@Service
public class AnySpokespersonShowService extends AbstractService<Any, Spokesperson> {

	@Autowired
	AnySpokespersonRepository	repositorio;

	Spokesperson				spokesperson;
	Campaign				campaign;
	int campaignId;


	@Override
	public void load() {
		if(super.getRequest().hasData("id", Integer.class)) {
			Integer id = super.getRequest().getData("id", Integer.class);
			this.spokesperson = this.repositorio.findSpokespersonById(id);
		}
		else {
			int campaignId = super.getRequest().getData("campaignId", int.class);
			this.spokesperson = this.repositorio.findSpokespersonByCampaignId(campaignId);
			this.campaign = this.repositorio.findCampaignById(campaignId);
		}
	}

	@Override
	public void authorise() {
		if(super.getRequest().hasData("id", Integer.class)){
			if (this.spokesperson == null)
				super.setAuthorised(false);
			else 
				super.setAuthorised(true);
		// hola
		}else{
			if (this.campaign == null)
				super.setAuthorised(false);
			else if (this.spokesperson.getUserAccount().getUsername().equals(super.getRequest().getPrincipal().getUsername()) || this.campaign.getDraftMode() == false)
				super.setAuthorised(true);
		}
	}

	@Override
	public void unbind() {
		super.unbindObject(this.spokesperson, "cv", "achievements", "licensed");
	}
}

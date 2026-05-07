package acme.features.manager.campaign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.campaign.Campaign;
import acme.entities.campaign.CampaignRepository;
import acme.realms.Manager;

@Service
public class ManagerCampaignShowService extends AbstractService<Manager, Campaign> {

	@Autowired
	private CampaignRepository	repository;

	private Campaign			campaign;

	@Override
	public void load() {
		int id = super.getRequest().getData("id", int.class);
		this.campaign = this.repository.findCampaignById(id);
	}

	@Override
	public void authorise() {
		boolean authorised = this.campaign != null && this.campaign.getProject() != null
			&& this.campaign.getProject().getManager() != null
			&& this.campaign.getProject().getManager().getId() == super.getRequest().getPrincipal().getActiveRealm().getId();
		super.setAuthorised(authorised);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.campaign, "ticker", "name", "description", "startMoment", "endMoment", "moreInfo", "draftMode", "monthsActive", "effort");
	}

}
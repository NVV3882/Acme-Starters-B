
package acme.features.projectMember.campaign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.campaign.Campaign;
import acme.entities.campaign.CampaignRepository;
import acme.realms.ProjectMember;

@Service
public class ProjectMemberCampaignShowService extends AbstractService<ProjectMember, Campaign> {

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

		boolean authorised = this.campaign != null && this.campaign.getProject() != null && this.campaign.getProject().getManager() != null;
		super.setAuthorised(authorised);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.campaign, "ticker", "name", "description", "startMoment", "endMoment", "moreInfo", "draftMode", "monthsActive", "effort");
	}

}

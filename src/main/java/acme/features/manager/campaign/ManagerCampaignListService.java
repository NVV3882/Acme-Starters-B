package acme.features.manager.campaign;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.campaign.Campaign;
import acme.entities.project.Project;
import acme.entities.project.ProjectRepository;
import acme.realms.Manager;

@Service
public class ManagerCampaignListService extends AbstractService<Manager, Campaign> {

	@Autowired
	private ProjectRepository	repository;

	private Collection<Campaign>	campaigns;

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
		this.campaigns = this.repository.findCampaignsByProjectId(projectId);
	}

	@Override
	public void unbind() {
		super.unbindObjects(this.campaigns, "ticker", "name", "description", "startMoment", "endMoment", "moreInfo", "draftMode", "monthsActive", "effort");
	}

}

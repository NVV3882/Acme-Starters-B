
package acme.features.projectMember.invention;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.invention.Invention;
import acme.entities.invention.InventionRepository;
import acme.realms.ProjectMember;

@Service
public class ProjectMemberInventionShowService extends AbstractService<ProjectMember, Invention> {

	@Autowired
	private InventionRepository	repository;

	private Invention			invention;


	@Override
	public void load() {
		int id = super.getRequest().getData("id", int.class);
		this.invention = this.repository.findInventionById(id);
	}

	@Override
	public void authorise() {
		boolean authorised = this.invention != null && this.invention.getProject() != null && this.invention.getProject().getManager() != null;
		super.setAuthorised(authorised);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.invention, "ticker", "name", "description", "startMoment", "endMoment", "moreInfo", "draftMode", "monthsActive", "cost");
	}

}

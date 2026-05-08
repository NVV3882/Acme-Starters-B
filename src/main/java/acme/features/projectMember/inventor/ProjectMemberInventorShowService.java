
package acme.features.projectMember.inventor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.invention.Invention;
import acme.realms.Inventor;
import acme.realms.ProjectMember;

@Service
public class ProjectMemberInventorShowService extends AbstractService<ProjectMember, Inventor> {

	@Autowired
	ProjectMemberInventorRepository	repositorio;

	Inventor					inventor;

	Invention					invento;
	int							inventorId;


	@Override
	public void load() {
		Integer inventorId = super.getRequest().getData("id", Integer.class);
		this.inventor = this.repositorio.findInventorById(inventorId);

	}
	@Override
	public void authorise() {

		if (this.inventor == null)
			super.setAuthorised(false);
		else
			super.setAuthorised(true);

	}

	@Override
	public void unbind() {
		super.unbindObject(this.inventor, "bio", "keyWords", "licensed");

	}

}

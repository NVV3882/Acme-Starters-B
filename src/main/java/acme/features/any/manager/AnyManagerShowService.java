
package acme.features.any.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.principals.Any;
import acme.client.services.AbstractService;
import acme.entities.project.Project;
import acme.realms.Manager;

@Service
public class AnyManagerShowService extends AbstractService<Any, Manager> {

	@Autowired
	AnyManagerRepository	repositorio;

	Manager					manager;

	int						projectId;


	@Override
	public void load() {
		this.projectId = super.getRequest().getData("projectId", int.class);
		this.manager = this.repositorio.listManagersByProjectId(this.projectId).stream().findFirst().orElse(null);
	}

	@Override
	public void authorise() {
		Project project = this.repositorio.findProjectById(this.projectId);
		if (project.getDraftMode() != false)
			super.setAuthorised(false);
		else
			super.setAuthorised(true);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.manager, "position", "skills", "executive");
	}

}

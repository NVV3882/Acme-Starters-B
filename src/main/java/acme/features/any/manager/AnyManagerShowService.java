package acme.features.any.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.principals.Any;
import acme.client.services.AbstractService;
import acme.entities.project.Project;
import acme.entities.project.ProjectRepository;
import acme.realms.Manager;

@Service
public class AnyManagerShowService extends AbstractService<Any, Manager> {

	@Autowired
	private ProjectRepository	repository;

	private Manager				manager;

	@Override
	public void load() {
		int projectId = super.getRequest().getData("projectId", int.class);
		Project project = this.repository.findProjectById(projectId);
		this.manager = project != null ? project.getManager() : null;
	}

	@Override
	public void authorise() {
		super.setAuthorised(this.manager != null);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.manager, "position", "skills", "executive");
	}

}

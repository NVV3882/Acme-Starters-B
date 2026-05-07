package acme.features.any.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.principals.Any;
import acme.client.services.AbstractService;
import acme.entities.project.Project;

@Service
public class AnyProjectShowService extends AbstractService<Any, Project> {

	@Autowired
	AnyProjectRepository	repositorio;

	Project					project;


	@Override
	public void load() {
		int id;
		id = super.getRequest().getData("id", int.class);
		this.project = this.repositorio.showProject(id);
	}

	@Override
	public void authorise() {
		// Puedo ver un proyecto si está publicado (o si fuera un usuario autenticado y fuera el manager, pero como es Any, solo publicados)
		if (this.project == null)
			super.setAuthorised(false);
		else if (this.project.getDraftMode().equals(false))
			super.setAuthorised(true);
		else
			super.setAuthorised(false);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.project, "title", "keyWords", "description", "kickOffMoment", "closeOutMoment", "momentOfPublication", "draftMode", "effort");
	}

}

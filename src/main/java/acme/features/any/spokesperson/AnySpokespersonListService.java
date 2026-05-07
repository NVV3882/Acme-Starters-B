package acme.features.any.spokesperson;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.principals.Any;
import acme.client.services.AbstractService;
import acme.entities.project.Project;
import acme.realms.Spokesperson;

@Service
public class AnySpokespersonListService extends AbstractService<Any, Spokesperson> {

	@Autowired
	AnySpokespersonRepository	repositorio;

	Collection<Spokesperson>	spokespersons;

    int projectId;


	@Override
	public void load() {
        this.projectId = super.getRequest().getData("projectId", Integer.class);
		this.spokespersons = this.repositorio.listAllSpokespersonsByProjectId(this.projectId);
	}

	@Override
	public void authorise() {
        Project project = this.repositorio.findProjectById(this.projectId);
        if(project.getDraftMode() != false){
            super.setAuthorised(false);

        }else{
		    super.setAuthorised(true);
        }
	}

	@Override
	public void unbind() {
		super.unbindObjects(this.spokespersons, "cv", "achievements", "licensed");
	}

}
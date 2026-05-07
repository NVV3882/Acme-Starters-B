package acme.features.any.inventor;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.principals.Any;
import acme.client.services.AbstractService;
import acme.entities.project.Project;
import acme.realms.Inventor;

@Service
public class AnyInventorListService extends AbstractService<Any, Inventor> {

	@Autowired
	AnyInventorRepository	repositorio;

	Collection<Inventor>	inventors;

    int projectId;



	@Override
	public void load() {
        this.projectId = super.getRequest().getData("projectId", Integer.class);

		this.inventors = this.repositorio.listInventorsByProjectId(this.projectId);
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
		super.unbindObjects(this.inventors, "bio", "keyWords", "licensed");
	}

}
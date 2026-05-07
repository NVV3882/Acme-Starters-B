
package acme.features.any.inventor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.principals.Any;
import acme.client.services.AbstractService;
import acme.entities.invention.Invention;
import acme.realms.Inventor;

@Service
public class AnyInventorShowService extends AbstractService<Any, Inventor> {

	@Autowired
	AnyInventorRepository	repositorio;

	Inventor				inventor;

	Invention				invento;
	int inventorId;


	@Override
	public void load() {

		if (super.getRequest().hasData("id", Integer.class)) {
			Integer inventorId = super.getRequest().getData("id", Integer.class);
			this.inventor = this.repositorio.findInventorById(inventorId);
		} else{
			int inventionId = super.getRequest().getData("inventionId", int.class);
			this.inventor = this.repositorio.showInventorByInventionId(inventionId);
			this.invento = this.repositorio.getInvention(inventionId);
		}

		
	}
	@Override
	public void authorise() {
		// puedo ver un inventor a partir del id de un invento, por lo que si dicho invento está publicado o si soy yo el inventor, puedo verlo
		if(super.getRequest().hasData("id", Integer.class)){
			if (this.inventor == null)
				super.setAuthorised(false);
			else 
				super.setAuthorised(true);
		}
		else {
			if (this.invento == null)
				super.setAuthorised(false);
			
			else if (this.inventor.getUserAccount().getUsername().equals(super.getRequest().getPrincipal().getUsername()) || this.invento.getDraftMode() == false)
				super.setAuthorised(true);
			
		}
	}

	@Override
	public void unbind() {
		super.unbindObject(this.inventor, "bio", "keyWords", "licensed");

	}

}

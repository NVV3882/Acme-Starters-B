
package acme.features.projectMember.manager;

import javax.annotation.PostConstruct;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;

import acme.client.controllers.AbstractController;
import acme.realms.Manager;
import acme.realms.ProjectMember;

@Controller
public class ProjectMemberManagerController extends AbstractController<ProjectMember, Manager> {

	@PostConstruct
	protected void initialise() {
		super.setMediaType(MediaType.TEXT_HTML);

		super.addBasicCommand("show", ProjectMemberManagerShowService.class);
	}

}

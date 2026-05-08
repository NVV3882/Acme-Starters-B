
package acme.features.projectMember.inventor;

import javax.annotation.PostConstruct;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;

import acme.client.controllers.AbstractController;
import acme.realms.Inventor;
import acme.realms.ProjectMember;

@Controller
public class ProjectMemberInventorController extends AbstractController<ProjectMember, Inventor> {

	@PostConstruct
	protected void initialise() {
		super.setMediaType(MediaType.TEXT_HTML);

		super.addBasicCommand("list", ProjectMemberInventorListService.class);
		super.addBasicCommand("show", ProjectMemberInventorShowService.class);
	}
}

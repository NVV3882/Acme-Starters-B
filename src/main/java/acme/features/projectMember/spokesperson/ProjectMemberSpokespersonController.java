
package acme.features.projectMember.spokesperson;

import javax.annotation.PostConstruct;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;

import acme.client.controllers.AbstractController;
import acme.realms.ProjectMember;
import acme.realms.Spokesperson;

@Controller
public class ProjectMemberSpokespersonController extends AbstractController<ProjectMember, Spokesperson> {

	@PostConstruct
	protected void initialise() {
		super.setMediaType(MediaType.TEXT_HTML);

		super.addBasicCommand("list", ProjectMemberSpokespersonListService.class);
		super.addBasicCommand("show", ProjectMemberSpokespersonShowService.class);
	}
}

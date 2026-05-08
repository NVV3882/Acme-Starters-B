
package acme.features.projectMember.fundraiser;

import javax.annotation.PostConstruct;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;

import acme.client.controllers.AbstractController;
import acme.realms.Fundraiser;
import acme.realms.ProjectMember;

@Controller
public class ProjectMemberFundraiserController extends AbstractController<ProjectMember, Fundraiser> {

	@PostConstruct
	protected void initialise() {
		super.setMediaType(MediaType.TEXT_HTML);

		super.addBasicCommand("list", ProjectMemberFundraiserListService.class);
		super.addBasicCommand("show", ProjectMemberFundraiserShowService.class);
	}

}

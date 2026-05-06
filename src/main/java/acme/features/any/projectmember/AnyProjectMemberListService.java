package acme.features.any.projectmember;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.principals.Any;
import acme.client.services.AbstractService;
import acme.entities.project.InvolvedIn;
import acme.entities.project.ProjectRepository;
import acme.realms.ProjectMember;

@Service
public class AnyProjectMemberListService extends AbstractService<Any, ProjectMember> {

	@Autowired
	private ProjectRepository	repository;

	private Collection<ProjectMember>	members;

	@Override
	public void authorise() {
		super.setAuthorised(true);
	}

	@Override
	public void load() {
		int projectId = super.getRequest().getData("projectId", int.class);
		Collection<InvolvedIn> involvedIn = this.repository.findMembersByProjectId(projectId);
		this.members = involvedIn.stream().map(InvolvedIn::getMember).toList();
	}

	@Override
	public void unbind() {
		super.unbindObjects(this.members, "userAccount.username");
	}

}

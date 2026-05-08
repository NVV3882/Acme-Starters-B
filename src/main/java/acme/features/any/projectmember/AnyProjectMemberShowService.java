
package acme.features.any.projectmember;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.principals.Any;
import acme.client.services.AbstractService;
import acme.realms.Fundraiser;
import acme.realms.Inventor;
import acme.realms.Manager;
import acme.realms.ProjectMember;
import acme.realms.Spokesperson;

@Service
public class AnyProjectMemberShowService extends AbstractService<Any, ProjectMember> {

	@Autowired
	private AnyProjectMemberRepository	repository;

	private ProjectMember				member;
	private Manager						manager;
	private Spokesperson				spokesperson;
	private Fundraiser					fundraiser;
	private Inventor					inventor;


	@Override
	public void load() {
		int memberId = super.getRequest().getData("id", int.class);
		this.member = this.repository.findProjectMemberById(memberId);
		if (this.member != null) {
			int userAccountId = this.member.getUserAccount().getId();
			this.manager = this.repository.findManagerByUserAccountId(userAccountId);
			this.spokesperson = this.repository.findSpokespersonByUserAccountId(userAccountId);
			this.fundraiser = this.repository.findFundraiserByUserAccountId(userAccountId);
			this.inventor = this.repository.findInventorByUserAccountId(userAccountId);
		}
	}

	@Override
	public void authorise() {
		super.setAuthorised(this.member != null);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.member, "userAccount.username");
		if (this.manager != null)
			super.unbindGlobal("manager", this.manager);
		if (this.spokesperson != null)
			super.unbindGlobal("spokesperson", this.spokesperson);
		if (this.fundraiser != null)
			super.unbindGlobal("fundraiser", this.fundraiser);
		if (this.inventor != null)
			super.unbindGlobal("inventor", this.inventor);
	}

}

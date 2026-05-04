package acme.constraints;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import acme.client.components.validation.AbstractValidator;
import acme.client.components.validation.Validator;
import acme.client.helpers.MomentHelper;
import acme.entities.project.Project;
import acme.entities.project.ProjectRepository;

@Validator
public class ProjectValidator extends AbstractValidator<ValidProject, Project> {

	@Autowired
	private ProjectRepository repositorio;


	@Override
	protected void initialise(final ValidProject annotation) {
		assert annotation != null;
	}

	@Override
	public boolean isValid(final Project project, final javax.validation.ConstraintValidatorContext context) {
		assert context != null;
		boolean result;
		if (project == null)
			result = true;
		else {

			if (project.getDraftMode().equals(false)) {
				boolean intervaloCorrectoTiempo;
				Date fechaInicio = project.getKickOffMoment();
				Date fechaFinal = project.getCloseOutMoment();

				intervaloCorrectoTiempo = fechaInicio != null && fechaFinal != null && MomentHelper.isAfter(fechaFinal, fechaInicio);

				super.state(context, intervaloCorrectoTiempo, "*", "acme.validation.project.incorrect-dates-intervale.message");
			}
			{
				boolean projectInventionsCorrectos;
				if (project.getId() != 0) {
					projectInventionsCorrectos = this.repositorio.existsInventionsByProjectId(project.getId()) || project.getDraftMode();
					super.state(context, projectInventionsCorrectos, "*", "acme.validation.project.incorrect-project-inventions.message");
				}
			}
			{
				boolean projectComponentsMomentsCorrectos;

				if (project.getDraftMode().equals(false)) {
					Date kickOffMoment = project.getKickOffMoment();
					Date closeOutMoment = project.getCloseOutMoment();

					long outOfRangeInventions = this.repositorio.countOutOfRangeInventions(project.getId(), kickOffMoment, closeOutMoment);
					long outOfRangeCampaigns = this.repositorio.countOutOfRangeCampaigns(project.getId(), kickOffMoment, closeOutMoment);
					long outOfRangeStrategies = this.repositorio.countOutOfRangeStrategies(project.getId(), kickOffMoment, closeOutMoment);

					projectComponentsMomentsCorrectos = outOfRangeInventions + outOfRangeCampaigns + outOfRangeStrategies == 0 || project.getDraftMode();
					super.state(context, projectComponentsMomentsCorrectos, "*", "acme.validation.project.incorrect-project-components-moments.message");
				}
			}
			{
				boolean momentOfPublicationCorrecto;
				if (project.getDraftMode().equals(true)) {
					momentOfPublicationCorrecto = project.getMomentOfPublication() == null;
					super.state(context, momentOfPublicationCorrecto, "*", "acme.validation.project.incorrect-moment-of-publication.message");
				}
			}
			{
				//project.draftMode = false -> todos los draftMode de sus componentes asociados = false
				boolean projectComponentsDraftModeCorrectos;
				if (project.getDraftMode().equals(false)) {
					// Use repository boolean queries to avoid loading entities
					boolean hasInventionsWithDraftTrue = this.repositorio.existsInventionsWithDraftModeTrueByProjectId(project.getId());
					boolean hasCampaignsWithDraftTrue = this.repositorio.existsCampaignsWithDraftModeTrueByProjectId(project.getId());
					boolean hasStrategiesWithDraftTrue = this.repositorio.existsStrategiesWithDraftModeTrueByProjectId(project.getId());

					projectComponentsDraftModeCorrectos = !hasInventionsWithDraftTrue && !hasCampaignsWithDraftTrue && !hasStrategiesWithDraftTrue;
					super.state(context, projectComponentsDraftModeCorrectos, "*", "acme.validation.project.incorrect-project-components-draft-mode.message");
				}

			}
			{
				// New validation: if project is published (draftMode == false), kickoff and closeout must be after momentOfPublication (if present)
				boolean publicationMomentsCorrectos;
				if (project.getDraftMode().equals(false) && project.getMomentOfPublication() != null) {
					Date momentOfPublication = project.getMomentOfPublication();
					Date kickOffMoment = project.getKickOffMoment();
					Date closeOutMoment = project.getCloseOutMoment();

					// Both moments must be non-null and strictly after momentOfPublication
					publicationMomentsCorrectos = kickOffMoment != null && closeOutMoment != null
						&& MomentHelper.isAfter(kickOffMoment, momentOfPublication)
						&& MomentHelper.isAfter(closeOutMoment, momentOfPublication);
					super.state(context, publicationMomentsCorrectos, "*", "acme.validation.project.components-after-publication.message");
				}
			}
			result = !super.hasErrors(context);
		}
		return result;
	}

}
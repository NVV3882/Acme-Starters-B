
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
			{
				boolean tituloUnico;
				Project projectExistente;

				projectExistente = this.repositorio.findProjectByTitle(project.getTitle());
				tituloUnico = projectExistente == null || projectExistente.equals(project);

				super.state(context, tituloUnico, "title", "acme.validation.project.duplicated-title.message");
			}

			if (project.getDraftMode().equals(false)) {
				boolean intervaloCorrectoTiempo;
				Date fechaInicio = project.getKickOffMoment();
				Date fechaFinal = project.getCloseOutMoment();

				intervaloCorrectoTiempo = fechaInicio != null && fechaFinal != null && MomentHelper.isAfter(fechaFinal, fechaInicio);

				super.state(context, intervaloCorrectoTiempo, "*", "acme.validation.project.incorrect-dates-intervale.message");
			}
			{
				//draftMode cant be true if there isnt at least one invention associated to the project
				boolean projectInventionsCorrectos;
				if (project.getId() != 0) {
					projectInventionsCorrectos = !this.repositorio.findInventionsByProjectId(project.getId()).isEmpty() || project.getDraftMode();
					super.state(context, projectInventionsCorrectos, "*", "acme.validation.project.correct-project-inventions.message");
				} else
					super.state(context, true, "*", "acme.validation.project.incorrect-project-inventions.message");
			}
			{
				boolean projectInventionsMomentsCorrectos;
				boolean projectCampaignsMomentsCorrectos;
				boolean projectStrategiesMomentsCorrectos;

				if (project.getDraftMode().equals(false)) {
					Date kickOffMoment = project.getKickOffMoment();
					Date closeOutMoment = project.getCloseOutMoment();

					projectInventionsMomentsCorrectos = this.repositorio.findInventionsByProjectId(project.getId()).stream()
						.allMatch(i -> MomentHelper.isInRange(i.getStartMoment(), kickOffMoment, closeOutMoment) && MomentHelper.isInRange(i.getEndMoment(), kickOffMoment, closeOutMoment));

					projectCampaignsMomentsCorrectos = this.repositorio.findCampaignsByProjectId(project.getId()).stream()
						.allMatch(c -> MomentHelper.isInRange(c.getStartMoment(), kickOffMoment, closeOutMoment) && MomentHelper.isInRange(c.getEndMoment(), kickOffMoment, closeOutMoment));

					projectStrategiesMomentsCorrectos = this.repositorio.findStrategiesByProjectId(project.getId()).stream()
						.allMatch(s -> MomentHelper.isInRange(s.getStartMoment(), kickOffMoment, closeOutMoment) && MomentHelper.isInRange(s.getEndMoment(), kickOffMoment, closeOutMoment));

					boolean projectComponentsMomentsCorrectos = projectInventionsMomentsCorrectos && projectCampaignsMomentsCorrectos && projectStrategiesMomentsCorrectos;
					super.state(context, projectComponentsMomentsCorrectos, "*", "acme.validation.project.correct-project-components-moments.message");
				} else
					super.state(context, true, "*", "acme.validation.project.incorrect-project-components-moments.message");

			}
			result = !super.hasErrors(context);
		}
		return result;
	}

}

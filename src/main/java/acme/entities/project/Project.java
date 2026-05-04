
package acme.entities.project;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.Valid;

import acme.client.components.basis.AbstractEntity;
import acme.client.components.validation.Mandatory;
import acme.client.components.validation.Optional;
import acme.client.components.validation.ValidMoment;
import acme.constraints.ValidHeader;
import acme.constraints.ValidText;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
//@ValidProject :TODO
public class Project extends AbstractEntity {

	// Serialisation version --------------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@Mandatory
	@ValidHeader
	@Column
	private String				title;

	@Mandatory
	@ValidHeader
	@Column
	private String				keyWords;

	@Mandatory
	@ValidText
	@Column
	private String				description;

	@Mandatory
	@ValidMoment
	@Temporal(TemporalType.TIMESTAMP)
	private Date				kickOffMoment;

	@Mandatory
	@ValidMoment
	@Temporal(TemporalType.TIMESTAMP)
	private Date				closeOutMoment;

	@Optional
	@ValidMoment
	@Temporal(TemporalType.TIMESTAMP)
	private Date				momentOfPublication;

	@Mandatory
	@Valid
	@Column
	private Boolean				draftMode;

	// Derived attributes -----------------------------------------------------


	@Transient
	@Mandatory
	@Valid
	public Double effort() {
		return 0.;
	}

	// Relationships ----------------------------------------------------------

	/*
	 * @Mandatory
	 * 
	 * @Valid
	 * 
	 * @ManyToOne(optional = false)
	 * private Manager manager;
	 */

}

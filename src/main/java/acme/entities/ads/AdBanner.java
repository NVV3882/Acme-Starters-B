
package acme.entities.ads;

import javax.persistence.Column;

import acme.client.components.basis.AbstractEntity;
import acme.client.components.validation.Mandatory;
import acme.client.components.validation.ValidUrl;
import acme.constraints.ValidHeader;

public class AdBanner extends AbstractEntity {

	// Serialisation version --------------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@Mandatory
	@ValidHeader
	@Column
	private String				slogan;

	@Mandatory
	@ValidUrl
	@Column
	private String				targetUrl;

	@Mandatory
	@ValidUrl
	@Column
	private String				pictureUrl;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

}

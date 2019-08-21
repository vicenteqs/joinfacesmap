package es.vqs.joinfacesmap.model.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Project extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -5823597056382026444L;

	@ManyToOne
	private User user;
	private String name;
	private Long estimated;

}

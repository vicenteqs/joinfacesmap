package es.vqs.joinfacesmap.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Task extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1578541809354924171L;

	@ManyToOne
	private Project project;

	@ManyToOne
	private User user;

	@ManyToOne
	private Task parent;

	private String name;
	private String description;
	private BigDecimal estimated;
	
}

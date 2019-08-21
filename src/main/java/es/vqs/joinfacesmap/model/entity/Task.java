package es.vqs.joinfacesmap.model.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

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

	@OneToMany(mappedBy = "task")
	@OrderBy("creationDate desc")
	private List<TaskComment> comments;

	private String name;
	private String description;
	private Long estimated;

}

package es.vqs.joinfacesmap.model.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "groups")
public class Group extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -2863888281621193736L;

	@ManyToOne
	private User responsible;

	@ManyToMany(mappedBy = "groups")
	private List<User> members;

	private String name;
	private String description;

}

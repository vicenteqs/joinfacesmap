package es.vqs.joinfacesmap.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class TaskComment extends BaseEntity implements Serializable, Comparable<TaskComment> {

	private static final long serialVersionUID = 1578541809354924171L;

	@ManyToOne
	private User user;

	@ManyToOne
	private Task task;

	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date creationDate;

	@Temporal(TemporalType.TIMESTAMP)
	@UpdateTimestamp
	private Date modificationDate;

	private String comment;

	@Override
	public int compareTo(TaskComment o) {
		if (this.creationDate == null && o.creationDate == null) {
			return 0;
		} else if (this.creationDate == null && o.creationDate != null) {
			return -1;
		} else if (this.creationDate != null && o.creationDate == null) {
			return 1;
		} else {
			return this.getCreationDate().compareTo(o.getCreationDate());
		}
	}
}

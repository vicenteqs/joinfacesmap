package es.vqs.joinfacesmap.model.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class BaseEntity {

	@Id
	@GeneratedValue
	protected Long id;

	@Override
	public String toString() {
		return String.format("%s[id=%d]", this.getClass().getSimpleName(), this.getId());
	}
}

package es.vqs.joinfacesmap.model.pojo;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DailyTaskWork implements Serializable {
	private static final long serialVersionUID = 1822109399847540040L;

	private Long idTask;
	private String taskName;
	private String projectName;
	private Date day;
	private Long time;
	
	public DailyTaskWork(Long id, String tName, String pName) {
		this.idTask = id;
		this.taskName = tName;
		this.projectName = pName;
	}

}

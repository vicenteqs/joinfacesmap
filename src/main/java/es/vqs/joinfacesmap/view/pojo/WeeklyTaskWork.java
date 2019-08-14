package es.vqs.joinfacesmap.view.pojo;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeeklyTaskWork implements Serializable {
	private static final long serialVersionUID = 1822109399847540040L;

	private Long idTask;
	private String taskName;
	private String projectName;
	private Long[] time = { 0l, 0l, 0l, 0l, 0l, 0l, 0l };
	private Long totalWeek;
	private Long totalMonth;
	private Long total;

}

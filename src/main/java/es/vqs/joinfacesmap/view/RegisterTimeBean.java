package es.vqs.joinfacesmap.view;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.vqs.joinfacesmap.service.TaskService;
import es.vqs.joinfacesmap.view.pojo.WeeklyTaskWork;
import lombok.Getter;
import lombok.Setter;

@Component
@ViewScoped
public class RegisterTimeBean implements Serializable {

	private static final long serialVersionUID = -5309121855847611116L;

	@Autowired
	private SessionBean sessionBean;

	@Autowired
	private TaskService taskService;

	@Getter
	@Setter
	private List<WeeklyTaskWork> tasks;

	@Getter
	@Setter
	private Integer month;

	@Getter
	@Setter
	private Integer year;

	@Getter
	@Setter
	private Integer week;

	@Getter
	@Setter
	private List<Integer> months;

	@Getter
	@Setter
	private List<Integer> years;

	@Getter
	@Setter
	private List<Integer> weeks;

	@Getter
	@Setter
	private String[] days;

	private SimpleDateFormat formatter;

	private Calendar weekInstance;

	@PostConstruct
	public void init() {
		this.weekInstance = Calendar.getInstance();
		this.formatter = new SimpleDateFormat("dd/MM/yyyy");

		this.month = this.weekInstance.get(Calendar.MONTH);
		this.year = this.weekInstance.get(Calendar.YEAR);
		this.week = this.weekInstance.get(Calendar.WEEK_OF_YEAR);
		this.months = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
		this.years = Arrays.asList(2019, 2018, 2017, 2016);

		this.updateGrid();

	}

	public void onDateChange() {
		this.weekInstance.set(Calendar.MONTH, this.month);
		this.weekInstance.set(Calendar.YEAR, this.year);
		this.weekInstance.set(Calendar.DAY_OF_MONTH, 1);
		this.week = this.weekInstance.get(Calendar.WEEK_OF_YEAR);

		this.updateGrid();

	}

	public void saveWorkTime() {
		this.taskService.saveWorkTime(this.tasks, (Calendar) this.weekInstance.clone(), this.sessionBean.getLoggedUser());

	}

	public void changeWeek(int amount) {
		this.weekInstance.add(Calendar.WEEK_OF_YEAR, amount);

		this.year = this.weekInstance.get(Calendar.YEAR);
		this.month = this.weekInstance.get(Calendar.MONTH);
		this.week = this.weekInstance.get(Calendar.WEEK_OF_YEAR);

		this.updateGrid();

	}

	private void updateGrid() {
		Calendar cal = (Calendar) this.weekInstance.clone();
		this.days = new String[7];
		this.tasks = this.taskService.generateRegisterWorkGrid(this.week, this.year, this.sessionBean.getLoggedUser());

		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		days[0] = formatter.format(cal.getTime());
		for (int i = 1; i < 7; i++) {
			cal.add(Calendar.DAY_OF_WEEK, 1);
			days[i] = formatter.format(cal.getTime());
		}
	}

}
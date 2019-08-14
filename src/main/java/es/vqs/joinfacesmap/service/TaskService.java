package es.vqs.joinfacesmap.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.vqs.joinfacesmap.model.entity.Project;
import es.vqs.joinfacesmap.model.entity.Task;
import es.vqs.joinfacesmap.model.entity.TaskWork;
import es.vqs.joinfacesmap.model.entity.User;
import es.vqs.joinfacesmap.model.pojo.DailyTaskWork;
import es.vqs.joinfacesmap.model.repository.TaskRepository;
import es.vqs.joinfacesmap.model.repository.TaskWorkRepository;
import es.vqs.joinfacesmap.utils.CalendarUtils;
import es.vqs.joinfacesmap.view.pojo.WeeklyTaskWork;

@Service
public class TaskService extends BaseService<Task> {

	@Autowired
	private TaskRepository taskRepository;

	@Autowired
	private TaskWorkRepository taskWorkRepository;

	public List<Task> findByLikeName(String name) {
		return this.taskRepository.findByNameContaining(name);
	}

	public Iterable<Task> findFirstLevelTasksByProject(Project project) {
		return this.taskRepository.findByProjectAndParentNull(project);
	}

	public List<Task> findByProjectAndNameContaining(Project project, String query) {
		return this.taskRepository.findByProjectAndNameContaining(project, query);
	}

	public List<Task> findAllTasksByProject(Project project) {
		return this.taskRepository.findByProject(project);
	}

	public List<WeeklyTaskWork> generateRegisterWorkGrid(Integer week, Integer year, User user) {

		List<DailyTaskWork> works;
		List<DailyTaskWork> tasksNoWork;

		Calendar calendarStart = CalendarUtils.getCalendarFirstDayWeek(week, year);
		Calendar calendarEnd = CalendarUtils.getCalendarLastDayWeek(week, year);

		works = this.taskRepository.findWorkTimeTasksByDateAndUser(calendarStart.getTime(), calendarEnd.getTime(),
				user);
		tasksNoWork = this.taskRepository.findTasksWithoutWorkByUserAndDate(calendarStart.getTime(),
				calendarEnd.getTime(), user);

		works.addAll(tasksNoWork);

		return this.dailyToWeeklyTaskWork(works);

	}

	public void saveWorkTime(List<WeeklyTaskWork> tasks, Calendar weekInstance, User user) {
		int i = 0;
		weekInstance.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		do {
			final int j = i;
			tasks.stream().filter(t -> t.getTime()[j] != 0)
					.forEach(t -> this.registerDayWork(t.getIdTask(), t.getTime()[j], weekInstance, user));
			weekInstance.add(Calendar.DAY_OF_WEEK, 1);
			i++;
		} while (i < 7);
	}

	private TaskWork registerDayWork(Long idTask, Long time, Calendar weekInstance, User user) {
		TaskWork tw = this.taskWorkRepository.findByIdTaskDayUser(idTask, weekInstance.getTime(), user);
		if (tw == null) {
			tw = new TaskWork();
			tw.setTask(this.taskRepository.findById(idTask).orElse(null));
			tw.setWorkingDate(weekInstance.getTime());
			tw.setUser(user);
		}
		tw.setSeconds(time);
		return this.taskWorkRepository.save(tw);
	}

	private List<WeeklyTaskWork> dailyToWeeklyTaskWork(List<DailyTaskWork> dailyTaskWorks) {
		List<WeeklyTaskWork> weeklyTaskWorkList = new ArrayList<>();
		WeeklyTaskWork weeklyTaskWork = null;
		Calendar calAux = Calendar.getInstance();
		int dayOfArray;

		for (DailyTaskWork dtw : dailyTaskWorks) {
			weeklyTaskWork = weeklyTaskWorkList.stream().filter(wtw -> wtw.getIdTask().equals(dtw.getIdTask()))
					.findFirst().orElse(null);

			if (weeklyTaskWork == null) {
				weeklyTaskWork = new WeeklyTaskWork();
				weeklyTaskWork.setIdTask(dtw.getIdTask());
				weeklyTaskWork.setTaskName(dtw.getTaskName());
				weeklyTaskWork.setProjectName(dtw.getProjectName());
				weeklyTaskWorkList.add(weeklyTaskWork);
			}

			if (dtw.getDay() != null) {
				dayOfArray = 6;
				calAux.setTime(dtw.getDay());
				if (calAux.get(Calendar.DAY_OF_WEEK) - 2 >= 0) {
					dayOfArray = calAux.get(Calendar.DAY_OF_WEEK) - 2;
				}
				weeklyTaskWork.getTime()[dayOfArray] = dtw.getTime();
			}
		}

		return weeklyTaskWorkList;
	}
}

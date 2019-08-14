package es.vqs.joinfacesmap.model.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;

import es.vqs.joinfacesmap.model.entity.Project;
import es.vqs.joinfacesmap.model.entity.Task;
import es.vqs.joinfacesmap.model.entity.User;
import es.vqs.joinfacesmap.model.pojo.DailyTaskWork;

public interface TaskRepository extends BaseRepository<Task> {

	List<Task> findByNameContaining(String name);

	Iterable<Task> findByProjectAndParentNull(Project project);

	List<Task> findByProjectAndNameContaining(Project project, String query);

	List<Task> findByProject(Project project);

	List<Task> findByUser(User user);

	@Query("select new es.vqs.joinfacesmap.model.pojo.DailyTaskWork(t.id, t.name, p.name, tw.workingDate, tw.seconds) from TaskWork tw inner join tw.task t inner join t.project p where tw.workingDate between ?1 and ?2 and tw.user = ?3")
	List<DailyTaskWork> findWorkTimeTasksByDateAndUser(Date start, Date end, User user);

	@Query("select new es.vqs.joinfacesmap.model.pojo.DailyTaskWork(t.id, t.name, p.name) from Task t left join TaskWork tw on tw.task = t and tw.workingDate >= ?1 and tw.workingDate <= ?2 inner join t.project p where tw is null and t.user = ?3")
	List<DailyTaskWork> findTasksWithoutWorkByUserAndDate(Date start, Date end, User user);

}

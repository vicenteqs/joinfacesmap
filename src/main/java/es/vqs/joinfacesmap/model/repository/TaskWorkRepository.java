package es.vqs.joinfacesmap.model.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;

import es.vqs.joinfacesmap.model.entity.TaskWork;
import es.vqs.joinfacesmap.model.entity.User;

public interface TaskWorkRepository extends BaseRepository<TaskWork> {

	List<TaskWork> findByWorkingDateBetweenAndUser(Date start, Date end, User user);

	@Query("select tw from TaskWork tw inner join tw.task t where t.id = ?1 and tw.workingDate = ?2 and tw.user = ?3")
	TaskWork findByIdTaskDayUser(Long idTask, Date time, User user);
	
}

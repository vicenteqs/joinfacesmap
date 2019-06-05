package es.vqs.joinfacesmap.model.repository;

import java.util.List;

import es.vqs.joinfacesmap.model.entity.Project;
import es.vqs.joinfacesmap.model.entity.Task;

public interface TaskRepository extends BaseRepository<Task> {

	List<Task> findByNameContaining(String name);

	Iterable<Task> findByProjectAndParentNull(Project project);

	List<Task> findByProjectAndNameContaining(Project project, String query);

	List<Task> findByProject(Project project);

}

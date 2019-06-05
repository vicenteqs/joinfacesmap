package es.vqs.joinfacesmap.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.vqs.joinfacesmap.model.entity.Project;
import es.vqs.joinfacesmap.model.entity.Task;
import es.vqs.joinfacesmap.model.repository.TaskRepository;

@Service
public class TaskService extends BaseService<Task> {

	@Autowired
	private TaskRepository taskRepository;

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
}

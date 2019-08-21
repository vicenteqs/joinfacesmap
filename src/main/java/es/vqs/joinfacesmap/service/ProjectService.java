package es.vqs.joinfacesmap.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.vqs.joinfacesmap.model.entity.Project;
import es.vqs.joinfacesmap.model.repository.ProjectRepository;
import es.vqs.joinfacesmap.model.repository.TaskWorkRepository;

@Service
public class ProjectService extends BaseService<Project> {

	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private TaskWorkRepository taskWorkRepository;

	public List<Project> findByLikeName(String name) {
		return this.projectRepository.findByNameContaining(name);
	}

	public Long getTotalWork(Project project) {
		return this.taskWorkRepository.getTotalWorkProject(project);
	}

}

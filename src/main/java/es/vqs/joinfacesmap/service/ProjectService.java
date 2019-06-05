package es.vqs.joinfacesmap.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.vqs.joinfacesmap.model.entity.Project;
import es.vqs.joinfacesmap.model.repository.ProjectRepository;

@Service
public class ProjectService extends BaseService<Project> {

	@Autowired
	private ProjectRepository projectRepository;
	
	public List<Project> findByLikeName(String name) {
		return this.projectRepository.findByNameContaining(name);
	}

}

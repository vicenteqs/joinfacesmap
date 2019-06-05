package es.vqs.joinfacesmap.model.repository;

import java.util.List;

import es.vqs.joinfacesmap.model.entity.Project;

public interface ProjectRepository extends BaseRepository<Project> {

	List<Project> findByNameContaining(String name);

}

package es.vqs.joinfacesmap.model.repository;

import es.vqs.joinfacesmap.model.entity.Group;

public interface GroupRepository extends BaseRepository<Group> {

	public Iterable<Group> findAllByOrderByNameAsc();

}

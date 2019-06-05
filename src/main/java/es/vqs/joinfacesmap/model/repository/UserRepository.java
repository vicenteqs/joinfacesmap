package es.vqs.joinfacesmap.model.repository;

import java.util.List;

import es.vqs.joinfacesmap.model.entity.User;

public interface UserRepository extends BaseRepository<User> {

	List<User> findByNameContaining(String name);

	User findByLogin(String username);
	
}

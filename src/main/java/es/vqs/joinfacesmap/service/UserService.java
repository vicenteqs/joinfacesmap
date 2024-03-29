package es.vqs.joinfacesmap.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.vqs.joinfacesmap.model.entity.User;
import es.vqs.joinfacesmap.model.repository.UserRepository;

@Service
public class UserService extends BaseService<User> {

	@Autowired
	private UserRepository userRepository;

	public List<User> findByLikeName(String name) {
		return this.userRepository.findByDisplayNameContaining(name);
	}

	public User findByLogin(String login) {
		return this.userRepository.findByLogin(login);
	}

	public Iterable<User> findAllOrdered() {
		return this.userRepository.findAllByOrderByDisplayNameAsc();
	}
}

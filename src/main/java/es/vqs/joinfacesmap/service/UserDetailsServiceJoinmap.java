package es.vqs.joinfacesmap.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import es.vqs.joinfacesmap.model.entity.User;
import es.vqs.joinfacesmap.model.repository.UserRepository;

@Service
public class UserDetailsServiceJoinmap implements UserDetailsService {

	@Autowired
	private UserRepository applicationUserRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User applicationUser = this.applicationUserRepository.findByLogin(username);
		if (applicationUser == null) {
			throw new UsernameNotFoundException(username);
		}
		return new org.springframework.security.core.userdetails.User(applicationUser.getUsername(),
				applicationUser.getPassword(), new ArrayList<>());
	}
}
package es.vqs.joinfacesmap.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.vqs.joinfacesmap.model.entity.Project;
import es.vqs.joinfacesmap.model.entity.User;
import es.vqs.joinfacesmap.service.ProjectService;
import es.vqs.joinfacesmap.service.UserService;
import lombok.Getter;
import lombok.Setter;

@Component
@ViewScoped
public class CreateProjectBean implements Serializable {

	private static final long serialVersionUID = -2873080720482413749L;

	@Autowired
	private UserService userService;
	
	@Autowired
	private ProjectService projectService;
	
	@Getter
	@Setter
	private User user;
	
	@Getter
	@Setter
	private List<User> filteredUsers;
	
	@Getter
	@Setter
	private String name;
	
	@PostConstruct
	public void init() {

	}

	public List<User> findUsers(String query) {
		this.filteredUsers = this.userService.findByLikeName(query);
		return this.filteredUsers;
	}
	
	public void create() {
		Project project = new Project();
		project.setName(this.name);
		project.setUser(this.user);
		this.projectService.save(project);
		PrimeFaces.current().dialog().closeDynamic(project);
	}

}
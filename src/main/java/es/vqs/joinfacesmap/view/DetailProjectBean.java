package es.vqs.joinfacesmap.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;

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
public class DetailProjectBean implements Serializable {

	private static final long serialVersionUID = -2873080720482413749L;

	@Autowired
	private UserService userService;
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private SessionBean sessionBean;

	@Getter
	@Setter
	private User user;
	
	@Getter
	@Setter
	private List<User> filteredUsers;
	
	@Getter
	@Setter
	private String name;
	
	private Project project;
	
	@PostConstruct
	public void init() {
		this.project = this.projectService.findById(this.sessionBean.getIdProject());
		this.name = project.getName();
		this.user = project.getUser();
		
	}

	public List<User> findUsers(String query) {
		this.filteredUsers = this.userService.findByLikeName(query);
		return this.filteredUsers;
	}
	
	public void save() {
		this.project.setName(this.name);
		this.project.setUser(this.user);
		this.project = this.projectService.save(project);
	}

}
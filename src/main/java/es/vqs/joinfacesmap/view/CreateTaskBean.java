package es.vqs.joinfacesmap.view;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.vqs.joinfacesmap.model.entity.Project;
import es.vqs.joinfacesmap.model.entity.Task;
import es.vqs.joinfacesmap.model.entity.User;
import es.vqs.joinfacesmap.service.ProjectService;
import es.vqs.joinfacesmap.service.TaskService;
import es.vqs.joinfacesmap.service.UserService;
import lombok.Getter;
import lombok.Setter;

@Component
@ViewScoped
public class CreateTaskBean implements Serializable {

	private static final long serialVersionUID = -1197848053208378878L;

	@Autowired
	private UserService userService;
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private SessionBean sessionBean;
	
	@Getter
	@Setter
	private User user;
	
	@Getter
	@Setter
	private Project project;
	
	@Getter
	@Setter
	private Task task;

	@Getter
	@Setter
	private List<User> filteredUsers;
	
	@Getter
	@Setter
	private List<Project> filteredProjects;
	
	@Getter
	@Setter
	private List<Task> filteredTasks;

	@Getter
	@Setter
	private BigDecimal estimated;
	
	@Getter
	@Setter
	private String name;
	
	@Getter
	@Setter
	private String description;
	
	
	@PostConstruct
	public void init() {
		if (this.sessionBean.getIdProject() != null) {
			this.project = this.projectService.findById(this.sessionBean.getIdProject());
			this.filteredProjects = new ArrayList<>();
			this.filteredProjects.add(this.project);
		}
	}

	public List<User> findUsers(String query) {
		this.filteredUsers = this.userService.findByLikeName(query);
		return this.filteredUsers;
	}
	
	public List<Project> findProjects(String query) {
		this.filteredProjects = this.projectService.findByLikeName(query);
		return this.filteredProjects;
	}
	
	public List<Task> findTasks(String query) {
		this.filteredTasks = null;
		
		if (this.project != null) {
			this.filteredTasks = this.taskService.findByProjectAndNameContaining(this.project, query);
		}
		return this.filteredTasks;
	}
	
	public void create() {
		Task task = new Task();
		task.setEstimated(this.estimated);
		task.setName(this.name);
		task.setProject(this.project);
		task.setUser(this.user);
		task.setParent(this.task);
		task.setDescription(this.description);
		this.taskService.save(task);
		PrimeFaces.current().dialog().closeDynamic(task);
	}

}
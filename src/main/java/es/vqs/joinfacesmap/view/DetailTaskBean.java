package es.vqs.joinfacesmap.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.vqs.joinfacesmap.model.entity.Project;
import es.vqs.joinfacesmap.model.entity.Task;
import es.vqs.joinfacesmap.model.entity.TaskComment;
import es.vqs.joinfacesmap.model.entity.User;
import es.vqs.joinfacesmap.service.ProjectService;
import es.vqs.joinfacesmap.service.TaskCommentService;
import es.vqs.joinfacesmap.service.TaskService;
import es.vqs.joinfacesmap.service.UserService;
import lombok.Getter;
import lombok.Setter;

@Component
@ViewScoped
public class DetailTaskBean implements Serializable {

	private static final long serialVersionUID = -1197848053208378878L;

	@Autowired
	private UserService userService;

	@Autowired
	private TaskService taskService;

	@Autowired
	private TaskCommentService taskCommentService;

	@Autowired
	private ProjectService projectService;

	@Autowired
	private GeneralProjectBean generalProjectBean;

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
	private Task parent;

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
	private Long estimated;

	@Getter
	@Setter
	private String name;

	@Getter
	@Setter
	private String description;

	@Getter
	@Setter
	private String newComment;

	@Getter
	@Setter
	private List<TaskComment> comments;

	private Task task;

	@PostConstruct
	public void init() {
		this.filteredProjects = new ArrayList<>();
		this.filteredTasks = new ArrayList<>();
		this.filteredUsers = new ArrayList<>();

		this.task = this.taskService.findById(this.generalProjectBean.getSelectedNode().getId());
		this.comments = this.task.getComments();
		this.project = this.task.getProject();
		this.user = this.task.getUser();
		this.parent = this.task.getParent();
		this.estimated = this.task.getEstimated();
		this.name = this.task.getName();
		this.description = this.task.getDescription();

		this.filteredProjects.add(this.project);
		this.filteredTasks.add(this.parent);
		this.filteredUsers.add(this.user);
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

	public void save() {
		this.task.setEstimated(this.estimated);
		this.task.setName(this.name);
		this.task.setProject(this.project);
		this.task.setUser(this.user);
		this.task.setParent(this.parent);
		this.task.setDescription(this.description);
		this.task = this.taskService.save(task);

		this.generalProjectBean.generateProjectTree();
		this.generalProjectBean.refreshProjectTree();
	}

	public void addComment() {
		TaskComment comment = new TaskComment();
		comment.setComment(this.newComment);
		comment.setTask(this.task);
		comment.setUser(this.sessionBean.getLoggedUser());
		this.comments.add(this.taskCommentService.save(comment));
		Collections.sort(this.comments, Collections.reverseOrder());
	}

}
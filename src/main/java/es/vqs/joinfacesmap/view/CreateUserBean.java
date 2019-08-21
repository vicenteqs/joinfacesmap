package es.vqs.joinfacesmap.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.vqs.joinfacesmap.model.entity.Group;
import es.vqs.joinfacesmap.model.entity.User;
import es.vqs.joinfacesmap.service.GroupService;
import es.vqs.joinfacesmap.service.UserService;
import lombok.Getter;
import lombok.Setter;

@Component
@ViewScoped
public class CreateUserBean implements Serializable {

	private static final long serialVersionUID = -1197848053208378878L;

	@Autowired
	private UserService userService;

	@Autowired
	private GroupService groupService;

	@Getter
	@Setter
	private User user;

	@Getter
	@Setter
	private List<User> filteredUsers;

	@Getter
	@Setter
	private String name;

	@Getter
	@Setter
	private String description;

	@PostConstruct
	public void init() {

	}

	public List<User> findUsers(String query) {
		this.filteredUsers = this.userService.findByLikeName(query);
		return this.filteredUsers;
	}

	public void create() {
		Group group = new Group();
		group.setName(this.name);
		group.setResponsible(this.user);
		group.setDescription(this.description);
		PrimeFaces.current().dialog().closeDynamic(this.groupService.save(group));
	}

}
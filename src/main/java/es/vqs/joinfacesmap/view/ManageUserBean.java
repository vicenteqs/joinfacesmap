package es.vqs.joinfacesmap.view;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.vqs.joinfacesmap.model.entity.User;
import es.vqs.joinfacesmap.service.UserService;
import lombok.Getter;
import lombok.Setter;

@Component
@ViewScoped
public class ManageUserBean implements Serializable {

	private static final long serialVersionUID = -5309121855847611116L;

	@Autowired
	private UserService userService;

	@Getter
	@Setter
	private Iterable<User> users;

	@Getter
	@Setter
	private User selectedUser;

	@PostConstruct
	public void init() {
		this.users = this.userService.findAll();

	}

	public void goToDetails() {

	}

	public void delete() {

	}

	public void openCreateGroup() {

		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("width", 380);
		options.put("height", 280);
		options.put("contentWidth", "100%");
		options.put("contentHeight", "100%");
		options.put("resizable", false);
		options.put("draggable", true);
		options.put("closeOnEscape", true);
		PrimeFaces.current().dialog().openDynamic("/settings/user/create", options, null);

	}

	public void handleCreateGroup() {
		this.users = this.userService.findAllOrdered();
	}

}
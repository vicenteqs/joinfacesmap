package es.vqs.joinfacesmap.view;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.vqs.joinfacesmap.model.entity.Group;
import es.vqs.joinfacesmap.service.GroupService;
import lombok.Getter;
import lombok.Setter;

@Component
@ViewScoped
public class ManageGroupBean implements Serializable {

	private static final long serialVersionUID = -5309121855847611116L;

	@Autowired
	private SessionBean sessionBean;

	@Autowired
	private GroupService groupService;

	@Getter
	@Setter
	private Iterable<Group> groups;

	@Getter
	@Setter
	private Group selectedGroup;

	@PostConstruct
	public void init() {
		this.groups = this.groupService.findAll();

	}

	public void goToDetails() {
		this.sessionBean.changeFragment("/settings/groups/detail.xhtml");
	}

	public void delete(Group group) {
		this.groupService.delete(group);
		this.groups = this.groupService.findAllOrdered();
	}

	public void openCreateGroup() {

		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("width", 400);
		options.put("height", 340);
		options.put("contentWidth", "100%");
		options.put("contentHeight", "100%");
		options.put("resizable", false);
		options.put("draggable", true);
		options.put("closeOnEscape", true);
		PrimeFaces.current().dialog().openDynamic("/settings/groups/create", options, null);

	}

	public void handleCreateGroup() {
		this.groups = this.groupService.findAllOrdered();
	}

}
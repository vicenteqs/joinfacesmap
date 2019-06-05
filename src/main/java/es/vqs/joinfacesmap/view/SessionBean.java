package es.vqs.joinfacesmap.view;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@SessionScoped
public class SessionBean implements Serializable {

	private static final long serialVersionUID = 5139026355400486084L;

	@Getter
	@Setter
	private String page;

	@Getter
	@Setter
	private Long idProject;

	@PostConstruct
	public void init() {
		this.page = "default.xhtml";
	}

	public void showMessage() {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "What we do in life",
				"Echoes in eternity.");

		PrimeFaces.current().dialog().showMessageDynamic(message);
	}

	public void createTask() {

		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("width", 640);
		options.put("height", 400);
		options.put("contentWidth", "100%");
		options.put("contentHeight", "100%");
		options.put("resizable", false);
		options.put("draggable", true);
		options.put("closeOnEscape", true);
		PrimeFaces.current().dialog().openDynamic("/task/create", options, null);
	}

	public void createProject() {

		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("width", 640);
		options.put("height", 400);
		options.put("contentWidth", "100%");
		options.put("contentHeight", "100%");
		options.put("resizable", false);
		options.put("draggable", true);
		options.put("closeOnEscape", true);
		PrimeFaces.current().dialog().openDynamic("/project/create", options, null);
	}

	public void handleCreateTask(SelectEvent event) {
		// Task task = (Task) event.getObject();
	}

	public void handleCreateProject(SelectEvent event) {
		// Task task = (Task) event.getObject();
	}

	public void goToListProjects() {
		this.changeFragment("/project/list.xhtml");
	}

	public void goToIndex() {
		this.changeFragment("/default.xhtml");
	}

	public void goToProjectGeneral() {
		this.changeFragment("/project/general.xhtml");
	}

	public void changeFragment(String _page) {
		Map<String, Object> viewMap = FacesContext.getCurrentInstance().getViewRoot().getViewMap();
		for (Iterator<Map.Entry<String, Object>> it = viewMap.entrySet().iterator(); it.hasNext();) {
			it.next();
			it.remove();
		}
		this.page = _page;
		PrimeFaces.current().ajax().update("mainPanel");
	}
}
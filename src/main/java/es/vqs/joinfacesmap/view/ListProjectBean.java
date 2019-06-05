package es.vqs.joinfacesmap.view;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;

import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.vqs.joinfacesmap.model.entity.Project;
import es.vqs.joinfacesmap.service.BaseService;
import lombok.Getter;
import lombok.Setter;

@Component
@ViewScoped
public class ListProjectBean implements Serializable {

	private static final long serialVersionUID = 8213788060948522159L;

	@Autowired
	private BaseService<Project> projectService;
	
	@Autowired
	private SessionBean sessionBean;

	@Getter
	@Setter
	private Iterable<Project> listProject;

	@Getter
	@Setter
	private Project selectedProject;

	@PostConstruct
	public void init() {
		this.listProject = this.projectService.findAll();
	}

	public void onProjectSelected(SelectEvent event) {
		this.sessionBean.setIdProject(this.selectedProject.getId());
		this.sessionBean.goToProjectGeneral();
	}
}
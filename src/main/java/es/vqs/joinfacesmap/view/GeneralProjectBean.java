package es.vqs.joinfacesmap.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import org.primefaces.PrimeFaces;
import org.primefaces.event.TreeDragDropEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.vqs.joinfacesmap.model.entity.Project;
import es.vqs.joinfacesmap.model.entity.Task;
import es.vqs.joinfacesmap.model.enums.ProjectNodeType;
import es.vqs.joinfacesmap.service.ProjectService;
import es.vqs.joinfacesmap.service.TaskService;
import es.vqs.joinfacesmap.view.pojo.ProjectNode;
import lombok.Getter;
import lombok.Setter;

@Component
@ViewScoped
public class GeneralProjectBean implements Serializable {

	private static final long serialVersionUID = -5309121855847611116L;

	@Autowired
	private SessionBean sessionBean;

	@Autowired
	private ProjectService projectService;

	@Autowired
	private TaskService taskService;

	@Getter
	@Setter
	private Project project;

	@Getter
	@Setter
	private TreeNode root;

	@Getter
	@Setter
	private ProjectNode selectedNode;

	@Getter
	@Setter
	private String detailPage;

	private List<ProjectNode> nodes;

	@PostConstruct
	public void init() {
		this.detailPage = "/project/detail.xhtml";
		this.project = this.projectService.findById(this.sessionBean.getIdProject());
		this.generateProjectTree();
	}

	public void generateProjectTree() {
		TreeNode project;
		List<Task> tasks;
		this.nodes = new ArrayList<>();
		this.root = new DefaultTreeNode("Root", null);

		tasks = this.taskService.findAllTasksByProject(this.project);
		project = new ProjectNode(this.project.getId(), null, this.project.getName(), this.root,
				ProjectNodeType.PROJECT);
		project.setExpanded(true);

		tasks.stream().filter(task -> task.getParent() == null).forEach(task -> this.paintNode(task, project));
		tasks.stream().filter(task -> task.getParent() != null).forEach(task -> this.paintNode(task, null));
		this.nodes.stream().filter(node -> node.getIdParent() != null).forEach(node -> this.setParentNode(node));

	}

	public void refreshProjectTree() {
		PrimeFaces.current().ajax().update("projectTreeForm");
	}

	private void setParentNode(ProjectNode node) {
		ProjectNode nodeParent = this.nodes.stream().filter(n -> n.getId() == node.getIdParent()).findFirst()
				.orElse(null);
		node.setParent(nodeParent);
		nodeParent.getChildren().add(node);
	}

	private void paintNode(Task task, TreeNode parent) {
		Long idParent = null;
		Task parentTask = task.getParent();

		if (parentTask != null) {
			idParent = parentTask.getId();
		}

		this.nodes.add(new ProjectNode(task.getId(), idParent, task.getName(), parent, ProjectNodeType.TASK));
	}

	public void onDragDropNode(TreeDragDropEvent event) {

	}

	public void showDetail() {
		Map<String, Object> viewMap = FacesContext.getCurrentInstance().getViewRoot().getViewMap();
		for (Iterator<Map.Entry<String, Object>> it = viewMap.entrySet().iterator(); it.hasNext();) {
			Map.Entry<String, Object> entry = it.next();
			if (entry.getKey().equals("detailProjectBean") || entry.getKey().equals("detailTaskBean")) {
				it.remove();
			}
		}
		switch (this.selectedNode.getNodeType()) {
		case PROJECT:
			this.detailPage = "/project/detail.xhtml";
			break;
		case TASK:
			this.detailPage = "/task/detail.xhtml";
			break;
		default:
		}
	}

}
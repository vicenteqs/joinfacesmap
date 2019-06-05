package es.vqs.joinfacesmap.view.pojo;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import es.vqs.joinfacesmap.model.enums.ProjectNodeType;
import lombok.Getter;
import lombok.Setter;

public class ProjectNode extends DefaultTreeNode {

	private static final long serialVersionUID = 3422776332367660534L;

	@Getter
	@Setter
	private ProjectNodeType nodeType;
	
	@Getter
	@Setter
	private String icon;

	@Getter
	@Setter
	private Long id;

	@Getter
	@Setter
	private Long idParent;

	public ProjectNode(Long id, Long idParent, Object data, TreeNode root, ProjectNodeType type) {
		super(data, root);
		this.nodeType = type;
		this.id = id;
		this.idParent = idParent;
		this.icon = "task";
		if (type.isProject()) {
			icon = "project";
		} 
	}

}

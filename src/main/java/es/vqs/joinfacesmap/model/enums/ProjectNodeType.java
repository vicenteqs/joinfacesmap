package es.vqs.joinfacesmap.model.enums;

public enum ProjectNodeType {
	MODULE, TASK, PROJECT;

	public boolean isProject() {
		return this == ProjectNodeType.PROJECT;
	}
}

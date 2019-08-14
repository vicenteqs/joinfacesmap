package es.vqs.joinfacesmap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.vqs.joinfacesmap.model.entity.TaskComment;
import es.vqs.joinfacesmap.model.repository.TaskCommentRepository;

@Service
public class TaskCommentService extends BaseService<TaskComment> {

	@Autowired
	private TaskCommentRepository taskCommentRepository;

}

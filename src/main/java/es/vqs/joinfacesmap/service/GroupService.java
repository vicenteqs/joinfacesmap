package es.vqs.joinfacesmap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.vqs.joinfacesmap.model.entity.Group;
import es.vqs.joinfacesmap.model.repository.GroupRepository;

@Service
public class GroupService extends BaseService<Group> {
	
	@Autowired
	private GroupRepository groupRepository;
	
	public Iterable<Group> findAllOrdered() {
		return this.groupRepository.findAllByOrderByNameAsc();
	}

	public void delete(Group group) {
		this.groupRepository.delete(group);
	}

}

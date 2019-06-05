package es.vqs.joinfacesmap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.vqs.joinfacesmap.model.repository.BaseRepository;

@Service
public abstract class BaseService<T> {

	@Autowired
	protected BaseRepository<T> baseRepository;

	public T findById(Long id) {
		return this.baseRepository.findById(id).orElse(null);
	}
	
	public T save(T entity) {
		return this.baseRepository.save(entity);
	}
	
	public Iterable<T> findAll() {
		return this.baseRepository.findAll();
	}
}

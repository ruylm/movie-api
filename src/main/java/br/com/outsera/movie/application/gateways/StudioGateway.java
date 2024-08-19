package br.com.outsera.movie.application.gateways;

import java.util.List;

import br.com.outsera.movie.domain.entity.Studio;

public interface StudioGateway {
	public Studio create(Studio studio);
	public Studio update(Studio studio);
	public Studio findByName(Studio studio);
	public List<Studio> getAll();
	public Studio getById(Long id);
	public void remove(Long id);
	public boolean isLinkedStudioWithMovie(Long id);
	
}

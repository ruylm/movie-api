package br.com.outsera.movie.application.gateways;

import br.com.outsera.movie.domain.entity.Studio;

public interface StudioGateway {
	Studio createStudio(Studio studio);
	Studio findStudioByName(Studio studio);
}

package ar.edu.unq.desapp.grupom.backenddesappapi

import lombok.Generated
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer

@Generated
class ServletInitializer : SpringBootServletInitializer() {

	@Generated
	override fun configure(application: SpringApplicationBuilder): SpringApplicationBuilder {
		return application.sources(BackendDesappApiApplication::class.java)
	}

}

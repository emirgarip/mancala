package com.bol.mancala;

import com.sun.faces.config.ConfigureListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import javax.faces.webapp.FacesServlet;

/**
 * This is the application class for starting application.
 * @author emir
 */
@SpringBootApplication
public class MancalaApplication {

	/**
	 * This is the main method for start the application.
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(MancalaApplication.class, args);
	}

	/**
	 * This bean is helping us for activate the web request, which has offset of xhtml.
	 * @return
	 */
	@Bean
	public ServletRegistrationBean facesServletRegistration() {
		ServletRegistrationBean registration = new ServletRegistrationBean<>(new FacesServlet(), "*.xhtml");
		registration.setLoadOnStartup(1);
		return registration;
	}


	/**
	 * This bean is helping us for setting configurations.
	 * We can decide which library using for JSF.
	 * @return
	 */
	@Bean
	public ServletContextInitializer servletContextInitializer() {
		return servletContext -> {
			servletContext.setInitParameter("com.sun.faces.forceLoadConfiguration", Boolean.TRUE.toString());
			servletContext.setInitParameter("primefaces.THEME", "nova-light");
		};
	}

	/**
	 * This bean is helping us for loading configuration of JSF and starting the JSF lifecycle.
	 * @return
	 */
	@Bean
	public ServletListenerRegistrationBean<ConfigureListener> jsfConfigureListener() {
		return new ServletListenerRegistrationBean<>(new ConfigureListener());
	}

}

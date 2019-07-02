package myDockerDemo;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

//host:8080/myDockerDemo/v1/resource

@ApplicationPath("v1")
public class AppConfig extends Application{
	private Set<Class<?>> resources = new HashSet<>();
	
	public AppConfig() {
		resources.add(Demo.class);
		System.out.println("Created AppConfig");
	}
	@Override
	public Set<Class<?>> getClasses() {
		return resources;
	}
	
}

package org.youngsoft.bench.core.services;

import java.util.HashMap;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;

@Component(property="scheduler.expression"+"=* * * * * ?")
public class EventSourceComponent {
EventAdmin	eventAdmin;
	
	public void run(Object object) {
		Event event = new Event( 
			"osgi/enroute/examples/ping", 
			new HashMap<String,Object>() );
		eventAdmin.postEvent(event);
	}
	
	@Reference
	void setEventAdmin( EventAdmin eventAdmin) {
		this.eventAdmin = eventAdmin;
	}
}
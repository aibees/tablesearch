package tableSearch.handler;

import tableSearch.listener.EventListener;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class EventHandler {
	private static final int MAX_THREAD = 5;
	
	private static List<EventListener> listeners = new CopyOnWriteArrayList<EventListener>();

	public static synchronized List<EventListener> getListener() {
		return listeners;
	}
	
	public static synchronized void addListener(EventListener e) {
		if(getListener().indexOf(e) == -1) {
			listeners.add(e);
		}
	}
	
	public static synchronized void removeListener(EventListener e) {
		if(getListener().indexOf(e) != -1) {
			listeners.remove(e);
		}
	}
	
	public static synchronized void callEvent(final Class<?> caller, String event, HashMap<String, String> data) {
		callEvent(caller, event, data, true);
	}
	
	public static synchronized void callEvent(final Class<?> caller, String event, HashMap<String, String> data, boolean async) {
		if(async) {
			callEventSync(caller, event, data);
		}
		else {
			callEventAsync(caller, event, data);
		}
	}
	
	private static synchronized void callEventSync(final Class<?> caller, final String event, HashMap<String, String> data) {
		ExecutorService executorService = Executors.newFixedThreadPool(MAX_THREAD);
		
		for(final EventListener listener : listeners) {
			executorService.execute(new Runnable() {
				@Override
				public void run() {
					if(!listener.getClass().getName().equals(caller.getName())) {
						listener.onEvent(event, data);
					}
				}
			});
		}
		
		executorService.shutdown();
	}
	
	private static synchronized void callEventAsync(final Class<?> caller, final String event, HashMap<String, String> data) {
		for(final EventListener listener : listeners) {
			if(!listener.getClass().getName().equals(caller.getName())) {
				listener.onEvent(event, data);
			}
		}
	}

}

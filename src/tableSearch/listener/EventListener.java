package tableSearch.listener;

import java.util.HashMap;

public interface EventListener {
	public void onEvent(String event, HashMap<String, String> param);
}

package tableSearch.listener.mouse;

import tableSearch.handler.EventHandler;

import java.awt.event.MouseEvent;

import javax.swing.JList;
import javax.swing.event.MouseInputAdapter;

public class ClickListener extends MouseInputAdapter {
	
    @Override
    public void mouseClicked(MouseEvent e) {
    	
        JList list = (JList)e.getSource();

        if(e.getClickCount() == 2) {
            int index = list.locationToIndex(e.getPoint());
            String item = list.getModel().getElementAt(index).toString();
            System.out.println("list clicked: " + item);
            
            
        }
    }
}

package br.unioeste.jgoose.e4j.actions;

import java.awt.Component;
import java.awt.event.ActionEvent;
import br.unioeste.jgoose.e4j.swing.BasicGraphEditor;

/**
 * Static Class for manage the actions.
 * @author Leonardo Merlin - leonardo.merlin at unioeste.br
 */
public class EditorActions {

    /**
     *
     * @param e
     * @return Returns the graph for the given action event.
     */
    public static BasicGraphEditor getEditor(ActionEvent e) {
        BasicGraphEditor result = null;
        
        Object sourceEvent = e.getSource();
        if (sourceEvent instanceof Component) {
            Component component = (Component) sourceEvent;
            
            // get the root compoment
            while (component != null
                    && !(component instanceof BasicGraphEditor)) {
                component = component.getParent();
            }
            result = (BasicGraphEditor) component;
        }

        return result;
    }
}

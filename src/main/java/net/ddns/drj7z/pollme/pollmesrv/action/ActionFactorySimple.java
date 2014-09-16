/**
 *
 */
package net.ddns.drj7z.pollme.pollmesrv.action;

import java.util.Map;

import net.ddns.drj7z.pollme.pollmesrv.FactoryException;

/**
 * @author emaschietto
 * @version 1.0.0
 *
 */
public class ActionFactorySimple implements ActionFactory {

  private static class InstanceContainer {
    private static final ActionFactorySimple INSTANCE=
        new ActionFactorySimple();
  }



  private Map<String, Action> actions;



  private ActionFactorySimple ()
  { /* do nothing. */ }




  public static ActionFactorySimple getInstance ()
  {
    return InstanceContainer.INSTANCE;
  }


  public Action createAction (ActionNames actionName)
  {
    String key= actionName.toString();
    if ( getActions().containsKey(key) ) {
      return getActions().get(key);
    }
    // else: error
    throw new FactoryException("cannot find action bean for name '" + key + "'.");
  }


  /**
   * @return the actions
   */
  public Map<String, Action> getActions ()
  {
    return actions;
  }

  /**
   * @param actions the actions to set
   */
  public void setActions (Map<String, Action> actions)
  {
    this.actions= actions;
  }

}

/**
 *
 */
package net.ddns.drj7z.pollme.pollmesrv.action;


/**
 * Abstract class that invokes @see{internalExecute} with actual model type.
 * Subclasses must (should) implement abstract methods:
 * <ul>
 * <li>internalExecute: where real stuff is done.</li>
 * <li>createDataModel: return instance of actual model type.</li>
 * </ul>
 *
 * @author emaschietto
 * @version 1.0.0
 *
 */
public abstract class AbstractAction implements Action {

  public AbstractAction ()
  { /* do nothing. */ }



  /**
   * Where action do actual specific action.
   * Action data must be casted to concrete implementation that should match
   * value returned by {@see getModelClass()}. If that is the scenario then cast can
   * be not checked as check is done by {@see execute(Actiondata)}.
   * @param actionData action data.
   */
  protected abstract boolean  internalExecute (ActionData actionData);

  protected abstract Class<? extends ActionData> getModelClass ();



  public final boolean execute (ActionData actionData) throws ActionException
  {
    try {
      return internalExecute(getModelClass().cast(actionData));
    } catch ( ClassCastException e ) {
      throw new ActionException("'actionData' canno be casted to '" + getModelClass().getName() + "'",e);
    }
  }


}

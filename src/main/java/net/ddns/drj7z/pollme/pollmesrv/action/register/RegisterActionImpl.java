/**
 *
 */
package net.ddns.drj7z.pollme.pollmesrv.action.register;

import net.ddns.drj7z.pollme.pollmesrv.action.AbstractAction;
import net.ddns.drj7z.pollme.pollmesrv.action.ActionData;

/**
 * @author emaschietto
 * @version 1.0.0
 *
 */
public class RegisterActionImpl extends AbstractAction implements RegisterAction {

  private static final class ModelClassHolder {
    public static final Class<RegisterData> MODEL_CLASS= RegisterData.class;
  }


  private RegisterService registerService= null;


  public RegisterActionImpl ()
  { /* do nothing. */ }



  @Override
  protected boolean internalExecute (ActionData actionData)
  {
    RegisterData model= (RegisterData)actionData;
    String password= model.getPassword();
    String name= model.getName();
    String username= model.getUsername();
    System.out.println("action: register; password: " + password +
        ", username: "+ username + ", name: " + name);
    try {
      getRegisterService().doRegister(model);
      return true;
    } catch ( RegisterFailedException e) {
      // TODO manage exception
      return false;
    } catch ( Throwable e ) {
      return false;
    }
  }


  @Override
  protected Class<? extends ActionData> getModelClass ()
  {
    return ModelClassHolder.MODEL_CLASS;
  }


  /**
   * @return the registerService
   */
  public RegisterService getRegisterService ()
  {
    return registerService;
  }

  /**
   * @param registerService the registerService to set
   */
  public void setRegisterService (RegisterService registerService)
  {
    this.registerService= registerService;
  }

}

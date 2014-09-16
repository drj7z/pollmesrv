/**
 *
 */
package net.ddns.drj7z.pollme.pollmesrv.action.register;

/**
 * @author emaschietto
 *
 */
public interface RegisterService {

  public void doRegister (RegisterData registerData)
      throws RegisterFailedException;

}

/**
 *
 */
package net.ddns.drj7z.pollme.pollmesrv.action.register;

import net.ddns.drj7z.pollme.pollmesrv.action.ActionData;


/**
 * @author emaschietto
 * @version 1.0.0
 *
 */
public interface RegisterData extends ActionData {

  public void setName(String name);
  public String getName();


  public void setPublicKey(String publicKey);
  public String getPublicKey();

  public void setEmail(String email);
  public String getEmail();

  public void setTelephoneNumber(String telephoneNumber);
  public String getTelephoneNumber();

  public void setPassword(String password);
  public String getPassword();

  public void setUsername(String username);
  public String getUsername();

}

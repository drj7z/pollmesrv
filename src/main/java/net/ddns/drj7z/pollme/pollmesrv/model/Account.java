/**
 *
 */
package net.ddns.drj7z.pollme.pollmesrv.model;


/**
 * @author emaschietto
 *
 */
public interface Account extends Model {




  /**
   * @return the id
   */
  public String getId ();

  /**
   * @param id the id to set
   */
  public void setId (String id);


  /**
   * @return the username
   */
  public String getUsername ();

  /**
   * @param username the username to set
   */
  public void setUsername (String username);


  /**
   * @return the password
   */
  public String getPassword ();

  /**
   * @param password the password to set
   */
  public void setPassword (String password);


  /**
   * @return the telephoneNumber
   */
  public String getTelephoneNumber ();

  /**
   * @param telephoneNumber the telephoneNumber to set
   */
  public void setTelephoneNumber (String telephoneNumber);


  /**
   * @return the email
   */
  public String getEmail ();

  /**
   * @param email the email to set
   */
  public void setEmail (String email);


  /**
   * @return the publicKey
   */
  public String getPublicKey ();

  /**
   * @param publicKey the publicKey to set
   */
  public void setPublicKey (String publicKey);


  /**
   * @return the name
   */
  public String getName ();

  /**
   * @param name the name to set
   */
  public void setName (String name);

}

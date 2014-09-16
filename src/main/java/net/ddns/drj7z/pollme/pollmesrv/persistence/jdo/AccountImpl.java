/**
 *
 */
package net.ddns.drj7z.pollme.pollmesrv.persistence.jdo;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;

import net.ddns.drj7z.pollme.pollmesrv.model.Account;

/**
 * @author emaschietto
 *
 */
@PersistenceCapable(table="account")
public class AccountImpl implements Account {

  @PrimaryKey
  String id= null;

  String username;
  String password;
  String telephoneNumber;
  String email;
  String publicKey;
  String name;



  /**
   * Do nothing.
   */
  public AccountImpl()
  { /* do nothing. */ }



  /**
   * @return the id
   */
  public String getId ()
  {
    return id;
  }

  /**
   * @param id the id to set
   */
  public void setId (String id)
  {
    this.id= id;
  }


  /**
   * @return the username
   */
  public String getUsername ()
  {
    return username;
  }

  /**
   * @param username the username to set
   */
  public void setUsername (String username)
  {
    this.username= username;
  }


  /**
   * @return the password
   */
  public String getPassword ()
  {
    return password;
  }

  /**
   * @param password the password to set
   */
  public void setPassword (String password)
  {
    this.password= password;
  }


  /**
   * @return the telephoneNumber
   */
  public String getTelephoneNumber ()
  {
    return telephoneNumber;
  }

  /**
   * @param telephoneNumber the telephoneNumber to set
   */
  public void setTelephoneNumber (String telephoneNumber)
  {
    this.telephoneNumber= telephoneNumber;
  }


  /**
   * @return the email
   */
  public String getEmail ()
  {
    return email;
  }

  /**
   * @param email the email to set
   */
  public void setEmail (String email)
  {
    this.email= email;
  }


  /**
   * @return the publicKey
   */
  public String getPublicKey ()
  {
    return publicKey;
  }

  /**
   * @param publicKey the publicKey to set
   */
  public void setPublicKey (String publicKey)
  {
    this.publicKey= publicKey;
  }


  /**
   * @return the name
   */
  public String getName ()
  {
    return name;
  }

  /**
   * @param name the name to set
   */
  public void setName (String name)
  {
    this.name= name;
  }

}

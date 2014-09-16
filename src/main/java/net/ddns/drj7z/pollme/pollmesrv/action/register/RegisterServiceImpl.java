/**
 *
 */
package net.ddns.drj7z.pollme.pollmesrv.action.register;

import java.util.UUID;

import net.ddns.drj7z.pollme.pollmesrv.model.Account;
import net.ddns.drj7z.pollme.pollmesrv.persistence.jdo.AccountImpl;
import net.ddns.drj7z.pollme.pollmesrv.pesrsistence.dao.AccountDao;
import net.ddns.drj7z.pollme.pollmesrv.pesrsistence.exception.InsertException;

import org.springframework.beans.BeanUtils;

/**
 * @author emaschietto
 *
 */
public class RegisterServiceImpl implements RegisterService {

  private AccountDao accountDao= null;

  public RegisterServiceImpl()
  { /* do nothing.  */ }

  // TODO aop
  public void doRegister (RegisterData registerData)
      throws RegisterFailedException
  {
    Account account= null;
    Account check= null;
    try {

      try {
        check= getAccountDao().findByUsername("1");
      } catch ( Exception e ) {
        return;
      }

      // TODO validate
      // TODO factory
      account= new AccountImpl();

      BeanUtils.copyProperties(registerData,account);
      // TODO uuid
      account.setId(UUID.randomUUID().toString());

      getAccountDao().insert(account);

    } catch ( InsertException e) {
      throw new RegisterFailedException("cannot register account '" +
          account + "' concerning registration data '" + registerData +
          "': " + e.getMessage() + ".");
    }

  }


  /**
   * @return the accountDao
   */
  protected AccountDao getAccountDao()
  {
    return accountDao;
  }

  /**
   * @param accountDao the accountDao to set
   */
  public void setAccountDao(AccountDao accountDao)
  {
    this.accountDao= accountDao;
  }

}

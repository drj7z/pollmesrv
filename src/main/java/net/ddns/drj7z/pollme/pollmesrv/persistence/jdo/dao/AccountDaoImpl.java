/**
 *
 */
package net.ddns.drj7z.pollme.pollmesrv.persistence.jdo.dao;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import net.ddns.drj7z.pollme.pollmesrv.model.Account;
import net.ddns.drj7z.pollme.pollmesrv.persistence.jdo.AccountImpl;
import net.ddns.drj7z.pollme.pollmesrv.pesrsistence.dao.AccountDao;
import net.ddns.drj7z.pollme.pollmesrv.pesrsistence.exception.FindException;
import net.ddns.drj7z.pollme.pollmesrv.pesrsistence.exception.InsertException;
import net.ddns.drj7z.pollme.pollmesrv.pesrsistence.exception.NoMatchingItems;
import net.ddns.drj7z.pollme.pollmesrv.support.Injected;

import org.slf4j.Logger;

/**
 * @author emaschietto
 *
 */
public class AccountDaoImpl implements AccountDao {

  @Injected
  Logger logger;

  @Injected
  private PersistenceManagerFactory persistenceManagerFactory= null;



  // TODO aop
  public Account findByUsername (String username) throws FindException,
  NoMatchingItems
  {
    PersistenceManager pm= persistenceManagerFactory.getPersistenceManager();
    // TODO AccountImpl hard-coded
    Query query= pm.newQuery(AccountImpl.class,"username == pUsername");
    query.declareParameters("String pUsername");

    List<Account> resultSet;

    try {
      resultSet= (List<Account>)query.execute(username);
    } catch ( ClassCastException e ) {
      throw new FindException("cannot cast 'query.execute' return value " +
          "to 'List<Account>'",e);
    }

    if ( resultSet.isEmpty() ) {
      throw new NoMatchingItems("there is no account matching '" + username +
          "' username.");
    } else if ( resultSet.size() > 1 ) {
      throw new FindException("very strange: more than one (1) account " +
          "matching '" + username + "' username.");
    } else { // wow: 1 and only 1 account
      return resultSet.get(0);
    }
  }


  // TODO aop
  public void insert(Account account) throws InsertException
  {
    PersistenceManager pm= persistenceManagerFactory.getPersistenceManager();
    try {
      pm.makePersistent(account);
    } catch ( Throwable e ) {
      throw new InsertException("cannot insert account '" + account +
          "' due to an exception: " + e.getMessage(),e);
    }
  }


  /**
   * @return the persistenceManagerFactory
   */
  protected PersistenceManagerFactory getPersistenceManagerFactory ()
  {
    return persistenceManagerFactory;
  }

  public void setPersistenceManagerFactory (PersistenceManagerFactory pmf)
  {
    persistenceManagerFactory= pmf;
  }


  protected PersistenceManager getPersistenceManager ()
  {
    return getPersistenceManagerFactory().getPersistenceManager();
  }

}

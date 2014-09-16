/**
 *
 */
package net.ddns.drj7z.pollme.pollmesrv.pesrsistence.dao;

import net.ddns.drj7z.pollme.pollmesrv.model.Account;
import net.ddns.drj7z.pollme.pollmesrv.pesrsistence.exception.FindException;
import net.ddns.drj7z.pollme.pollmesrv.pesrsistence.exception.InsertException;
import net.ddns.drj7z.pollme.pollmesrv.pesrsistence.exception.NoMatchingItems;

/**
 * @author emaschietto
 *
 */
public interface AccountDao {

  /**
   * Return the unique account concerning the specified username.
   * @param username username to search for.
   * @return the matching account - if exists.
   * @throws FindException if error(s) occur(s) while finding.
   * @throws NoMatchingItems if no account concerns the specified username.
   */
  public Account findByUsername (String username) throws FindException, NoMatchingItems;

  /**
   * Insert (add) a new account.
   * @param account account to insert (add).
   * @throws InsertException if error(s) occur(s) while inserting.
   */
  public void insert (Account account) throws InsertException;

}

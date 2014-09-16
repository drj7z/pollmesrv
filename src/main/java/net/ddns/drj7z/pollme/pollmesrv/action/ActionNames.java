/**
 *
 */
package net.ddns.drj7z.pollme.pollmesrv.action;

/**
 * Names of valid actions.
 *
 * @author emaschietto
 * @version 1.0.0
 *
 */
public enum ActionNames {
  register, // register (create) a new account
  login, // login
  logout, // logout
  closeAccount, // close an account
  createPoll, // create a new poll
  editPoll, // edit an existent poll
  deletePoll, // delete a poll
  vote, // give a vote for a poll
  // changeVote, // change a vote  for the poll
  // updateMyPolls, // get fresh info concerning my polls
  // updateActivePolls // get fresh info concerning active polls
}

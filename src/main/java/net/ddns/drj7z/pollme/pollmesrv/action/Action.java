/**
 *
 */
package net.ddns.drj7z.pollme.pollmesrv.action;


/**
 * @author emaschietto
 *
 */
public interface Action {

  public boolean execute (ActionData actionData) throws ActionException;

}

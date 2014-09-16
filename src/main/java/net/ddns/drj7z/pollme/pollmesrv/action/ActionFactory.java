/**
 *
 */
package net.ddns.drj7z.pollme.pollmesrv.action;


/**
 * @author emaschietto
 *
 */
public interface ActionFactory {

  public Action createAction (ActionNames name);

}

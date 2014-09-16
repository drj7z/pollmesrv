/**
 *
 */
package net.ddns.drj7z.pollme.pollmesrv.communication.json;

/**
 * Json encoder(decoder) adapter interface.
 *
 * @author emaschietto
 * @version 1.0.0
 *
 */
public interface JsonEncoder{

  public String encode (Object object) throws JsonException;
  public Object decode (String json, Class<?> objectClass) throws JsonException;

}

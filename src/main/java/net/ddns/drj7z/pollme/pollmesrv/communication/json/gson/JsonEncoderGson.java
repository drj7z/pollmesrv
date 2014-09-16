package net.ddns.drj7z.pollme.pollmesrv.communication.json.gson;

import net.ddns.drj7z.pollme.pollmesrv.communication.json.JsonEncoder;
import net.ddns.drj7z.pollme.pollmesrv.communication.json.JsonException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

public class JsonEncoderGson implements JsonEncoder {

  public static final class EncoderHolder {
    public static final Gson ENCODER= new GsonBuilder().
        // TODO remove comments in production.
        //registerTypeAdapter(ActionData.class,new GsonPacketAdapter()).
        //registerTypeHierarchyAdapter(Packet.class,new GsonPacketAdapter()).
        registerTypeAdapterFactory(new GsonTypeAdapterFactory()).
        //setFieldNamingStrategy(new GsonFieldNamingPolicy()).
        create();
  }

  private static Gson getEncoder ()
  {
    return EncoderHolder.ENCODER;
  }



  public String encode (Object object) throws JsonException
  {
    try {
      String encoded= JsonEncoderGson.getEncoder().toJson(object);
      return encoded;
    } catch ( JsonSyntaxException e ) {
      throw new JsonException("something went wrong while encoding object '" +
          object.getClass().getName() + "' to json",e);
    }
  }

  public Object decode (String json, Class<?> objectClass) throws JsonException
  {
    try {
      Object object= JsonEncoderGson.getEncoder().fromJson(json,objectClass);
      return object;
    } catch ( JsonSyntaxException e ) {
      throw new JsonException("something went wrong while decoding json '" +
          json + "' to class '" + objectClass.getName() + "'",e);
    }
  }

}

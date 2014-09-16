package net.ddns.drj7z.pollme.pollmesrv.support.springframework;

import java.lang.reflect.Field;

import net.ddns.drj7z.pollme.pollmesrv.support.WireLogger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.ReflectionUtils.FieldCallback;

/**
 * Auto injects the underlying implementation of logger into the bean with field
 * having annotation <code>Logger</code>.
 *
 */
public class LoggerInjector implements BeanPostProcessor {

  public Object postProcessBeforeInitialization (final Object bean,
      String beanName) throws BeansException
  {
    ReflectionUtils.doWithFields(bean.getClass(), new FieldCallback() {
      public void doWith(Field field) throws IllegalArgumentException,
      IllegalAccessException
      {
        // make the field accessible if defined private
        ReflectionUtils.makeAccessible(field);
        if (field.getAnnotation(WireLogger.class) != null) {
          Logger logger= LoggerFactory.getLogger(bean.getClass());
          field.set(bean,logger);
        }
      }
    });
    return bean;
  }


  public Object postProcessAfterInitialization(Object bean, String beanName)
      throws BeansException
  {
    return bean;
  }

}

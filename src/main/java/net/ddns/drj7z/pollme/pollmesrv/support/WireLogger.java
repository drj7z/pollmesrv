/**
 *
 */
package net.ddns.drj7z.pollme.pollmesrv.support;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Indicates that logger must be inject at runtime to annotated field.
 *
 * @author emaschietto
 *
 */
@Retention(RUNTIME)
@Target(FIELD)
@Documented
public @interface WireLogger {}

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
 * @author emaschietto
 *
 */
@Retention(RUNTIME)
@Target(FIELD)
@Documented
public @interface Injected {}

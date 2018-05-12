package cn.aegisa.project.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Using IntelliJ IDEA.
 *
 * @author HNAyd.xian at 2018/3/23 10:39
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface SystemLog {
    String message();

    int type();
}

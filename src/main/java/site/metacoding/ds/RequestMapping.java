package site.metacoding.ds;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD}) // 메서드, 클래스,  필드(변수) 타겟 지정 가능 
@Retention(RetentionPolicy.RUNTIME) // RUNTIME(런타임시 동작), SOURCE(컴파일시 동작)
public @interface RequestMapping {
	String value(); // value 메서드 고정, 키값 
}


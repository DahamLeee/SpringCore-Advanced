package hello.proxy.jdkdynamic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Slf4j
public class ReflectionTest {

    @Test
    void reflection0() {
        Hello target = new Hello();
        
        // 공통 로직1 시작
        log.info("start");
        String result1 = target.callA(); // 호출하는 메서드가 다름
        log.info("result={}", result1);
        // 공통 로직1 종료

        log.info("start");
        String result2 = target.callB(); // 호출하는 메서드가 다름
        log.info("result={}", result2);
    }

    @Test
    void reflection1() throws Exception {
        // 클래스 정보
        Class classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");

        Hello target = new Hello();
        // callA 메서드 정보
        Method methodCallA = classHello.getMethod("callA"); // 문자로 Method 를 얻었다..!
        Object result1 = methodCallA.invoke(target);
        log.info("result1={}", result1);

        // callA 메서드 정보
        Method methodCallB = classHello.getMethod("callB"); // 문자로 Method 를 얻었다..!
        Object result2 = methodCallB.invoke(target);
        log.info("result2={}", result2);
    }

    @Test
    public void reflection2() throws Exception {
        // 클래스 정보
        Class classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");


        Hello target = new Hello();
        // callA 메서드 정보
        Method methodCallA = classHello.getMethod("callA"); // 문자로 Method 를 얻었다..!
        dynamicCall(methodCallA, target);

        // callA 메서드 정보
        Method methodCallB = classHello.getMethod("callB"); // 문자로 Method 를 얻었다..!
        dynamicCall(methodCallB, target);
    }

    private void dynamicCall(Method method, Object target) throws Exception {
        // 공통 로직1 시작
        log.info("start");
        Object result = method.invoke(target);
        log.info("result={}", result);
        // 공통 로직1 종료
    }

    @Slf4j
    static class Hello {
        public String callA() {
            log.info("callA");
            return "A";
        }
        
        public String callB() {
            log.info("callB");
            return "B";
        }
    }

}
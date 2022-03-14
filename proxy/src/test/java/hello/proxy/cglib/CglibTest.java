package hello.proxy.cglib;

import hello.proxy.cglib.code.TimeMethodInterceptor;
import hello.proxy.common.service.ConcreteService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.cglib.proxy.Enhancer;

@Slf4j
public class CglibTest {

    @Test
    public void cglib() throws Exception {
        ConcreteService target = new ConcreteService(); // 동적 Proxy 를 인터페이스 없이 사용하는 예제임

        Enhancer enhancer = new Enhancer(); // cglib 를 만드는 코드임.
        enhancer.setSuperclass(ConcreteService.class); // 이거를 상속받아서 만들게 됨
        enhancer.setCallback(new TimeMethodInterceptor(target));
        ConcreteService proxy = (ConcreteService) enhancer.create();// proxy 생성

        log.info("targetClass={}", target.getClass());
        log.info("proxyClass={}", proxy.getClass());

        proxy.call();
    }
}

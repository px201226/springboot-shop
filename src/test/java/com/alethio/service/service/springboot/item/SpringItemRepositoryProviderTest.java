package com.alethio.service.service.springboot.item;

import com.alethio.service.service.domain.item.IItemRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@DisplayName("[스프링] SpringItemRepositoryProviderTest")
class SpringItemRepositoryProviderTest {

    @Autowired
    private SpringItemRepositoryProvider springItemRepositoryProvider;


    // 테스트가 실패한다면 ItemRepository 구현체가 레포지토리맵 테이블에 등록이 안된 것!
    @Test
    @DisplayName("Repository 테이블의 사이즈와 런타임 구현체 갯수가 같은지 검증")
    public void 레포지토리_테이블의_사이즈는_ItemRepository_구현체의_갯수와_같다(){

        ApplicationContext applicationContext = ApplicationContextProvider.getApplicationContext();
        String[] beanNamesForType = applicationContext.getBeanNamesForType(IItemRepository.class);

        int staticRegisteredCount = beanNamesForType.length;
        int runtimeRegisteredCount = springItemRepositoryProvider.getRegisteredItemRepository();

        assertEquals(staticRegisteredCount,runtimeRegisteredCount);
    }
}
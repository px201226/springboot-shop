package com.alethio.service.service.springboot.item;

import com.alethio.service.service.domain.common.ItemStatusDTO;
import com.alethio.service.service.domain.common.ItemType;
import com.alethio.service.service.domain.exception.business.OutOfStockQuantityException;
import com.alethio.service.service.domain.item.*;
import com.alethio.service.service.domain.item.itemtype.Food;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@DisplayName("[스프링] ItemService 테스트")
@SpringBootTest
class ItemServiceImplTest {

    @Autowired
    private IItemService itemService;

    @Autowired
    private ItemRepositoryProvider itemRepositoryProvider;

    private static final ExecutorService executorService = Executors.newFixedThreadPool(100);

    @Nested
    @DisplayName("동시성 테스트")
    class Concurrency {

        @Test
        @DisplayName("재고가 수량만큼 증가되는지 검증")
        public void 아이템_재고가_주문수량만큼_증가된다() throws InterruptedException {

            //given
            int repeat = 100;
            Long availableQty = 0L;
            Long addQty = 1L;
            Long expectQty = 100L;
            Food food = saveStubFoodEntity("라면", availableQty, availableQty, 100L);

            //when
            CountDownLatch latch = new CountDownLatch(repeat);

            for (int i = 0; i < repeat; i++) {
                executorService.execute(() -> {
                    itemService.addAvailableStock(food.getItemType(), food.getId(), addQty);
                    latch.countDown();
                });
            }

            latch.await();

            ItemStatusDTO itemStatus = itemService.getItemStatus(food.getItemType(), food.getId());
            Long actualQty = itemStatus.getAvailableStockQuantity();

            //then
            assertEquals(expectQty, actualQty);
        }

        @Test
        @DisplayName("재고가 수량만큼 감소되는지 검증")
        public void 아이템_재고가_주문수량만큼_감소된다() throws InterruptedException {

            //given
            int repeat = 100;
            Long availableQty = 100L;
            Long removeQty = 1L;
            Long expectQty = 0L;
            Food food = saveStubFoodEntity("라면", availableQty, availableQty, 100L);

            //when
            CountDownLatch latch = new CountDownLatch(repeat);

            for (int i = 0; i < repeat; i++) {
                executorService.execute(() -> {
                    itemService.removeAvailableStock(food.getItemType(), food.getId(), removeQty);
                    latch.countDown();
                });
            }

            latch.await();

            ItemStatusDTO itemStatus = itemService.getItemStatus(food.getItemType(), food.getId());
            Long actualQty = itemStatus.getAvailableStockQuantity();

            //then
            assertEquals(expectQty, actualQty);
        }

    }


    @Nested
    @DisplayName("싱글 스레드 테스트")
    class SingleThread {

        @Test
        @DisplayName("재고가 수량만큼 증가되는지 검증")
        public void 아이템_재고가_주문수량만큼_증가된다() {

            //given
            Long availableQty = 0L;
            Long addQty = 20L;
            Long expectQty = 20L;
            Food food = saveStubFoodEntity("라면", availableQty, availableQty, 100L);

            //when
            ItemStatusDTO itemStatusDTO = itemService.addAvailableStock(food.getItemType(), food.getId(), addQty);
            Long actualQty = itemStatusDTO.getAvailableStockQuantity();

            //then
            assertEquals(expectQty, actualQty);
        }

        @Test
        @DisplayName("재고가 수량만큼 감소되는지 검증")
        public void 아이템_재고가_주문수량만큼_줄어든다() {

            //given
            Long availableQty = 10L;
            Long removeQty = 10L;
            Long expectQty = 0L;
            Food food = saveStubFoodEntity("라면", availableQty, availableQty, 100L);

            //when
            ItemStatusDTO itemStatusDTO = itemService.removeAvailableStock(food.getItemType(), food.getId(), removeQty);
            Long actualQty = itemStatusDTO.getAvailableStockQuantity();

            //then
            assertEquals(expectQty, actualQty);
        }

        @Test
        @DisplayName("재고가 부족하면 예외를 일으키는지 검증")
        public void 아이템_재고가_부족하면_OutOfStockQuantityException이_발생한다() {

            //given
            Long availableQty = 10L;
            Long removeQty = 11L;
            Food food = saveStubFoodEntity("라면", availableQty, availableQty, 100L);

            //when & then
            assertThrows(OutOfStockQuantityException.class, () ->
                    itemService.removeAvailableStock(food.getItemType(), food.getId(), removeQty)
            );
        }
    }

    private Food saveStubFoodEntity(String name, Long quantity, Long threshold, Long requestQuantity) {
        Food food = Food.builder()
                .name(name)
                .availableStockQuantity(quantity)
                .requestStockThreshold(threshold)
                .requestStockQuantity(requestQuantity)
                .vendor(Vendor.AMADON)
                .build();

        IItemRepository itemRepository = itemRepositoryProvider.getRepositoryByItemType(ItemType.FOOD);
        ItemEntity foodEntity = itemRepository.save(food);

        return (Food) foodEntity;
    }
}
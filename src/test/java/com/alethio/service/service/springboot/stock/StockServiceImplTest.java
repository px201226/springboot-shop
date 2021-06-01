package com.alethio.service.service.springboot.stock;

import com.alethio.service.service.domain.common.ItemStatusDTO;
import com.alethio.service.service.domain.common.ItemType;
import com.alethio.service.service.domain.exception.business.OutOfStockQuantityException;
import com.alethio.service.service.domain.item.IItemRepository;
import com.alethio.service.service.domain.item.ItemEntity;
import com.alethio.service.service.domain.item.ItemRepositoryProvider;
import com.alethio.service.service.domain.item.Vendor;
import com.alethio.service.service.domain.item.itemtype.Food;
import com.alethio.service.service.domain.stock.IStockService;
import com.alethio.service.service.domain.stock.request.IReceivingRequestRepository;
import com.alethio.service.service.domain.stock.request.ReceivingRequestEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("[스프링] StockService 테스트")
@SpringBootTest
class StockServiceImplTest {

    private static final ExecutorService executorService = Executors.newFixedThreadPool(100);

    @Autowired
    private IReceivingRequestRepository receivingRequestRepository;

    @Autowired
    private IStockService stockService;

    @Autowired
    private ItemRepositoryProvider itemRepositoryProvider;

    @Nested
    @DisplayName("동시성 테스트")
    class ConcurrencyTest {

        @Test
        @DisplayName("여러 사용자가 동시에 같은 아이템 재고를 증가시킬 때 경쟁 상태를 방지하는지 검증")
        public void 동시에_100개의_재고추가_메시지를_받으면_재고가_100_증가한다() throws InterruptedException {

            //given
            int repeat = 100;
            Long addQuantity = 1L;
            Long expectQty = 200L;

            Food food = saveStubFoodEntity("김부각", 100L, 10L, 50L);

            //when
            CountDownLatch latch = new CountDownLatch(repeat);

            for (int i = 0; i < repeat; i++) {
                executorService.execute(() -> {
                    stockService.addAvailableStock(food.getItemType(), food.getId(), addQuantity);
                    latch.countDown();
                });
            }

            latch.await();

            Long actualQty = stockService.getItemStatus(food.getItemType(),food.getId()).getAvailableStockQuantity();

            //then
            assertEquals(expectQty, actualQty);
        }

        @Test
        @DisplayName("여러 사용자가 동시에 같은 아이템 재고를 감소시킬 때 경쟁 상태를 방지하는지 검증")
        public void 동시에_100개의_재고추가_메시지를_받으면_재고가_100_감소한다() throws InterruptedException {

            //given
            int repeat = 100;
            Long removeQuantity = 1L;
            Long expectQty = 0L;

            Food food = saveStubFoodEntity("김부각", 100L, 10L, 50L);

            //when
            CountDownLatch latch = new CountDownLatch(repeat);

            for (int i = 0; i < repeat; i++) {
                executorService.execute(() -> {
                    stockService.placeOrder(food.getItemType(), food.getId(), removeQuantity);
                    latch.countDown();
                });
            }

            latch.await();
            Long actualQty = stockService.getItemStatus(food.getItemType(),food.getId()).getAvailableStockQuantity();

            //then
            assertEquals(expectQty, actualQty);
        }

        @Test
        @DisplayName("입고 요청이 중복해서 저장되지 않는지 검증")
        public void 여러_사용자가_주문을_하더라도_입고_요청이_중복되지_않는다() throws InterruptedException {

            //given
            int repeat = 100;

            Food food = saveStubFoodEntity("치킨", 100L, 100L, 50L);
            receivingRequestRepository.deleteAll();

            //when
            CountDownLatch latch = new CountDownLatch(repeat);

            for (int i = 0; i < repeat; i++) {
                executorService.execute(() -> {
                    stockService.placeOrder(food.getItemType(), food.getId(), 1L);
                    latch.countDown();
                });
            }

            latch.await();

            //then
            List<ReceivingRequestEntity> all = receivingRequestRepository.findAll();
            all.forEach(a-> System.out.println(a));
            assertEquals(1, all.size());
        }
    }

    @Nested
    @DisplayName("싱글스레드 테스트")
    class SingleThreadTest {

        @Test
        @DisplayName("아이템 재고 추가 메시지를 받으면 아이템 재고가 증가되는지 검증")
        public void 재고추가_시_재고가_증가한다() throws InterruptedException {

            //given
            Long availableQty = 100L;
            Long addQty = 1L;
            Long expectQty = 101L;

            Food food = saveStubFoodEntity("김부각", availableQty, 10L, 50L);

            //when
            ItemStatusDTO itemStatusDTO = stockService.addAvailableStock(food.getItemType(), food.getId(), addQty);

            //then
            assertEquals(expectQty, itemStatusDTO.getAvailableStockQuantity());
        }

        @Test
        @DisplayName("아이템 주문 메시지를 받으면 아이템 재고가 줄어드는지 검증")
        public void 아이템_주문을_받으먼_재고가_줄어든다() throws InterruptedException {

            //given
            Long availableQty = 100L;
            Long removeQty = 1L;
            Long expectQty = 99L;

            Food food = saveStubFoodEntity("김부각", availableQty, 10L, 50L);

            //when
            ItemStatusDTO itemStatusDTO = stockService.placeOrder(food.getItemType(), food.getId(), removeQty);

            //then
            assertEquals(expectQty, itemStatusDTO.getAvailableStockQuantity());
        }

        @Test
        @DisplayName("상품 주문시 재고가 임계값보다 작으면 입고 요청을 하는지 검증")
        public void 아이템_주문_시_재고가_임계값보다_작으면_입고_요청_데이터를_저장한다() throws InterruptedException {

            //given
            Long availableQty = 100L;
            Long removeQty = 1L;
            Long threshold = 100L;
            Long requestQty = 1000L;

            Food food = saveStubFoodEntity("치킨", availableQty, threshold, requestQty);
            receivingRequestRepository.deleteAll();

            //when
            stockService.placeOrder(food.getItemType(), food.getId(), removeQty);

            //then
            ReceivingRequestEntity receivingRequestEntity = receivingRequestRepository.findAll().stream().findFirst().get();
            assertEquals(receivingRequestEntity.getItemType(), ItemType.FOOD);
            assertEquals(receivingRequestEntity.getEncryptKey(), food.getVendor().encryptKey(food.getName()));
            assertEquals(receivingRequestEntity.getRequestStockQuantity(), requestQty);
        }

        @Test
        @DisplayName("상품 주문시 재고가 부족하면 예외가 발생하는지 검증")
        public void 아이템_주문_시_재고가_부족하면_예외가_발생한다() throws InterruptedException {

            //given
            Long availableQty = 10L;
            Long removeQty = 11L;

            Food food = saveStubFoodEntity("치킨", availableQty, 100L, 100L);

            //when && then
            assertThrows(OutOfStockQuantityException.class, () ->
                    stockService.placeOrder(food.getItemType(), food.getId(), removeQty)
            );
        }
    }

    private Food saveStubFoodEntity(String name, Long quantity, Long threshold, Long requestQuantity){
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
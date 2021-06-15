package com.alethio.service.service.springboot.order.controller;

import com.alethio.service.service.domain.common.ItemType;
import com.alethio.service.service.domain.item.IItemRepository;
import com.alethio.service.service.domain.item.ItemEntity;
import com.alethio.service.service.domain.item.ItemRepositoryProvider;
import com.alethio.service.service.domain.item.Vendor;
import com.alethio.service.service.domain.item.itemtype.Food;
import com.alethio.service.service.domain.order.PlaceOrderRequestDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@DisplayName("[스프링] OrderController 테스트")
@SpringBootTest
@AutoConfigureMockMvc
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ItemRepositoryProvider itemRepositoryProvider;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    @DisplayName("상품 주문 성공 시 주문내역을 응답값으로 받는지 검증")
    public void 주문요청_시_주문내역을_응답값으로_받는다() throws Exception {

        //given
        Food food = saveStubFoodEntity("치킨", 1L, 100L, 100L);
        PlaceOrderRequestDTO orderRequestDTO = buildPlaceOrderRequestDTO(food.getItemType(),food.getId());

        //when & then
        mockMvc.perform(post("/order")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(orderRequestDTO))
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.items.id").value(food.getId()))
                .andExpect(jsonPath("$.items.itemType").value(food.getItemType().getName()));

    }

    @Test
    @DisplayName("상품 주문 시 재고가 부족하면 서버에러를 메시지를 받는지 검증")
    public void 상품_주문_시_재고가_부족하면_에러코드_102_를받는다() throws Exception {

        //given
        Food food = saveStubFoodEntity("치킨", 0L, 100L, 100L);
        PlaceOrderRequestDTO orderRequestDTO = buildPlaceOrderRequestDTO(food.getItemType(),food.getId());

        //when & then
        mockMvc.perform(post("/order")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(orderRequestDTO))
        )
                .andDo(print())
                .andExpect(status().is5xxServerError())
                .andExpect(jsonPath("$.errorType.errorCode").value(-102));

    }

    @Test
    @DisplayName("상품 주문 시 잘못된 파라미터값을 전달하면 서버에러를 메시지를 받는지 검증")
    public void 상품_주문_요청시_입력이_잘못되면_에러코드_103_를받는다() throws Exception {

        //given
        Food food = saveStubFoodEntity("치킨", 0L, 100L, 100L);
        PlaceOrderRequestDTO orderRequestDTO = buildUnvalidPlaceOrderRequestDTO(food.getItemType(),food.getId());

        //when & then
        mockMvc.perform(post("/order")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(orderRequestDTO))
        )
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.errorType.errorCode").value(-103));

    }

    @Test
    @DisplayName("존재하지 않은 상품을 주문하면 서버에러를 메시지를 받는지 검증")
    public void 존재하지_않은_상품을_주문하면_에러코드_101_를받는다() throws Exception {

        //given
        Long notExistItemId = 987L;
        PlaceOrderRequestDTO orderRequestDTO = buildPlaceOrderRequestDTO(ItemType.FOOD,notExistItemId);

        //when & then
        mockMvc.perform(post("/order")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(orderRequestDTO))
        )
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.errorType.errorCode").value(-101));

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

    private PlaceOrderRequestDTO buildPlaceOrderRequestDTO(ItemType itemType, Long itemId){
        return PlaceOrderRequestDTO.builder()
                .itemType(itemType)
                .itemId(itemId)
                .contactName("Hong")
                .contactEmail("test@test.com")
                .mobile("01011111111")
                .build();
    }

    private PlaceOrderRequestDTO buildUnvalidPlaceOrderRequestDTO(ItemType itemType, Long itemId){
        return PlaceOrderRequestDTO.builder()
                .itemType(null)
                .itemId(0L)
                .contactName("Hong")
                .contactEmail("test@test.com")
                .mobile("01011111111")
                .build();
    }
}
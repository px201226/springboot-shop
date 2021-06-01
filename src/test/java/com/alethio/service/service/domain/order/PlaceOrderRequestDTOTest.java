package com.alethio.service.service.domain.order;

import com.alethio.service.service.domain.common.ItemType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


@DisplayName("[도메인] PlaceOrderRequestDTO 테스트")
class PlaceOrderRequestDTOTest {

    @Test
    @DisplayName("파라미터 입력 문자열에 양끝 공백을 제거하는지 검증")
    public void 사용자_입력_문자열에_양끝_공백을_제거한다(){

        //given
        OrderEntity requestDto = PlaceOrderRequestDTO.builder()
                .contactEmail("  앞 공백이 있는 이메일")
                .contactName("뒷 공백이 있는 이름    ")
                .mobile("  양쪽 공백이 있는 연락처   ")
                .itemId(1L)
                .itemType(ItemType.FOOD)
                .build()
                .toEntity();

        //when && then
        assertEquals("앞 공백이 있는 이메일", requestDto.getContactEmail());
        assertEquals("뒷 공백이 있는 이름", requestDto.getContactName());
        assertEquals("양쪽 공백이 있는 연락처", requestDto.getMobile());

    }

}
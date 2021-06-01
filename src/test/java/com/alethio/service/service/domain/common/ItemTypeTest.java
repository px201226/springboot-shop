package com.alethio.service.service.domain.common;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("[도메인] ItemType 테스트")
class ItemTypeTest {

    @Test
    @DisplayName("String 문자열에서 Enum ItemType으로 변환할 수 있는지 검증")
    public void String_타입에서_ItemType_타입으로_변환할_수_있다(){
        // given
        String enumString1 = "food";
        String enumString2 = "clothes";

        // when & then
        assertEquals(ItemType.FOOD, ItemType.fromString(enumString1));
        assertEquals(ItemType.CLOTHES, ItemType.fromString(enumString2));
    }

    @Test
    @DisplayName("ItemType에 해당하지 않은 문자열을 변환하면 null을 반환하는지 검증")
    public void 문자열이_ItemType에_해당되지_않으면_null_이다(){
        // given
        String enumString1 = "Google";

        // when & then
        assertEquals(null, ItemType.fromString(enumString1));
    }

}
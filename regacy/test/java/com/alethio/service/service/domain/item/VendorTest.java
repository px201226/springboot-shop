package com.alethio.service.service.domain.item;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DisplayName("[도메인] Vendor 테스트")
class VendorTest {

    @Test
    @DisplayName("String 문자열에서 Enum Vendor로 변환할 수 있는지 검증")
    public void String_타입에서_Vendor_타입으로_변환할_수_있다(){
        // given
        String enumString1 = "Coumang";
        String enumString2 = "Amadon";

        // when & then
        assertEquals(Vendor.COUMANG, Vendor.fromString(enumString1));
        assertEquals(Vendor.AMADON, Vendor.fromString(enumString2));
    }

    @Test
    @DisplayName("Vendor Type에 해당하지 않은 문자열을 변환하면 null을 반환하는지 검증")
    public void 문자열이_Vendor에_해당되지_않으면_null_이다(){
        // given
        String enumString1 = "Google";

        // when & then
        assertEquals(null, Vendor.fromString(enumString1));
    }

    @Test
    @DisplayName("Amadon Vendor의 암호화가 정상적으로 작동하는지 검증")
    public void Amadon_암호화는_키값에_123_이_뒤에_붙는다(){
        // given
        String key = "Google";
        String expectEncrypt = "Google123";

        // when & then
        assertEquals(expectEncrypt, Vendor.AMADON.encryptKey(key));
    }

    @Test
    @DisplayName("Coumang Vendor의 암호화가 정상적으로 작동하는지 검증")
    public void Coumang_암호화는_키값에_123_이_앞에_붙는다(){
        // given
        String key = "Google";
        String expectEncrypt = "123Google";

        // when & then
        assertEquals(expectEncrypt, Vendor.COUMANG.encryptKey(key));
    }
}
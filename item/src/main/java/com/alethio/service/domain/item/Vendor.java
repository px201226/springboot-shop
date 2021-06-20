package com.alethio.service.domain.item;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Getter
public enum Vendor {

    AMADON("Amadon",(key)-> key + "123"),
    COUMANG("Coumang", (key) -> "123" + key);

    private static Map<String, Vendor> enumMap;
    private Function<Object, String> encrypt;
    private String name;

    Vendor(String name, Function<Object, String> encrypt) {
        this.name = name;
        this.encrypt = encrypt;
    }

    @JsonCreator
    public static Vendor fromString(String str){
        if (enumMap == null) {
            initializeMap();
        }
        return enumMap.get(str.toUpperCase());
    }

    private static Map<String, Vendor> initializeMap() {
        enumMap = new HashMap<>();
        for (Vendor vendor : Vendor.values()) {
            enumMap.put(vendor.toString(), vendor);
        }

        return enumMap;
    }

    public String encryptKey(Object key) {
        return encrypt.apply(key);
    }

}

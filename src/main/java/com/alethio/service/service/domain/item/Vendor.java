package com.alethio.service.service.domain.item;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public enum Vendor {

    AMADON("Amadon"){
        @Override
        public String encryptKey(Object key) {
            return key + "123";
        }
    },

    COUMANG("Coumang"){
        @Override
        public String encryptKey(Object key) {
            return "123" + key;
        }
    };

    public abstract String encryptKey(Object key);
    private static final Map<String, Vendor> stringToVendor = new HashMap<String, Vendor>();
    private final String name;

    static {
        for (Vendor vendor : values())
            stringToVendor.put(vendor.toString(), vendor);
    }

    Vendor(String name) {
        this.name = name;
    }

    public static Vendor fromString(String symbol) {
        return stringToVendor.get(symbol);
    }
}

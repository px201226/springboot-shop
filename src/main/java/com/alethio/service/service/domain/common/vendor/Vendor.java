package com.alethio.service.service.domain.common.vendor;

import java.util.HashMap;
import java.util.Map;

public enum Vendor {

    AMADON("Amadon"){
        @Override
        String encryptKey(Object key) {
            return key + "123";
        }
    },

    COUMANG("Coumang"){
        @Override
        String encryptKey(Object key) {
            return "123" + key;
        }
    };

    private final String vendorName;
    abstract String encryptKey(Object key);

    Vendor(String vendorName) {
        this.vendorName = vendorName;
    }

    private static final Map<String, Vendor> stringToVendor = new HashMap<String, Vendor>();
    static {
        for (Vendor vendor : values())
            stringToVendor.put(vendor.toString(), vendor);
    }

    public static Vendor fromString(String symbol) {
        return stringToVendor.get(symbol);
    }
}

package stock.domain.common;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public enum  ItemType {

    CLOTHES("clothes"), FOOD("food");

    private static Map<String, ItemType> enumMap;
    private String name;

    ItemType(String name) {
        this.name = name;
    }

    @JsonCreator
    public static ItemType fromString(String str){
        if (enumMap == null) {
            initializeMap();
        }
        return enumMap.get(str.toUpperCase());
    }

    private static Map<String, ItemType> initializeMap() {
        enumMap = new HashMap<>();
        for (ItemType itemType : ItemType.values()) {
            enumMap.put(itemType.toString(), itemType);
        }

        return enumMap;
    }

}

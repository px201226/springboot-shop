package com.alethio.service.service.domain.common;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

@Getter
public enum  ItemType {

    CLOTHES("clothes"), FOOD("food");

    private static final Map<String, ItemType> stringToEnum =
            Stream.of(values()).collect(toMap(Objects::toString, e -> e));

    private String name;

    ItemType(String name) {
        this.name = name;
    }

    @JsonCreator
    public static ItemType fromText(String text){
        return stringToEnum.get(text.toUpperCase());
    }

}

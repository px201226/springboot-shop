package com.alethio.service.service.domain.item;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

public enum  ItemType {

    CLOTHES, FOOD;

    private static final Map<String, ItemType> stringToEnum =
            Stream.of(values()).collect(toMap(Objects::toString, e -> e));

    @JsonCreator
    public static ItemType fromText(String text){
        return stringToEnum.get(text.toUpperCase());
    }

}

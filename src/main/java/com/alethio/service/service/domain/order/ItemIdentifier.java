package com.alethio.service.service.domain.order;


import com.alethio.service.service.domain.item.ItemType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ItemIdentifier {

    @NotNull
    @Enumerated(EnumType.STRING)
    private ItemType itemType;

    @Positive
    @JsonProperty("id")
    private Long itemId;
}

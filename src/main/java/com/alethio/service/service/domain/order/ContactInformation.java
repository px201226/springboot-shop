package com.alethio.service.service.domain.order;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Entity;

@Embeddable
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactInformation {
    private String contactEmail;
    private String contactName;
    private String mobile;
}

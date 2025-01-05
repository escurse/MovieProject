package com.escass.movieproject.vos.ticket;

import com.escass.movieproject.entities.ticket.PaymentEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentVo extends PaymentEntity {
    private int scNum;
}

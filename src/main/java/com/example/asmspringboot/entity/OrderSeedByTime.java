package com.example.asmspringboot.entity;

import com.example.asmspringboot.entity.entityEnum.OrderSeedByType;
import com.example.asmspringboot.entity.entityEnum.OrderStatus;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderSeedByTime {
    private OrderSeedByType seedByType;
    private int year;
    private int month;
    private int day;
    private int countOrder;
    private OrderStatus orderStatus;
}

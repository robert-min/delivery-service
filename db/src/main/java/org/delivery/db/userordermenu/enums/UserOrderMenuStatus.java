package org.delivery.db.userordermenu.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum UserOrderMenuStatus {
    REGISTERED("등록"),
    UNREGISTERED("해지"),

    ;

//    UserOrderStatus(String description) {
//        this.description = description;
//    }

    private String description;
}

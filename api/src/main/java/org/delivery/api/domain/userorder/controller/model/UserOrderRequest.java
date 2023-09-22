package org.delivery.api.domain.userorder.controller.model;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class UserOrderRequest {

    @NotNull
    private Long storeId;

    // 주문
    @NotNull
    private List<Long> storeMenuIdList;

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public List<Long> getStoreMenuIdList() {
        return storeMenuIdList;
    }

    public void setStoreMenuIdList(List<Long> storeMenuIdList) {
        this.storeMenuIdList = storeMenuIdList;
    }
}

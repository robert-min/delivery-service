package org.delivery.storeadmin.domain.user.business;

import lombok.RequiredArgsConstructor;
import org.delivery.db.store.StoreRepository;
import org.delivery.db.store.enums.StoreStatus;
import org.delivery.storeadmin.domain.user.controller.model.StoreUserRegisterRequest;
import org.delivery.storeadmin.domain.user.controller.model.StoreUserResponse;
import org.delivery.storeadmin.domain.user.converter.StoreUserConverter;
import org.delivery.storeadmin.domain.user.service.StoreUserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StoreUserBusiness {

    private final StoreUserConverter storeUserConverter;
    private final StoreUserService storeUserService;
    private final StoreRepository storeRepository; // TODO : Service로 변경

    public StoreUserResponse register(
            StoreUserRegisterRequest request
    ) {
        var storeEntity = Optional.ofNullable(storeRepository.findFirstByNameAndStatusOrderByIdDesc(request.getStoreName(), StoreStatus.REGISTERED));

        var entity = storeUserConverter.toEntity(request, storeEntity.get()); // TODO : NULL 일 때 에러 체크 필요

        var newEntity = storeUserService.register(entity);

        var response = storeUserConverter.toResponse(newEntity, storeEntity.get());
        return response;

    }

}

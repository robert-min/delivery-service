package org.delivery.db.store

import org.delivery.db.store.enums.StoreCategory
import org.delivery.db.store.enums.StoreStatus
import org.springframework.data.jpa.repository.JpaRepository

interface StoreRepository : JpaRepository<StoreEntity, Long> {
    // 특정유효한 스토어(id + status)
    // select * from store where id = ? and status = "REGISTERED" order by id desc limit 1
    fun findFirstByIdAndStatusOrderByIdDesc(id: Long?, status: StoreStatus?): StoreEntity?

    // 유효한 스토어 리스트
    // select * from store where status = "REGISTERED" order by id desc
    fun findAllByStatusOrderByIdDesc(status: StoreStatus?): List<StoreEntity>

    // 유효한 특정 카테고리와 스토어 리스트
    // select * from store where status = "REGISTERED" and category = ? order by star desc
    fun findAllByStatusAndCategoryOrderByStarDesc(status: StoreStatus?, category: StoreCategory?): List<StoreEntity>

    // select * from store where name = ? and status = ? order by id desc limit 1
    fun findFirstByNameAndStatusOrderByIdDesc(name: String?, status: StoreStatus?): StoreEntity
}

package com.gb.tonometer.data.data_source

import com.gb.tonometer.domain.entity.TonometerData

interface FirebaseMapper {
    fun mapToFirebaseEntity(tonometerData: TonometerData): HashMap<String, Any>

    fun mapToDomainEntity(firebaseEntity: Map<String, Any>, id: String): TonometerData
}
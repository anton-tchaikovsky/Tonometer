package com.gb.tonometer.data.data_source

import android.util.Log
import com.gb.tonometer.data.preferences.TonometerSharedPreferences
import com.gb.tonometer.domain.entity.TonometerData
import com.gb.tonometer.domain.entity.TonometerMeasurement
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class FirebaseDataSourceImpl @Inject constructor(
    private val mapper: FirebaseMapper,
    sharedPreferences: TonometerSharedPreferences
) :
    FirebaseDataSource {

    private val collection = Firebase.firestore.collection(COLLECTION_NAME)

    private val tonometerData: MutableList<TonometerData> = mutableListOf()

    private val tonometerDataSharedFlow: MutableSharedFlow<List<TonometerData>> =
        MutableSharedFlow()

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    init {
        if (sharedPreferences.isFirstLaunch()){
            coroutineScope.launch {
                insertTonometerData(TonometerData(TonometerData. DEFAULT_ID, "14 October", "07:09", TonometerMeasurement(125, 82, 63)))
                insertTonometerData(TonometerData(TonometerData. DEFAULT_ID,"24 October", "12:07", TonometerMeasurement(105, 80, 61)))
            }
        }
    }

    override suspend fun getTonometerData(): SharedFlow<List<TonometerData>> {
        collection.get()
            .addOnFailureListener {
                Log.d(TAG, it.message.toString())
            }
        observeCollection()
        return tonometerDataSharedFlow
    }

    override suspend fun insertTonometerData(tonometerData: TonometerData) {
        collection.add(mapper.mapToFirebaseEntity(tonometerData))
            .addOnFailureListener {
                Log.d(TAG, it.message.toString())
            }
    }

    override suspend fun deleteTonometerData(id: String) {
        if (id != TonometerData.DEFAULT_ID)
            collection.document(id).delete()
    }

    private fun observeCollection() {
        collection.addSnapshotListener { value, error ->
            error?.let {
                Log.d(TAG, it.message.toString())
            }
            value?.let {
                it.documentChanges.forEach { document ->
                    val firebaseEntity = document.document.data
                    val id = document.document.id
                    when (document.type) {
                        DocumentChange.Type.ADDED -> {
                            tonometerData.add(mapper.mapToDomainEntity(firebaseEntity, id))
                        }

                        DocumentChange.Type.REMOVED -> {
                            tonometerData.remove(tonometerData.find { tonometerData ->
                                tonometerData.id == id
                            })
                        }

                        DocumentChange.Type.MODIFIED -> {
                            tonometerData.remove(tonometerData.find { tonometerData ->
                                tonometerData.id == id
                            })
                            tonometerData.add(mapper.mapToDomainEntity(firebaseEntity, id))
                        }
                    }
                }
                coroutineScope.launch {
                    tonometerDataSharedFlow.emit(tonometerData)
                }
            }
        }
    }

    companion object {
        private const val COLLECTION_NAME = "Tonometer"
        const val TAG = "Firebase"
    }
}
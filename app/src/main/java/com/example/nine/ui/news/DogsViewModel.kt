package com.example.nine.ui.news

import android.os.Handler
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.DiffUtil
import com.example.nine.R
import com.example.nine.bindings.recyclerview.NegativeDiffCallback
import com.example.nine.data.models.Dog
import com.example.nine.data.models.DogsImgResponse
import com.example.nine.data.models.DogsResponse
import com.example.nine.domain.DogsRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.internal.schedulers.IoScheduler
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class DogsViewModel @Inject constructor(private val dogsRepository: DogsRepository) : ViewModel() {
    private var portrait = false

    private val disposable = CompositeDisposable()

    val dogs = MutableLiveData<MutableList<Dog>>()

    val assetsDiff: DiffUtil.ItemCallback<Dog> = NegativeDiffCallback()
    val assetsLayoutProvider: (Dog) -> Int = { R.layout.dog_item }

    init {
        getDogs()
    }

    fun setPortraitOrientation(isPortrait: Boolean) {
        this.portrait = isPortrait
    }

    private fun getDogs() {
//        loading.value = true      // Todo: Loading animations, before the network call.
        disposable.add(
            dogsRepository.getDogs()!!
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<DogsResponse?>() {
                    override fun onSuccess(dogsResp: DogsResponse) {
                        Log.d("XXX", "Status: ......\n ${dogsResp.status}")

                        val breedNames = dogsResp.message::class.java.declaredFields.map { it.toGenericString().substringAfterLast(".") }
                        dogs.value = breedNames.map { Dog(it, "") }.toMutableList()


                        breedNames.forEach { doggoBreedName ->
                            disposable.add(
                                dogsRepository.getImageUrls(doggoBreedName)!!
                                    .subscribeOn(IoScheduler())
                                    .subscribeWith(object : DisposableSingleObserver<DogsImgResponse?>() {
                                        override fun onSuccess(t: DogsImgResponse) {
                                            Log.d("XXX", "$t")

                                            val newDogsList = dogs.value!!.toMutableList()
                                            newDogsList.find { it.breedName == doggoBreedName }?.imgUrl = t.message


                                            dogs.postValue(newDogsList) // Sets MutableLiveData's list asynchronously from the background thread :)
                                        }

                                        override fun onError(e: Throwable) {
                                            Log.d("XXX", "ERROR !!!!")
                                            e.printStackTrace()
                                        }
                                    })
                            )
                        }
                    }

                    override fun onError(e: Throwable) {
                        // TODO: Error handling.
                        Log.d("XXX", "ERROR !!!!!!!")
                        e.printStackTrace()
                        error("Doggo breed call hath failed...")
                    }
                })
        )
    }
}
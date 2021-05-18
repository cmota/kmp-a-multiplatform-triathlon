package com.cmota.playground.triathlon

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cmota.playground.triathlon.shared.Gutenberg
import com.cmota.playground.triathlon.shared.ServiceLocator
import com.cmota.playground.triathlon.shared.data.entities.Conference
import com.cmota.playground.triathlon.shared.presentation.cb.IConferenceData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val TAG = "ConferencesViewModel"

class ConferencesViewModel: ViewModel(), IConferenceData  {

  private val _conferences = MutableLiveData<List<Conference>>(emptyList())
  val conferences: LiveData<List<Conference>> = _conferences

  private val presenter by lazy {
    ServiceLocator.getConferencePresenter
  }

  fun getConferences() {
    presenter.attachView(this)
  }

  // region IConferenceData

  override fun onConferenceDataFetched(conferences: List<Conference>) {
    Gutenberg.d(TAG, "onConferenceDataFetched | conferences=${conferences.size}")
    viewModelScope.launch {
      withContext(Dispatchers.Main) {
        _conferences.value = conferences
      }
    }
  }

  override fun onConferenceDataFailed(e: Exception) {
    Gutenberg.e(TAG, "onConferenceDataFailed | e=$e")
  }

  // endregion IConferenceData
}
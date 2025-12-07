package com.mahim.mkcam.camera

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mahim.mkcam.ai.data.cloud.CloudEnhancer
import com.mahim.mkcam.ai.data.tflite.TFLiteEnhancer
import com.mahim.mkcam.core.domain.IImageEnhancer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class CameraViewModel @Inject constructor(
    @Named("Cloud") private val cloudEnhancer: CloudEnhancer,
    @Named("TFLite") private val localEnhancer: TFLiteEnhancer
) : ViewModel() {

    private val _uiState = MutableStateFlow(CameraUiState())
    val uiState = _uiState.asStateFlow()

    fun onImageCaptured(bitmap: Bitmap) {
        // Default to local enhancement
        enhanceImage(bitmap, useCloud = _uiState.value.isCloudEnabled)
    }

    fun toggleCloudMode(enabled: Boolean) {
        _uiState.value = _uiState.value.copy(isCloudEnabled = enabled)
    }

    fun resetCamera() {
        _uiState.value = _uiState.value.copy(
            lastCapturedImage = null,
            processStatus = "",
            isProcessing = false
        )
    }

    private fun enhanceImage(bitmap: Bitmap, useCloud: Boolean) {
        viewModelScope.launch {
            val enhancer = if (useCloud) cloudEnhancer else localEnhancer
            _uiState.value = _uiState.value.copy(isProcessing = true, processStatus = "Starting...")

            enhancer.enhance(bitmap, "default").collectLatest { status ->
                when (status) {
                    is IImageEnhancer.EnhancementStatus.Progress -> {
                        _uiState.value = _uiState.value.copy(
                            processStatus = status.step,
                            processProgress = status.percentage
                        )
                    }
                    is IImageEnhancer.EnhancementStatus.Success -> {
                        _uiState.value = _uiState.value.copy(
                            isProcessing = false,
                            lastCapturedImage = status.bitmap,
                            processStatus = "Done"
                        )
                    }
                    is IImageEnhancer.EnhancementStatus.Error -> {
                         _uiState.value = _uiState.value.copy(
                            isProcessing = false,
                            processStatus = "Error: ${status.message}"
                        )
                    }
                }
            }
        }
    }
}

data class CameraUiState(
    val isProcessing: Boolean = false,
    val processStatus: String = "",
    val processProgress: Int = 0,
    val isCloudEnabled: Boolean = false,
    val lastCapturedImage: Bitmap? = null
)

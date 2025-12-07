package com.mahim.mkcam.core.domain

import android.graphics.Bitmap
import kotlinx.coroutines.flow.Flow

interface IImageEnhancer {
    suspend fun enhance(
        inputBitmap: Bitmap,
        presetId: String,
        parameters: Map<String, Any> = emptyMap()
    ): Flow<EnhancementStatus>

    sealed class EnhancementStatus {
        data class Progress(val percentage: Int, val step: String) : EnhancementStatus()
        data class Success(val bitmap: Bitmap) : EnhancementStatus()
        data class Error(val message: String) : EnhancementStatus()
    }
}

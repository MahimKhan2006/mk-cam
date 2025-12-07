package com.mahim.mkcam.core.domain.model

data class Preset(
    val id: String,
    val name: String,
    val description: String,
    val prompt: String,
    val negativePrompt: String? = null,
    val strength: Float = 0.7f,
    val detailLevel: String = "Medium", // Low, Medium, High
    val isCloudOnly: Boolean = false,
    val modelId: String? = null,
    val parameters: Map<String, String> = emptyMap()
)

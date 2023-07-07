package com.example.domain.model.response

import com.example.domain.model.user.TriveaUser

@kotlinx.serialization.Serializable
data class ApiResponse(
    val success:Boolean,
    val user: TriveaUser? = null,
    val message:String? = null
)

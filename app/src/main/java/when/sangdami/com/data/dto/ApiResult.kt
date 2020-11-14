package `when`.sangdami.com.data.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiResult<T> (
    val result : T?,
    val status : String?,
    val exception : String?
)
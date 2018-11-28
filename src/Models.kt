package ca.rjreid

import com.google.gson.annotations.SerializedName

enum class ResponseStatus {
    SUCCESS,
    ERROR
}

enum class DoorStatus {
    OPEN,
    CLOSED
}

data class DoorStatusResponse(
    @SerializedName("response_status") val responseStatus: ResponseStatus,
    @SerializedName("door_status") val doorStatus: DoorStatus)

data class DoorAction(
    @SerializedName("id") var id: Int,
    @SerializedName("user_id") val userId: Int,
    @SerializedName("date") val date: Long)
package com.example.route.user

import com.example.domain.model.EndPoints
import com.example.domain.model.requests.ApiOwnerId

import com.example.domain.model.requests.ApiUserRequest
import com.example.domain.model.response.ApiResponse
import com.example.domain.model.user.TriveaUser
import com.example.domain.repository.UserDataSource
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Route.saveUserRoute(

    userDataSource: UserDataSource,
    app:Application
) {




    post(EndPoints.SignUpUser.path) {


        val request = call.receiveOrNull<ApiOwnerId>() ?: kotlin.run {
            call.respond(HttpStatusCode.BadRequest,"Request is null")
            return@post
        }

        val userId = request.ownerId




//        val areFieldsBlank = request.username.isBlank() || request.password.isBlank()
//        val isPwTooShort = request.password.length < 8
//
//        if (areFieldsBlank || isPwTooShort) {
//            call.respond(HttpStatusCode.Conflict)
//            return@post
//        }



        val wasAcknowledged = userDataSource.saveUserInfo(userId)


            call.respond(ApiResponse(
                success = wasAcknowledged
            ))








    }


}
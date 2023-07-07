package com.example.route.user

import com.example.domain.model.EndPoints

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


        val request = call.receiveOrNull<ApiUserRequest>() ?: kotlin.run {
            call.respond(HttpStatusCode.BadRequest,"Request is null")
            return@post
        }




//        val areFieldsBlank = request.username.isBlank() || request.password.isBlank()
//        val isPwTooShort = request.password.length < 8
//
//        if (areFieldsBlank || isPwTooShort) {
//            call.respond(HttpStatusCode.Conflict)
//            return@post
//        }



        val triveaUser = TriveaUser(
            //Solve id issue

            ownerId= request.ownerId,
            bioData= "Trivious App Player",
            phoneNumber = "unverified",
            username = request.username,
            email = request.email,
            profilePicture = request.profilePicture
            //account = Account()

        )



        val wasAcknowledged = userDataSource.saveUserInfo(triveaUser)

        if (!wasAcknowledged) {
            call.respond(ApiResponse(
                success = true
            ))
            return@post
        }







    }


}
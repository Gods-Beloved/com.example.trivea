package com.example.route

import com.example.domain.model.EndPoints

import com.example.domain.model.response.SaveResponse
import com.example.domain.model.user.account_info.Account
import com.example.domain.repository.TransactionsDataSource
import com.example.domain.repository.UserDataSource
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.transact(
    app:Application,
    transactionsDataSource: TransactionsDataSource,
    userDataSource: UserDataSource
) {



        post(EndPoints.SaveTransaction.path){



            val request = call.receive<Account>()

            val acc = Account(
                id= request.id,
                email=request.email,
                name = request.name,
                amount = request.amount,
                transferType = request.transferType,
                reference = request.reference,

            )


                try {

                    val response =  transactionsDataSource.saveTransaction(account = acc,userId = request.id,userDataSource = userDataSource,request.amount)

                    if (response) {
                        

                        app.log.info("TRANSACTION SUCCESSFULLY SAVED/RETRIEVED")
                        call.respond(SaveResponse(
                            updated =  true
                        ))
                    } else {
                        app.log.info("ERROR SAVING THE TRANSFER")
                        call.respond(SaveResponse(
                            updated =  false
                        ))

                    }
                }catch (ex:Exception){
                    app.log.info("ERROR SAVING THE TRANSFER $ex")

                }








            }





//    }
}
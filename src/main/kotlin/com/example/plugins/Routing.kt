package com.example.plugins

import com.example.domain.repository.QuestionsDataSource
import com.example.domain.repository.TransactionsDataSource
import com.example.domain.repository.UserDataSource
import com.example.route.questions.getLivePrice
import com.example.route.questions.getLiveQuestions
import com.example.route.rootRoute
import com.example.route.transact
import com.example.route.user.saveUserRoute
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*
import org.koin.java.KoinJavaComponent.inject


fun Application.configureRouting(

) {
    routing {

        val userDataSource: UserDataSource by inject(UserDataSource::class.java)

        val questionsDataSource: QuestionsDataSource by inject(QuestionsDataSource::class.java)

        val transactionsDataSource: TransactionsDataSource by inject(TransactionsDataSource::class.java)



        rootRoute()
        saveUserRoute(userDataSource=userDataSource,application)
        transact(application, transactionsDataSource = transactionsDataSource, userDataSource = userDataSource)
        getLiveQuestions(questionsDataSource = questionsDataSource)
        getLivePrice(questionsDataSource = questionsDataSource)





    }
}

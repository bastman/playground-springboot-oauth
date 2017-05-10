package com.example.demo.restservice.api.handler

import com.example.demo.restservice.api.handler.common.TweetsCollectionResponse
import com.example.demo.restservice.domain.TweetService
import org.springframework.stereotype.Component

@Component
class TweetsFindByAuthorRequestHandler(private val tweetService: TweetService) {
    data class Request(val author: String)

    fun handleRequest(request: Request) = TweetsCollectionResponse.of(
            tweetService
                    .findByAuthor(author = request.author)
                    .sortedByDescending { it.createdAt }
    )
}
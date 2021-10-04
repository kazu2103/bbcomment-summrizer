package com.kazu.bitbucket.commentsummarizer.domain.service

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.node.ObjectNode
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.kazu.bitbucket.commentsummarizer.domain.model.CommentDto
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder

@Service
class CommentsCallServiceImpl(private val restTemplate: RestTemplate): CommentsCallService,
    AbstructAPICallService(restTemplate) {

    override fun getCommentList(urlList: List<String>): List<CommentDto> {
        val commentsJsonList: List<JsonNode> = doApiCall(urlList)
        if (commentsJsonList.isEmpty()){
            println("there is no comment")
            return listOf()
        }


        var objectMapper: ObjectMapper = ObjectMapper()
        val commentList: MutableList<CommentDto> = commentsJsonList.map {
                it -> objectMapper.convertValue(it, CommentDto::class.java)
        }.toMutableList()

        return commentList
    }

    fun doApiCall(urlList: List<String>): List<JsonNode> {
        val commentList: MutableList<JsonNode> = mutableListOf()
        for (url in urlList) {
            val builder: UriComponentsBuilder = UriComponentsBuilder
                .fromHttpUrl(url)

            // Do Request
            val response: JsonNode? = super.apiCall(builder)
            response?.let { commentList.add(it) }

            // bind response jsonnode to CommonDto
            val om: ObjectMapper = jacksonObjectMapper()
            om.convertValue(response, CommentDto::class.java)
        }
        return commentList
    }


}
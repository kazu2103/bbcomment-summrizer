package com.kazu.bitbucket.commentsummarizer.domain.service;

import com.fasterxml.jackson.databind.JsonNode
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder
import com.kazu.bitbucket.commentsummarizer.domain.config.WebConfig
import org.springframework.beans.factory.annotation.Autowired
import java.util.*

open class AbstructAPICallService(private val restTemplate: RestTemplate) {

    @Autowired
    lateinit var webConfig: WebConfig

    fun apiCall(builder: UriComponentsBuilder): JsonNode? {
        val response: ResponseEntity<out JsonNode> = webConfig.restTemplate().getForEntity(
            builder.toUriString(),
            JsonNode::class.java
        )
        return response.body
    }

}

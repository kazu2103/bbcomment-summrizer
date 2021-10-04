package com.kazu.bitbucket.commentsummarizer.domain.service

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Service

@Service
interface PullRequestsCallService {
    fun getPullRequestUrls(): List<String>
}
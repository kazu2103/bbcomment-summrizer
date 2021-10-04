package com.kazu.bitbucket.commentsummarizer.domain.service

import com.fasterxml.jackson.databind.JsonNode
import com.kazu.bitbucket.commentsummarizer.domain.model.CommentDto
import org.springframework.stereotype.Service

@Service
interface CommentsCallService {
    fun getCommentList(urlList: List<String>): List<CommentDto>
}
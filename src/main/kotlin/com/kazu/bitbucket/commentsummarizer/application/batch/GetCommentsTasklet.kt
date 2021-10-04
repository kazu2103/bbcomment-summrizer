package com.kazu.bitbucket.commentsummarizer.application.batch

import com.fasterxml.jackson.databind.JsonNode
import com.kazu.bitbucket.commentsummarizer.domain.model.CommentDto
import com.kazu.bitbucket.commentsummarizer.domain.service.CommentsCallService
import org.springframework.batch.core.StepContribution
import org.springframework.batch.core.configuration.annotation.StepScope
import org.springframework.batch.core.scope.context.ChunkContext
import org.springframework.batch.core.step.tasklet.Tasklet
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
@StepScope
class GetCommentsTasklet: Tasklet {

    @Autowired
    lateinit var commentsCallService: CommentsCallService

    @Value("#{jobExecutionContext['urlList']}")
    lateinit var urlList: List<String>

    override fun execute(contribution: StepContribution, chunkContext: ChunkContext): RepeatStatus {

        val commentsDtoList: List<CommentDto> = commentsCallService.getCommentList(urlList)
        if (commentsDtoList.isEmpty()){
            //TODO("use logger")
            println("there is no comment")
            return RepeatStatus.FINISHED
        }
        println(commentsDtoList)
/*
        val values: JsonNode? = commentsJson.findValue("values")
        if (values == null){
            //TODO("use logger")
            println("there is no pull-request")
            return RepeatStatus.FINISHED
        }
  */
        return RepeatStatus.FINISHED;
    }
}
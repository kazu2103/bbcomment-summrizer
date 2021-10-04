package com.kazu.bitbucket.commentsummarizer.application.batch

import com.fasterxml.jackson.databind.JsonNode
import com.kazu.bitbucket.commentsummarizer.domain.service.PullRequestsCallService
import org.springframework.batch.core.StepContribution
import org.springframework.batch.core.listener.ExecutionContextPromotionListener
import org.springframework.batch.core.scope.context.ChunkContext
import org.springframework.batch.core.step.tasklet.Tasklet
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component

@Component
class GetOutputTasklet: Tasklet {

    override fun execute(contribution: StepContribution, chunkContext: ChunkContext): RepeatStatus {
        TODO("write your output tasklet")
    }

    @Bean
    fun promotionListener(): ExecutionContextPromotionListener{
        val keySet = arrayOf("urlList")
        val promotionListener: ExecutionContextPromotionListener = ExecutionContextPromotionListener()
        promotionListener.setKeys(keySet)
        return promotionListener
    }
}
package com.kazu.bitbucket.commentsummarizer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
class CommentSummarizerApplication

fun main(args: Array<String>) {
	runApplication<CommentSummarizerApplication>(*args)
}

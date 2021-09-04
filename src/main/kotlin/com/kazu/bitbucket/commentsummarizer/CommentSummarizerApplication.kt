package com.kazu.bitbucket.commentsummarizer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CommentSummarizerApplication

fun main(args: Array<String>) {
	runApplication<CommentSummarizerApplication>(*args)
}

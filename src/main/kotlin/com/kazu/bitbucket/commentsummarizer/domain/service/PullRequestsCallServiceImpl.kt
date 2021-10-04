package com.kazu.bitbucket.commentsummarizer.domain.service

import com.fasterxml.jackson.databind.JsonNode
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder

@Service
class PullRequestsCallServiceImpl(private val restTemplate: RestTemplate): PullRequestsCallService,
    AbstructAPICallService(restTemplate) {

    @Value("\${bitbucket.api.url}")
    lateinit var apiUrl: String

    @Value("\${bitbucket.workspace}")
    lateinit var workspace: String

    @Value("\${bitbucket.repository}")
    lateinit var repos: String

    override fun getPullRequestUrls(): List<String> {
        val pullRequestsJson: JsonNode? = doApiCall()
        if (pullRequestsJson == null){
            //TODO("use logger")
            println("there is no pull-request")
            return listOf()
        }

        val values: JsonNode? = pullRequestsJson.findValue("values")
        if (values == null){
            //TODO("use logger")
            println("there is no pull-request")
            return listOf()
        }

        val urlList: MutableList<String> = mutableListOf()
        for (i in 0 until values.size()) {
            urlList.add(values.get(i).get("links").get("comments").get("href").toString().removeSurrounding("\""))
        }

        //TODO("use logger")
        println(urlList.toString())

        return urlList
    }

    fun doApiCall(): JsonNode? {
        val url: String = apiUrl + "/" + workspace + "/" + repos + "/pullrequests"
        val builder: UriComponentsBuilder = UriComponentsBuilder
            .fromHttpUrl(url)

        // Do Request
        val response: JsonNode? = super.apiCall(builder)

        return response
    }
}
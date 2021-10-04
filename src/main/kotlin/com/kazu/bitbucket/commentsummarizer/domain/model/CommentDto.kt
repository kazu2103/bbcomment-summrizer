package com.kazu.bitbucket.commentsummarizer.domain.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown=true)
data class CommentDto (
    @JsonProperty("pagelen")
    val pagelen: String,

    @JsonProperty("values")
    val values: List<Value>,

    @JsonProperty("page")
    val page: Int,

    @JsonProperty("size")
    val size: Int,
)

@JsonIgnoreProperties(ignoreUnknown=true)
data class Value (
    @JsonProperty("links")
    val links: Link,

    @JsonProperty("deleted")
    val deleted: Boolean,

    @JsonProperty("pullrequest")
    val pullrequest: PullRequest,

    @JsonProperty("content")
    val content: Content,

    @JsonProperty("created_on")
    val created_on: String,

    @JsonProperty("user")
    val user: User,

    @JsonProperty("updated_on")
    val updated_on: String,

    @JsonProperty("type")
    val type: String,

    @JsonProperty("id")
    val id: Long,
)

@JsonIgnoreProperties(ignoreUnknown=true)
data class Link (
    @JsonProperty("self")
    val self: Html,

    @JsonProperty("html")
    val html: Html,
)

@JsonIgnoreProperties(ignoreUnknown=true)
data class Html(
    @JsonProperty("href")
    val href: String,
)


@JsonIgnoreProperties(ignoreUnknown=true)
data class PullRequest (
    @JsonProperty("type")
    val type: String,

    @JsonProperty("id")
    val id: Int,

    @JsonProperty("links")
    val links: Link,

    @JsonProperty("title")
    val title: String,
)

@JsonIgnoreProperties(ignoreUnknown=true)
data class Content (
    @JsonProperty("raw")
    val raw: String,

    @JsonProperty("markup")
    val markup: String,

    @JsonProperty("html")
    val html: String,

    @JsonProperty("type")
    val type: String,
)

@JsonIgnoreProperties(ignoreUnknown=true)
data class User(
    @JsonProperty("display_name")
    val display_name: String,

    @JsonProperty("uuid")
    val uuid: String,

    @JsonProperty("links")
    val links: Link,

    @JsonProperty("type")
    val type: String,

    @JsonProperty("nickname")
    val nickname: String,

    @JsonProperty("account_id")
    val account_id: String,
)
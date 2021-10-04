package com.kazu.bitbucket.commentsummarizer.domain.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.web.client.RestTemplate

@Configuration
class WebConfig {

    @Bean
    fun restTemplate(): RestTemplate{
        val restTemplate: RestTemplate = RestTemplate()
        val converter: MappingJackson2HttpMessageConverter = MappingJackson2HttpMessageConverter()
        converter.setSupportedMediaTypes(listOf<MediaType>(MediaType.APPLICATION_JSON))
        restTemplate.messageConverters.add(converter)
        return restTemplate
    }
}
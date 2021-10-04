package com.kazu.bitbucket.commentsummarizer.application.batch

import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.BatchConfigurationException
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.core.explore.JobExplorer
import org.springframework.batch.core.explore.support.JobExplorerFactoryBean
import org.springframework.batch.core.job.flow.FlowExecutionStatus
import org.springframework.batch.core.job.flow.JobExecutionDecider
import org.springframework.batch.core.launch.JobLauncher
import org.springframework.batch.core.launch.support.SimpleJobLauncher
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean
import org.springframework.batch.core.step.tasklet.Tasklet
import org.springframework.batch.item.ItemProcessor
import org.springframework.batch.item.ItemReader
import org.springframework.batch.item.ItemWriter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource


@Configuration
@EnableBatchProcessing
class BatchConfiguration: DefaultBatchConfigurer() {

    override fun setDataSource(dataSource: DataSource) {
        //This BatchConfigurer ignores any DataSource
    }

    @Bean
    fun summarizeJob(
        jobs: JobBuilderFactory,
        @Qualifier("getPullRequestsStep") getPullRequestsStep: Step,
        @Qualifier("getCommentsStep") getCommentsStep: Step,
        @Qualifier("getOutputStep") getOutputStep: Step
    ): Job
    {
        return jobs.get("summarizeJob")
            .start(getPullRequestsStep)
            .next(getCommentsStep)
            .next(getOutputStep)
            .build()
    }

    @Bean(name = arrayOf("getPullRequestsStep"))
    fun getPullRequestsStep(steps: StepBuilderFactory, tasklet: GetPullRequestsTasklet): Step {
        return steps.get("getPullRequestsStep")
            .tasklet(tasklet)
            .listener(tasklet.promotionListener())
            .build()
    }

    @Bean(name = arrayOf("getCommentsStep"))
    fun getCommentsStep(steps: StepBuilderFactory, tasklet: GetCommentsTasklet): Step {
        return steps.get("getCommentsStep")
            .tasklet(tasklet)
            .build()
    }

    @Bean(name = arrayOf("getOutputStep"))
    fun getOutputStep(steps: StepBuilderFactory, tasklet: GetOutputTasklet): Step {
        return steps.get("getOutputStep")
            .tasklet(tasklet)
            .build()
    }
}
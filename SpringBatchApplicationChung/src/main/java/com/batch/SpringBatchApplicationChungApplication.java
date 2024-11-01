package com.batch;

import jakarta.servlet.http.PushBuilder;
import java.io.File;
import java.util.Date;
import java.util.concurrent.ThreadPoolExecutor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.PollerSpec;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.file.FileReadingMessageSource;
import org.springframework.integration.file.filters.SimplePatternFileListFilter;
import org.springframework.integration.scheduling.PollerMetadata;
import org.springframework.messaging.MessageHandler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@SpringBootApplication
public class SpringBatchApplicationChungApplication {

	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	private Job job;
	public static void main(String[] args) {
		SpringApplication.run(SpringBatchApplicationChungApplication.class, args);
	}
 //opcional es este
	@Bean
	public TaskExecutor taskExecutor(){
		ThreadPoolTaskExecutor taskExcecutor= new ThreadPoolTaskExecutor();
		taskExcecutor.setCorePoolSize(1); // cuantos hilos comineza la aplicacion
		taskExcecutor.setMaxPoolSize(5); // cuanto es el maximo de hilos
		taskExcecutor.setQueueCapacity(5); // maximo en tareas en cola
		return taskExcecutor;
	}


	@Bean
	CommandLineRunner init(){
		return args -> {
			JobParameters jobParameters = new JobParametersBuilder()
					.addString("name", "chunk")
					.addLong("id", System.currentTimeMillis())
					.addDate("date", new Date())
					.toJobParameters();
			jobLauncher.run(job, jobParameters);
		};
	}


	@Bean
	public FileReadingMessageSource fileReadingMessageSource() {
		FileReadingMessageSource source = new FileReadingMessageSource();
		source.setDirectory(new File("/ejemplo")); // Cambia esta ruta a la carpeta que deseas monitorear
		source.setFilter( new SimplePatternFileListFilter("*.csv"));
		return source;
	}

	@Bean
	public MessageHandler fileMessageHandler() {
		return message -> {
			File file = (File) message.getPayload();
			System.out.println("Archivo detectado: " + file.getName());

			// Ejecutar el trabajo cuando se detecte un nuevo archivo
			JobParameters jobParameters = new JobParametersBuilder()
					.addString("name", "chunk")
					.addLong("id", System.currentTimeMillis())
					.addDate("date", new Date())
					.addString("fileName", file.getName()) // Puedes agregar parámetros adicionales
					.toJobParameters();
			try {
				jobLauncher.run(job, jobParameters);
			} catch (Exception e) {
				e.printStackTrace();
			}
		};
	}

	@Bean
	public IntegrationFlow fileIntegrationFlow() {
		return IntegrationFlow.from(fileReadingMessageSource(),
						c -> c.poller(poller()))
				.handle(fileMessageHandler())
				.get();
	}

	@Bean
	public PollerMetadata poller() {
		return Pollers.fixedDelay(5000) // 5 segundos
				.getObject();
	}

}

package ds.assign1;

import ds.assign1.messaging.service.Consumer;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.io.IOException;
import java.util.TimeZone;
import java.util.concurrent.TimeoutException;

@SpringBootApplication
@Validated
@EnableRabbit
@CrossOrigin
public class Assign1 extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Assign1.class);
    }

    public static void main(String[] args) throws IOException, TimeoutException {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        SpringApplication.run(Assign1.class, args);
    }
}

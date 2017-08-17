package mojtaba.safaeian.go3;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;

/**
 * @author  Mojtaba Safaeian
 * Created at: 11/11/2016.
 *
 * This is a sample implementation for Game-of-three application
 *
 */

@SpringBootApplication
public class GO3Application extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(GO3Application.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(GO3Application.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}

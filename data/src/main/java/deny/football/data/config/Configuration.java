package deny.football.data.config;

import deny.football.data.infrastructure.ClubRepository;
import deny.football.data.infrastructure.PlayerRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.client.RestTemplate;

@EnableMongoRepositories(basePackageClasses = {PlayerRepository.class, ClubRepository.class})
@org.springframework.context.annotation.Configuration
public class Configuration {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

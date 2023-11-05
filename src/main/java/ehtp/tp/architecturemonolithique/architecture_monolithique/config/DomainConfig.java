package ehtp.tp.architecturemonolithique.architecture_monolithique.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EntityScan("ehtp.tp.architecturemonolithique.architecture_monolithique.domain")
@EnableJpaRepositories("ehtp.tp.architecturemonolithique.architecture_monolithique.repos")
@EnableTransactionManagement
public class DomainConfig {
}

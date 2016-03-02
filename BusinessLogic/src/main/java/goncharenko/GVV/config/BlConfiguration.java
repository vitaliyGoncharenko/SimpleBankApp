package goncharenko.GVV.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ComponentScan(value = "goncharenko.GVV.managers")
@ImportResource(value = "classpath:app-context-annotation.xml")
public class BlConfiguration {
}

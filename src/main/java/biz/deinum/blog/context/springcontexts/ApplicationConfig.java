package biz.deinum.blog.context.springcontexts;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import biz.deinum.blog.context.springcontexts.Person;
import biz.deinum.blog.context.springcontexts.SystemOutGreeter;

@Configuration
public class ApplicationConfig {

    @Bean
    public Person person() {
        return new Person("Marten Deinum");
    }

    @Bean
    public Greeter greeter() {
        return new SystemOutGreeter();
    }
}

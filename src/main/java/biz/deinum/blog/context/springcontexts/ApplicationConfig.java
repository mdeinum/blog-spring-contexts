package biz.deinum.blog.context.springcontexts;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import biz.deinum.blog.context.springcontexts.Person;
import biz.deinum.blog.context.springcontexts.SystemOutGreeter;

@Configuration
public class ApplicationConfig {


    @Bean
    public Person person() {
        Person person = new Person();
        person.setName("Marten Deinum");
        return person;
    }

    @Bean
    public SystemOutGreeter greeter() {
        return new SystemOutGreeter();
    }
}

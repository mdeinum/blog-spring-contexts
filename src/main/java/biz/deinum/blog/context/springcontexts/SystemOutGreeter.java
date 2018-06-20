package biz.deinum.blog.context.springcontexts;

import org.springframework.stereotype.Component;

@Component
public class SystemOutGreeter implements Greeter {

    @Override
    public void greet(Person person) {
        System.out.printf("Greetings %s!%n", person.getName());
    }
}

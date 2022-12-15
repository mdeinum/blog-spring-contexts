package biz.deinum.blog.context.springcontexts;

public class SystemOutGreeter implements Greeter {

    @Override
    public void greet(Person person) {
        System.out.printf("Greetings %s!%n", person.name());
    }
}

package biz.deinum.blog.context.springcontexts;

public class Person {

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("Person [name=%s]", this.name);
    }
}

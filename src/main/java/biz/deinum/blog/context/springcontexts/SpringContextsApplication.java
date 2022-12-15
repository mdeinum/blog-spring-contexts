package biz.deinum.blog.context.springcontexts;

import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

public class SpringContextsApplication {

    public static void main(String[] args) throws Exception {

        // Properties
        try (var contextFromProperties = new GenericApplicationContext()) {
            var propertiesReader = new PropertiesBeanDefinitionReader(contextFromProperties);
            propertiesReader.loadBeanDefinitions("classpath:application-context.properties");
            contextFromProperties.refresh();
            doGreeting(contextFromProperties);
        }

        // XML
        try (var contextFromXml = new GenericApplicationContext()) {
            var xmlReader = new XmlBeanDefinitionReader(contextFromXml);
            xmlReader.loadBeanDefinitions("classpath:applicationContext.xml");
            contextFromXml.refresh();
            doGreeting(contextFromXml);
        }

        // The same as above but now the loading is encapsulated
        try (var xmlContext = new ClassPathXmlApplicationContext("applicationContext.xml")) {
            doGreeting(xmlContext);
        }

        // Java Configuration
        try (var javaContext = new AnnotationConfigApplicationContext(ApplicationConfig.class)) {
            doGreeting(javaContext);
        }

        // Java Configuration with Functional Bean Registation
        try (var functionalContext = new GenericApplicationContext()) {
            functionalContext.registerBean("person", Person.class, () -> new Person("Marten Deinum"));
			functionalContext.registerBean("greeter", Greeter.class, SystemOutGreeter::new);
			functionalContext.refresh();
			doGreeting(functionalContext);
        }
    }

    private static void doGreeting(ApplicationContext ctx) {
        var greeter = ctx.getBean(Greeter.class);
        var person = ctx.getBean(Person.class);
        greeter.greet(person);
    }
}

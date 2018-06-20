package biz.deinum.blog.context.springcontexts;

import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

public class SpringContextsApplication {

	public static void main(String[] args) throws Exception {

		// Properties
		GenericApplicationContext contextFromProperties = new GenericApplicationContext();
		BeanDefinitionReader propertiesReader = new PropertiesBeanDefinitionReader(contextFromProperties);
		propertiesReader.loadBeanDefinitions("classpath:application-context.properties");
		contextFromProperties.refresh();
		doGreeting(contextFromProperties);
		contextFromProperties.stop();

		// XML
		GenericApplicationContext contextFromXml = new GenericApplicationContext();
		BeanDefinitionReader xmlReader = new XmlBeanDefinitionReader(contextFromXml);
		xmlReader.loadBeanDefinitions("classpath:applicationContext.xml");
		contextFromXml.refresh();
		doGreeting(contextFromXml);
		contextFromXml.stop();

		// The same as above but now the loading is encapsulated
		ApplicationContext xmlContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		doGreeting(xmlContext);
		((ClassPathXmlApplicationContext) xmlContext).stop();

		// Java Configuration
		ApplicationContext javaContext = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		doGreeting(xmlContext);
		((AnnotationConfigApplicationContext) javaContext).close();

	}

	private static void doGreeting(ApplicationContext ctx) {
		Greeter greeter = ctx.getBean(Greeter.class);
		Person person = ctx.getBean(Person.class);
		greeter.greet(person);

	}
}

package co.kr.api.restapisample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class RestApiSampleApplication {

	public static void main(String[] args) {
		ApplicationContext ac = SpringApplication.run(RestApiSampleApplication.class, args);
		/*String[] allBeanNm = ac.getBeanDefinitionNames();
		for(String bean : allBeanNm ){
			System.out.println("getBean :: >> " + bean);
		}*/
	}
	/*@Bean
	public LocaleResolver localeResolver(){
		SessionLocaleResolver localeResolver = new SessionLocaleResolver();
		localeResolver.setDefaultLocale(Locale.US);
		return localeResolver;
	}*/
}

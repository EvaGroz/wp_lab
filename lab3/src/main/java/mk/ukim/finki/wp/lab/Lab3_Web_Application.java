package mk.ukim.finki.wp.lab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class Lab3_Web_Application {

	public static void main(String[] args) {
		SpringApplication.run(Lab3_Web_Application.class, args);
	}

}

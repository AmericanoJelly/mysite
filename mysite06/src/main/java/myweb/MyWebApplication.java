package myweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/*class A{
	아우터 클래스  public 못씀
}*/

@SpringBootApplication
public class MyWebApplication {
	
	@Controller
	//inner 클래스
	public class HelloController{
		@GetMapping("/hello")
		public String hello() {
			return "hello";
		}
	}
	

	@RestController
	public class HelloController2 {
		@GetMapping("/hello2")
		public String hello() {
			return "hello";
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(MyWebApplication.class, args);
	         
	      }
	}


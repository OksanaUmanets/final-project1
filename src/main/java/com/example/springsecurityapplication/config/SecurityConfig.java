package com.example.springsecurityapplication.config;

import com.example.springsecurityapplication.services.PersonDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	private final PersonDetailsService personDetailsService;

	@Bean
	public PasswordEncoder getPasswordEncode() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http.authorizeHttpRequests() // указываем что все страницы должны быть защищены аутентификацией
                .requestMatchers("/admin").hasRole("ADMIN") // указываем на то что страница /admin доступна пользователю
                // с ролью ADMIN
                // указываем что не аутентифицированные пользователя могут зайти на страницу
                // аутентификации и на объект ошибки
                // c помощью permitAll указываем что не аутентифицированные пользователи могут
                // заходить на перечисленные страницы
                // указываем что для всех остальных страниц необходимо вызывать метод
                // authenticated(), который открывает форму аутентификации
//                .anyRequest().authenticated()
                .requestMatchers("/authentication", "/", "/registration", "/error", "/resources/**", "/static/**","/assets/**", "/css/**",
                        "/js/**", "/img/**", "/product", "/product/info/{id}","/product/info/css/**","/product/info/img/**", "/product/search","/search")
                .permitAll().anyRequest().hasAnyRole("USER", "ADMIN").and() // указываем что дальше настраиватеся
                // аутентификация и соединяем ее с
                // настройкой доступа
                .formLogin(login -> login.loginPage("/authentication") // указываем какой url запрос будет отправлятся при заходе на
                        // защищенные страницы
                        .loginProcessingUrl("/process_login") // указываем на какой адрес будут отправляться данные с формы. Нам
                        // уже не нужно будет создавать метод в контроллере и
                        // обрабатывать данные с формы. Мы задали url, который
                        // используется по умолчанию для обработки формы аутентификации
                        // по средствам Spring Security. Spring Security будет ждать
                        // объект с формы аутентификации и затем сверять логин и пароль
                        // с данными в БД
                        .defaultSuccessUrl("/person account", true) // Указываем на какой url необходимо направить пользователя
                        // после успешной аутентификации. Вторым аргументом
                        // указывается true чтобы перенаправление шло в любом случае
                        // послу успешной аутентификации
                        .failureUrl("/authentication?error")).logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/authentication"));
		return http.build();
	}

	@Autowired
	public SecurityConfig(PersonDetailsService personDetailsService) {
		this.personDetailsService = personDetailsService;
	}

	protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(personDetailsService).passwordEncoder(getPasswordEncode());
	}

}

package com.fatec.scel.po;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;



@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
class REQ01CadastrarLivroTS {
	private WebDriver driver;
	private static Logger logger;
	@BeforeEach
	public void setup() {
		logger = LogManager.getLogger(REQ01CadastrarLivroTS.class);
		System.setProperty("webdriver.chrome.driver", "browserDriver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://ts-scel.herokuapp.com");
	}

	@AfterEach
	public void tearDown() {
		driver.quit();
	}

	
	@Test
	public void ct03_quando_dados_validos_retorna_livro_cadastrado() {
		logger.info(">>>>>> 1. Abre pagina de login");
		PageLogin pageLogin = new PageLogin (driver);
		pageLogin.login("jose", "123");
		espera();
		PageCadastrarLivro pageLivro = new PageCadastrarLivro(driver);
		pageLivro.cadastrar("3333", "Delamaro", "Teste de Software");
		espera();
		assertEquals("Lista de livros", pageLivro.getResultadoCadastroComSucesso());
		pageLivro.excluiRegistro();
	}
	public void espera() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

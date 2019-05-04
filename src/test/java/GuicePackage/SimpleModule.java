package GuicePackage;

import java.io.FileReader;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.google.inject.name.Names;

import Pages.CaseInfoFrame;
import Pages.CaseInfoPage;
import Pages.IGoPage;
import Pages.IGoPageFrame;
import Pages.LoginPage;
import Pages.StartPage;
import Tests.Tests;

public class SimpleModule extends AbstractModule {

	@Override
	protected void configure() {

		bind(WebDriver.class).toProvider(WebDriverProvider.class).in(Scopes.SINGLETON);
		bind(LoginPage.class);
		bind(StartPage.class);
		bind(CaseInfoFrame.class);
		bind(CaseInfoPage.class);
		bind(IGoPage.class);
		bind(IGoPageFrame.class);
		bind(Tests.class);

		Properties properties = new Properties();
		
		try {
	        properties.load(new FileReader("src/test/resources/config.properties"));
			Names.bindProperties(binder(), properties);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

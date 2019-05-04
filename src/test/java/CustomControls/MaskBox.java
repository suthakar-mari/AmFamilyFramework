package CustomControls;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import ru.yandex.qatools.htmlelements.element.TextInput;

public class MaskBox extends TextInput{
	
	public MaskBox(WebElement customElement) {
		super(customElement);
	}
	
	@Override 
	public void sendKeys(CharSequence... typeValue) {

		int length = getWrappedElement().getText().length();
		
		for (int i = 0; i < length; i++) {
			getWrappedElement().sendKeys(Keys.BACK_SPACE);
		}

		String maskSymbols = getWrappedElement().getText().replace("_", "").replace("-", "");	
		String rawText = StringUtils.replaceChars(Arrays.toString(typeValue), maskSymbols, "");
		String expectedKeys = "";	
		
		for (int i = 0; i < rawText.length(); i++) {
			String key = String.valueOf(rawText.charAt(i));
			expectedKeys += key;
			getWrappedElement().sendKeys(key);
		}
	}

}

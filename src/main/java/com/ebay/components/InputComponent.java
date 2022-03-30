package com.ebay.components;

import com.ebay.utils.javascript_executor.JSExecutor;
import org.openqa.selenium.Keys;

public class InputComponent extends BaseComponent {

    public void sendKeys(CharSequence... keysToSend) {
        this.clear();
        this.webElement.sendKeys(keysToSend);
    }

    public void append(String value) {
        this.webElement.sendKeys(value);
    }

    public void clear() {
        this.click();
        JSExecutor.clearInput(this.webElement);
    }

    public void applyEnteredValue() {
        String shiftTab = Keys.chord(Keys.LEFT_SHIFT, Keys.TAB);
        this.webElement.sendKeys(shiftTab);
    }

    public String getValue() {
        String value = this.webElement.getAttribute("value");
        return value;
    }

    public boolean isEnabled() {
        boolean status = this.webElement.isEnabled();
        return status;
    }

    public boolean isTextHidden() { // for password etc fields with entered values like '••••'
        return this.getAttribute("type").equals("password");
    }

    public void pressEnter() {
        String shiftTab = Keys.chord(Keys.ENTER);
        this.webElement.sendKeys(shiftTab);
    }

    public void clearViaBackspace() {
        this.click();
        String value = this.getValue();
        if (!value.isEmpty()) {
            for (int i = 0; i < value.length(); i++) {
                this.webElement.sendKeys(Keys.BACK_SPACE);
            }
        }
    }
}

package helpers;

import java.util.Random;

public class TestData {
    Random random = new Random();
    private String testEmail = (random.nextInt(10000)) + "@mailto.plus";
    private String testPassword = "p@ssword";
    private String testName = "yelloweye";
    private String mainPageURL = "https://stellarburgers.nomoreparties.site/";
    private String loginPageURL = "https://stellarburgers.nomoreparties.site/login";
    private String registrationPageURL = "https://stellarburgers.nomoreparties.site/register";
    private String accountPageURL = "https://stellarburgers.nomoreparties.site/account/profile";

    public String getTestEmail() {
        return testEmail;
    }

    public String getTestPassword() {
        return testPassword;
    }

    public String getTestName() {
        return testName;
    }

    public String getMainPageURL() {
        return mainPageURL;
    }

    public String getLoginPageURL() {
        return loginPageURL;
    }

    public String getAccountPageURL() {
        return accountPageURL;
    }

    public void setTestPassword(String testPassword) {
        this.testPassword = testPassword;
    }


}
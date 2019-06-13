package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import ru.stqa.pft.mantis.model.UserData;

public class ChangePasswordHelper extends HelperBase {

  public ChangePasswordHelper(ApplicationManager app) {
    super(app);
  }

  public void adminLogin() {
    wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
    type(By.name("username"), "administrator");
    type(By.name("password"), "root");
    click(By.xpath("//input[@type='submit']"));
  }

  public void goToManageUsers() {
    click(By.xpath("//a[@href='/mantisbt-1.2.19/manage_user_page.php']"));
  }

  public void selectUser(UserData user) {
    click(By.xpath(String.format("//a[contains(text(),'%s')]", user.getUsername())));
  }

  public void resetPassword() {
    click(By.xpath("//input[@value='Reset Password']"));
  }

}
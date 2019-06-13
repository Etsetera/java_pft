package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.appmanager.HttpSession;
import ru.stqa.pft.mantis.model.UserData;
import ru.stqa.pft.mantis.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ChangePasswordTests extends TestBase {

  @BeforeMethod
  public void startMailServer()  {
    app.mail().start();
  }

  @Test
  public void testChangePassword() throws IOException, MessagingException {
    app.changePasswd().adminLogin();
    app.changePasswd().goToManageUsers();
    UserData userFromDB = app.db().users().iterator().next();
    app.changePasswd().selectUser(userFromDB);
    String password = "newpassword";
    app.changePasswd().resetPassword();
    List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);

    String confirmationLink = findConfirmationLink(mailMessages, userFromDB.getEmail());
    app.registration().finish(confirmationLink, password);
    HttpSession session = app.newSession();
    assertTrue(session.login(userFromDB.getUsername(), password));
    assertTrue(session.isLoggedInAs(userFromDB.getUsername()));

  }

  private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }

  @AfterMethod(alwaysRun = true)
  public void stopMailServer() {
    app.mail().stop();
  }
}
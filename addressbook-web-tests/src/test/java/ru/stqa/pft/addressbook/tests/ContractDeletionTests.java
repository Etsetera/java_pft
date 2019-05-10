package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class ContractDeletionTests  extends TestBase {

  @Test
  public void testContractDeletion () {
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteSelectedContact();
    app.getContactHelper().alertAcceptDeletionContact();
    app.getNavigationHelper().gotoHomePage();
  }
}

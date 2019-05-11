package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContractDeletionTests extends TestBase {

  @Test
  public void testContractDeletion() {
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Elena", "Nikonova", "Samara, st Central 4-67", "89654206522", "nikon@testov.com", "test1"));
      app.getNavigationHelper().gotoHomePage();
    }
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteSelectedContact();
    app.getContactHelper().alertAcceptDeletionContact();
    app.getNavigationHelper().gotoHomePage();
  }
}

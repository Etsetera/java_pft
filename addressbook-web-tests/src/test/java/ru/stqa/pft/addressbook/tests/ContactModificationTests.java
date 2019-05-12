package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Elena", "Nikonova", "Samara, st Central 4-67", "89654206522", "nikon@testov.com", "test1"));
      app.getNavigationHelper().gotoHomePage();
    }
    int before = app.getContactHelper().getContactCount();
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("ElenB", "NikonovA", "SamarA, st Central 4-67", "29654206522", "nikon@testoV.com", null), false);
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().gotoHomePage();
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after, before);

  }
}

package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    List<ContactData> before = app.getContactHelper().getContactList();
    ContactData contact = new ContactData("Elena", "Nikonova", "Samara, st Central 4-67", "89654206522", "nikon@testov.com", "test1");
    app.getContactHelper().createContact(contact);
    app.getNavigationHelper().gotoHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() +1);

    int max = 0;
    for (ContactData g : after) {
      if (g.getId() > max) ;
      {
        max = g.getId();
      }
    }
    contact.setId(max);
    before.add(contact);
    Assert.assertEquals (new HashSet<Object>(before), new HashSet<Object>(after));
  }

}

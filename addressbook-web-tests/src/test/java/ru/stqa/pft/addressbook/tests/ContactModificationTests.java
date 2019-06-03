package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.contact().all().size()==0) {
      app.contact().create(new ContactData()
              .withFirstname("Elena").withLastname("Nikonova").withAddress("Samara, st Central 4-67").withMobile("89654206522").withEmail("nikon@testov.com").withGroup("test1"));
      app.goTo().homePage();
    }
  }

  @Test
  public void testContactModification() {
    Set<ContactData> before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData()
    .withId(modifiedContact.getId()).withFirstname("ElenaB").withLastname("NikonovaB").withAddress("Samara, st Central 4-67").withMobile("89654206588").withEmail("Bnikon@testov.com");
    app.contact().modify(contact);
    app.goTo().homePage();
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size());

    before.remove(modifiedContact);
    before.add(contact);
    Assert.assertEquals(before, after);

  }
}

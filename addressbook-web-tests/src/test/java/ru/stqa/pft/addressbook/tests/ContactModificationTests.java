package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size()==0) {
      //Groups groups = app.db().groups();
      app.contact().create(new ContactData()
              .withFirstname("Elena").withLastname("Nikonova").withAddress("Samara, st Central 4-67")
              .withHomePhone("+7(111)856").withMobilePhone("22-22").withWorkPhone("33 33 33")
              .withEmail("nikon@testov.com")
              .withEmail2("nikon22@testov.com")
              .withEmail3("nikon33@testov.com"));
              //.inGroup(groups.iterator().next()));
    }
  }

  @Test
  public void testContactModification() {
    Contacts before = app.db().contacts();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData()
    .withId(modifiedContact.getId()).withFirstname("ElenaB").withLastname("NikonovaB").withAddress("Samara, st Central 4-67").withMobilePhone("89654206588").withEmail("Bnikon@testov.com");
    app.contact().modify(contact);
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    verifyContactListInUI();

  }
}

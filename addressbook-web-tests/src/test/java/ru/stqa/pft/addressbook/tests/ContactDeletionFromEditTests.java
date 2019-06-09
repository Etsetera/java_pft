package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionFromEditTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size()==0) {
      app.contact().create(new ContactData()
              .withFirstname("Elena").withLastname("Nikonova").withAddress("Samara, st Central 4-67").withMobilePhone("89654206522").withEmail("nikon@testov.com").withGroup("test1"));
         }
  }

  @Test
  public void testContractDeletionFromEdit() {
    Contacts before = app.db().contacts();
    ContactData deletedContact = before.iterator().next();
    app.contact().deleteFromMod(deletedContact);
    assertThat(app.contact().count(), equalTo(before.size() -1));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(deletedContact)));
    verifyContactListInUI();
  }


}

package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    Contacts before = app.contact().all();
    ContactData contact = new ContactData()
            .withFirstname("Elena").withLastname("Nikonova").withAddress("Samara, st Central 4-67")
            .withHomePhone("+7(111)856").withMobilePhone("22-22").withWorkPhone("33 33 33")
            .withEmail("nikon@testov.com")
            .withEmail2("nikon22@testov.com")
            .withEmail3("nikon33@testov.com")
            .withGroup("test1");
    app.contact().create(contact);
    assertThat(app.contact().count(), equalTo(before.size() +1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

}

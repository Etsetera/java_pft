package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;


import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactRemoveFromGroup extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().groups().size() == 0) {
      app.group().create(new GroupData().withName("test1").withHeader("header1").withFooter("footer1"));
    }
    Groups groups = app.db().groups();
    app.contact().deleteAllContacts();
    ContactData newContact = new ContactData()
            .withFirstname("Elena").withLastname("Nikonova").withAddress("Samara, st Central 4-67")
            .withHomePhone("+7(111)856").withMobilePhone("22-22").withWorkPhone("33 33 33")
            .withEmail("nikon@testov.com")
            .withEmail2("nikon22@testov.com")
            .withEmail3("nikon33@testov.com").inGroup(groups.iterator().next());
    app.contact().create(newContact);
  }

  @Test
  public void testRemoveContactFromGroup() {
    Contacts contacts = app.db().contacts().stream().filter(c -> c.getGroups().size() > 0).collect(Collectors.toCollection(Contacts::new));
    ContactData contact = contacts.iterator().next();
    app.contact().deleteFromGroup(contact);
    ContactData contactWithoutGroup = app.db().contacts().stream().filter(c -> c.getId() == contact.getId()).collect(Collectors.toList()).iterator().next();
    assertThat(contactWithoutGroup.getGroups(), equalTo(contact.getGroups().withOut(contact.getGroups().iterator().next())));
  }


}


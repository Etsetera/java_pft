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

public class ContactAddToGroup extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().groups().size() == 0) {
      app.group().create(new GroupData().withName("test1").withHeader("header1").withFooter("footer1"));
    }
    app.contact().deleteAllContacts();

  }

  @Test
  public void ContactAddToGroup() {
    app.contact().create(new ContactData()
            .withFirstname("Elena").withLastname("Nikonova").withAddress("Samara, st Central 4-67")
            .withHomePhone("+7(111)856").withMobilePhone("22-22").withWorkPhone("33 33 33")
            .withEmail("nikon@testov.com")
            .withEmail2("nikon22@testov.com")
            .withEmail3("nikon33@testov.com"));
    Groups groups = app.db().groups();
    Contacts contacts = app.db().contacts().stream().filter(contact -> contact.getGroups().size() < groups.size()).collect(Collectors.toCollection(Contacts::new));
    ContactData modify = contacts.iterator().next();
    groups.removeAll(modify.getGroups());
    app.contact().addToGroup(modify, groups.iterator().next());
    ContactData modified = app.db().contacts().stream().filter(c -> c.getId() == modify.getId()).collect(Collectors.toList()).iterator().next();
    assertThat(modified.getGroups(), equalTo(modify.getGroups().withAdded(groups.iterator().next())));
  }

}
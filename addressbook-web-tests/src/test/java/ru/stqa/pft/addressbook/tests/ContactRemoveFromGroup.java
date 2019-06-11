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
      app.group().create(new GroupData().withName("testRem1").withHeader("header1").withFooter("footer1"));
    }
    Groups groups = app.db().groups();
    if (app.db().contacts().size() == 0) {
      app.contact().create(new ContactData()
              .withFirstname("ElenaRem").withLastname("NikonovaRem").withAddress("Samara, st Central 4-67")
              .withHomePhone("+7(111)856").withMobilePhone("22-22").withWorkPhone("33 33 33")
              .withEmail("nikon@testov.com")
              .withEmail2("nikon22@testov.com")
              .withEmail3("nikon33@testov.com").inGroup(groups.iterator().next()));
    }
    Contacts beforeContacts = app.db().contacts().stream()
            .filter((a) -> a.getGroups().size() > 0).collect(Collectors.toCollection(Contacts::new));
    if (beforeContacts.size() == 0) {
      int groupsCount = app.db().groups().size();
      Contacts filterContacts = app.db().contacts().stream()
              .filter((a) -> a.getGroups().size() < groupsCount).collect(Collectors.toCollection(Contacts::new));
      ContactData selectedContact = filterContacts.iterator().next();
      Groups GroupsOfContact = selectedContact.getGroups();
      groups.removeAll(GroupsOfContact);
      GroupData thatGroup = groups.iterator().next();
      app.contact().addToGroup(selectedContact, thatGroup);
    }
  }

  @Test
  public void testRemoveContactFromGroup() {
    Groups beforeGroups = app.db().groups();
    Contacts beforeContacts = app.db().contacts();
    Contacts filterContacts = app.db().contacts().stream()
            .filter((a) -> a.getGroups().size() > 0).collect(Collectors.toCollection(Contacts::new));
    ContactData selectedContact = filterContacts.iterator().next();
    Groups listOfGroups = selectedContact.getGroups();
    GroupData thatGroup = listOfGroups.iterator().next();
    app.contact().deleteFromGroup(selectedContact);
    Groups afterGroups = app.db().groups();
    Contacts afterContacts = app.db().contacts();
    Contacts filterContactsAfter = app.db().contacts().stream()
            .filter((a) -> a.getId() == selectedContact.getId()).collect(Collectors.toCollection(Contacts::new));
    ContactData selectedContactAfter = filterContactsAfter.iterator().next();
    Groups groupsAfter = selectedContactAfter.getGroups();
    assertThat(afterContacts, equalTo(beforeContacts));
    assertThat(afterGroups, equalTo(beforeGroups));
    assertThat(groupsAfter, equalTo(listOfGroups.withOut(thatGroup)));

  }

}


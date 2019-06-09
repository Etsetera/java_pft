package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactInfoTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.contact().all().size() == 0) {
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
  public void testContactInfo() {
    app.contact().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().InfoFromEditForm(contact);
    assertThat((contact.getAddress()), equalTo(contactInfoFromEditForm.getAddress()));
    assertThat((contact.getAllPhones()), equalTo(mergePhones(contactInfoFromEditForm)));
    assertThat((contact.getAllEmails()), equalTo(mergeEmails(contactInfoFromEditForm)));
  }

  private String mergeEmails(ContactData contact) {
    return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
            .stream().filter((s) -> !s.equals(""))
            .map(ContactInfoTest::cleanedEmails)
            .collect(Collectors.joining("\n"));
  }

  public static String cleanedEmails(String email) {
    return email.replaceAll("\\s", "");
  }

  private String mergePhones(ContactData contact) {
    return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
            .stream().filter((s) -> !s.equals(""))
            .map(ContactInfoTest::cleanedPhones)
            .collect(Collectors.joining("\n"));
  }

  public static String cleanedPhones(String phone) {

    return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
  }
}
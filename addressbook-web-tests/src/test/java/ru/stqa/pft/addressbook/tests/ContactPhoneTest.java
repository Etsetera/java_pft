package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.contact().all().size()==0) {
      app.contact().create(new ContactData()
              .withFirstname("Elena").withLastname("Nikonova").withAddress("Samara, st Central 4-67")
              .withHomePhone("111").withMobilePhone("222").withWorkPhone("333").withEmail("nikon@testov.com").withGroup("test1"));
    }
  }

  @Test
  public void testContactPhones(){
    app.contact().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm  =app.contact().InfoFromEditForm(contact);
    assertThat((contact.getAllPhones()), equalTo(mergePhones(contactInfoFromEditForm)));
    }

  private String mergePhones(ContactData contact) {
    return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
            .stream().filter( (s) -> ! s.equals("") )
            .map(ContactPhoneTest::cleaned)
            .collect(Collectors.joining("\n"));
  }

  public static String cleaned(String phone) {
    return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
  }
}

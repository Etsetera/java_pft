package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

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
    assertThat((contact.getHomePhone()), equalTo(cleaned(contactInfoFromEditForm.getHomePhone())));
    assertThat((contact.getMobilePhone()), equalTo(cleaned(contactInfoFromEditForm.getMobilePhone())));
    assertThat((contact.getWorkPhone()), equalTo(cleaned(contactInfoFromEditForm.getWorkPhone())));
  }

  public String cleaned(String phone) {
    return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
  }
}

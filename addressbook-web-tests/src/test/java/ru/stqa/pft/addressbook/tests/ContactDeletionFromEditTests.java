package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionFromEditTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.group().list().size()==0) {
      app.contact().create(new ContactData("Elena", "Nikonova", "Samara, st Central 4-67", "89654206522", "nikon@testov.com", "test1"));
      app.goTo().homePage();
    }
  }

  @Test
  public void testContractDeletionFromEdit() {
    List<ContactData> before = app.contact().list();
    int index = before.size() - 1;
    app.contact().initContactModification(index);
    app.contact().deleteContactFromEdit();
    app.goTo().homePage();
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(index);
    Assert.assertEquals(before, after);
  }

}

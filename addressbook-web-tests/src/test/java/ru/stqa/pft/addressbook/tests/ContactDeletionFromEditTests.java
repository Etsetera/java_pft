package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionFromEditTests extends TestBase {

  @Test
  public void testContractDeletionFromEdit (){
    app.getContactHelper().initContactModification();
    app.getContactHelper().deleteContactFromEdit();
    app.getNavigationHelper().gotoHomePage();
  }

}

package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {


  @Parameter(names = "-c", description = "Contact count")
  public int count;

  @Parameter(names = "-f", description = "Target file")
  public String file;

  @Parameter(names = "-d", description = "Data format")
  public String format;

  public static void main(String[] args) throws IOException {
    ContactDataGenerator generator = new ContactDataGenerator();
    JCommander jCommander = new JCommander(generator);
    try {
      jCommander.parse(args);
    } catch (ParameterException ex) {
      jCommander.usage();
      return;
    }
    generator.run();
  }

  private void run() throws IOException {
    List<ContactData> contacts = generateContacts(count);
    if (format.equals("csv")) {
      saveAsCsv(contacts, new File(file));
    } else if (format.equals("xml")){
      saveAsXml(contacts, new File(file));
    } else {
      System.out.println("Unrecognized  format " + format);
    }
  }

  private void saveAsXml(List<ContactData> contacts, File file) throws IOException {
    XStream xstream = new XStream();
    xstream.processAnnotations(ContactData.class);
    String xml = xstream.toXML(contacts);
     try (Writer writer = new FileWriter(file)) {
       writer.write(xml);
     }
  }

  private void saveAsCsv(List<ContactData> contacts, File file) throws IOException {
   try (Writer writer = new FileWriter(file)) {
     for (ContactData contact : contacts) {
       writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s\n", contact.getFirstname(), contact.getLastname()
               ,contact.getAddress(), contact.getHomePhone(), contact.getMobilePhone()
               ,contact.getWorkPhone(), contact.getEmail(), contact.getEmail2(), contact.getEmail3(), contact.getGroup()));
     }
   }
  }

  private List<ContactData> generateContacts(int count) {
    List<ContactData>contacts = new ArrayList<ContactData>();
    for (int i = 0; i < count; i++) {
      contacts.add(new ContactData().withFirstname(String.format("Elena%s", i))
              .withLastname(String.format("Nikonova%s", i)).withAddress(String.format("Samara, st Central 4-%s", i))
              .withHomePhone(String.format("+7(111)856%s", i)).withMobilePhone(String.format("22-22%s", i))
              .withWorkPhone(String.format("33 33 3%s", i)).withEmail(String.format("nikon%s@testov.com", i))
              .withEmail2(String.format("nikon%s@testov2.com", i)).withEmail3(String.format("nikon%s@testov3.com", i)).withGroup((String.format("test1"))));
    }
    return contacts;
  }
}

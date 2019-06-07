package ru.stqa.pft.addressbook.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;

@XStreamAlias("contact")
@Entity
@Table(name = "addressbook")
public class ContactData {
  @XStreamOmitField
  @Id
  @Column(name = "id")
  private int id = Integer.MAX_VALUE;
  @Column(name = "firstname")
  private String firstname;
  @Column(name = "lastname")
  private String lastname;
  @Column(name = "address")
  @Type(type = "text")
  private String address;
  @Column(name = "home")
  @Type(type = "text")
  private String homePhone;
  @Column(name = "mobile")
  @Type(type = "text")
  private String mobilePhone;
  @Column(name = "work")
  @Type(type = "text")
  private String workPhone;
  @XStreamOmitField
  @Transient
  private String allPhones;
  @Column(name = "email")
  @Type(type = "text")
  private String email;
  @Column(name = "email2")
  @Type(type = "text")
  private String email2;
  @Column(name = "email3")
  @Type(type = "text")
  private String email3;
  @XStreamOmitField
  @Transient
  private String allEmails;
  @Transient
  private String group;
  @XStreamOmitField
  @Column(name = "photo")
  @Type(type = "text")
  private String photo;

  public File getPhoto() {
    return new File(photo);

  }

  public ContactData withPhoto(File photo) {
    this.photo = photo.getPath();
    return this;
  }

  public String getEmail2() {
    return email2;
  }

  public String getEmail3() {
    return email3;
  }

  public String getAllEmails() {
    return allEmails;
  }


  public String getAllPhones() {
    return allPhones;
  }


  public String getFirstname() {
    return firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public String getAddress() {
    return address;
  }

  public String getMobilePhone() {
    return mobilePhone;
  }

  public String getEmail() {
    return email;
  }

  public String getGroup() {
    return group;
  }

  public int getId() {
    return id;
  }

  public String getHomePhone() {
    return homePhone;
  }

  public String getWorkPhone() {
    return workPhone;
  }


  public ContactData withId(int id) {
    this.id = id;
    return this;
  }

  public ContactData withFirstname(String firstname) {
    this.firstname = firstname;
    return this;
  }

  public ContactData withLastname(String lastname) {
    this.lastname = lastname;
    return this;
  }

  public ContactData withAddress(String address) {
    this.address = address;
    return this;
  }

  public ContactData withMobilePhone(String mobilePhone) {
    this.mobilePhone = mobilePhone;
    return this;
  }

  public ContactData withEmail(String email) {
    this.email = email;
    return this;
  }

  public ContactData withGroup(String group) {
    this.group = group;
    return this;
  }

  public ContactData withWorkPhone(String workPhone) {
    this.workPhone = workPhone;
    return this;
  }

  public ContactData withHomePhone(String homePhone) {
    this.homePhone = homePhone;
    return this;
  }

  public ContactData withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }

  public ContactData withEmail2(String email2) {
    this.email2 = email2;
    return this;
  }

  public ContactData withEmail3(String email3) {
    this.email3 = email3;
    return this;
  }

  public ContactData withAllEmails(String allEmails) {
    this.allEmails = allEmails;
    return this;
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id='" + id + '\'' +
            ", firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            '}';
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactData that = (ContactData) o;

    if (id != that.id) return false;
    if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
    return lastname != null ? lastname.equals(that.lastname) : that.lastname == null;

  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
    result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
    return result;
  }
}

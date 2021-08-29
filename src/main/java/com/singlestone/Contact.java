package com.singlestone;


import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;


@Entity
@Table(name="contact")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contact_id")
    private Long id;

    @Embedded
   private Name name;

   @Embedded
   private Address address;

    @OneToMany(mappedBy = "contact", orphanRemoval = true, cascade = CascadeType.ALL)
   private List<PhoneNumber> phone;

   private String email;


    public Contact() {
    }

    public Contact(Name name, Address address, List<PhoneNumber> phone, String email) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

    public Contact(Contact contactJson){
        this.name = contactJson.getName();
        this.address= contactJson.getAddress();
        this.email = contactJson.getEmail();
        this.phone = contactJson.getPhone().stream().
                map(p-> new PhoneNumber(p.getNumber(),p.getType(),this)).collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<PhoneNumber> getPhone() {
        return phone;
    }

    public void setPhone(List<PhoneNumber> phoneNumbers) {
        this.phone = phoneNumbers;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void addPhoneNumber(PhoneNumber phoneNumber){
        phone.add(phoneNumber);
        phoneNumber.setContact(this);
    }

    public void removePhoneNumber(PhoneNumber phoneNumber){
        phone.remove(phoneNumber);
        phoneNumber.setContact(null);
    }
}

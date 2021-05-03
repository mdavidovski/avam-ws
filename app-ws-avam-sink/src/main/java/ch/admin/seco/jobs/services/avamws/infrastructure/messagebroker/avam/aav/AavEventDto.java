package ch.admin.seco.jobs.services.avamws.infrastructure.messagebroker.avam.aav;

import ch.admin.seco.jobs.services.avamws.infrastructure.messagebroker.avam.EventDto;
import ch.admin.seco.jobs.services.avamws.infrastructure.messagebroker.avam.Language;
import ch.admin.seco.jobs.services.avamws.infrastructure.messagebroker.avam.aav.enums.CivilStatus;
import ch.admin.seco.jobs.services.avamws.infrastructure.messagebroker.avam.aav.enums.ContactType;
import ch.admin.seco.jobs.services.avamws.infrastructure.messagebroker.avam.aav.enums.Gender;
import ch.admin.seco.jobs.services.avamws.infrastructure.messagebroker.avam.aav.enums.ResidentialStatus;

import java.time.LocalDate;

public class AavEventDto implements EventDto {

    private String id;

    private Language language;

    private String egovNumber;

    private String jobCenterCode;

    private Gender gender;

    private String firstName;

    private String lastName;

    private String firstNamePostal;

    private String lastNamePostal;

    private String phone;

    private String mobile;

    private String email;

    private Address address;

    private LocalDate birthday;

    private String svNumber;

    private CivilStatus civilStatus;

    private ContactType contactType;

    private LocalDate aavFromDate;

    private String nationality;

    private ResidentialStatus residentialStatus;

    private LocalDate residentialPermissionEndDate;

    public AavEventDto() {
    }

    public String getId() {
        return id;
    }

    public AavEventDto setId(String id) {
        this.id = id;
        return this;
    }

    public Language getLanguage() {
        return language;
    }

    public AavEventDto setLanguage(Language language) {
        this.language = language;
        return this;
    }

    public String getEgovNumber() {
        return egovNumber;
    }

    public AavEventDto setEgovNumber(String egovNumber) {
        this.egovNumber = egovNumber;
        return this;
    }

    public String getJobCenterCode() {
        return jobCenterCode;
    }

    public AavEventDto setJobCenterCode(String jobCenterCode) {
        this.jobCenterCode = jobCenterCode;
        return this;
    }

    public Gender getGender() {
        return gender;
    }

    public AavEventDto setGender(Gender gender) {
        this.gender = gender;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public AavEventDto setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public AavEventDto setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getFirstNamePostal() {
        return firstNamePostal;
    }

    public AavEventDto setFirstNamePostal(String firstNamePostal) {
        this.firstNamePostal = firstNamePostal;
        return this;
    }

    public String getLastNamePostal() {
        return lastNamePostal;
    }

    public AavEventDto setLastNamePostal(String lastNamePostal) {
        this.lastNamePostal = lastNamePostal;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public AavEventDto setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getMobile() {
        return mobile;
    }

    public AavEventDto setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public AavEventDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public Address getAddress() {
        return address;
    }

    public AavEventDto setAddress(Address address) {
        this.address = address;
        return this;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public AavEventDto setBirthday(LocalDate birthday) {
        this.birthday = birthday;
        return this;
    }

    public String getSvNumber() {
        return svNumber;
    }

    public AavEventDto setSvNumber(String svNumber) {
        this.svNumber = svNumber;
        return this;
    }

    public CivilStatus getCivilStatus() {
        return civilStatus;
    }

    public AavEventDto setCivilStatus(CivilStatus civilStatus) {
        this.civilStatus = civilStatus;
        return this;
    }

    public ContactType getContactType() {
        return contactType;
    }

    public AavEventDto setContactType(ContactType contactType) {
        this.contactType = contactType;
        return this;
    }

    public LocalDate getAavFromDate() {
        return aavFromDate;
    }

    public AavEventDto setAavFromDate(LocalDate aavFromDate) {
        this.aavFromDate = aavFromDate;
        return this;
    }

    public String getNationality() {
        return nationality;
    }

    public AavEventDto setNationality(String nationality) {
        this.nationality = nationality;
        return this;
    }

    public ResidentialStatus getResidentialStatus() {
        return residentialStatus;
    }

    public AavEventDto setResidentialStatus(ResidentialStatus residentialStatus) {
        this.residentialStatus = residentialStatus;
        return this;
    }

    public LocalDate getResidentialPermissionEndDate() {
        return residentialPermissionEndDate;
    }

    public AavEventDto setResidentialPermissionEndDate(LocalDate residentialPermissionEndDate) {
        this.residentialPermissionEndDate = residentialPermissionEndDate;
        return this;
    }

    @Override
    public String toString() {
        return "Aav{" +
                "id='" + id + '\'' +
                ", language='" + language + '\'' +
                ", egovNumber='" + egovNumber + '\'' +
                ", jobCenterCode='" + jobCenterCode + '\'' +
                ", gender='" + gender + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", firstNamePostal='" + firstNamePostal + '\'' +
                ", lastNamePostal='" + lastNamePostal + '\'' +
                ", phone='" + phone + '\'' +
                ", mobile='" + mobile + '\'' +
                ", address='" + address +
                ", birthday='" + birthday + '\'' +
                ", svNumber='" + svNumber + '\'' +
                ", civilStatus='" + civilStatus + '\'' +
                ", contactType='" + contactType + '\'' +
                ", aavFromDate='" + aavFromDate + '\'' +
                ", nationality='" + nationality + '\'' +
                ", residentialStatus='" + residentialStatus + '\'' +
                ", residentialPermissionEndDate='" + residentialPermissionEndDate + '\'' +
                '}';
    }

}

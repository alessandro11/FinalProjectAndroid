package com.example.jessielhacke.finalprojectandroid;

/**
 * Created by Jessiel Hacke on 6/21/2015.
 */
import org.brickred.socialauth.util.BirthDate;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


public class User implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String providerId;
    private String validatedId;
    private String firstName;
    private String lastName;
    private String email;
    private String country;
    private String language;
    private String location;
    private String profileImageUrl;
    private String gender;
    private String displayName;
    private String fullName;
    private Map<String, String> skills;
    private Map<String, String> contactInfo;
    private BirthDate birthDate;
    private List<User> friends;


    /**
     * @return the providerId
     */
    public String getProviderId() {
        return providerId;
    }
    /**
     * @param providerId the providerId to set
     */
    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }
    /**
     * @return the validatedId
     */
    public String getValidatedId() {
        return validatedId;
    }
    /**
     * @param validatedId the validatedId to set
     */
    public void setValidatedId(String validatedId) {
        this.validatedId = validatedId;
    }
    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }
    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }
    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }
    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * @return the country
     */
    public String getCountry() {
        return country;
    }
    /**
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }
    /**
     * @return the language
     */
    public String getLanguage() {
        return language;
    }
    /**
     * @param language the language to set
     */
    public void setLanguage(String language) {
        this.language = language;
    }
    /**
     * @return the location
     */
    public String getLocation() {
        return location;
    }
    /**
     * @param location the location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }
    /**
     * @return the profileImageUrl
     */
    public String getProfileImageUrl() {
        return profileImageUrl;
    }
    /**
     * @param profileImageUrl the profileImageUrl to set
     */
    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }
    /**
     * @return the gender
     */
    public String getGender() {
        return gender;
    }
    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }
    /**
     * @return the displayName
     */
    public String getDisplayName() {
        return displayName;
    }
    /**
     * @param displayName the displayName to set
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
    /**
     * @return the fullName
     */
    public String getFullName() {
        return fullName;
    }
    /**
     * @param fullName the fullName to set
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    /**
     * @return the skills
     */
    public Map<String, String> getSkills() {
        return skills;
    }
    /**
     * @param skills the skills to set
     */
    public void setSkills(Map<String, String> skills) {
        this.skills = skills;
    }
    /**
     * @return the birthDate
     */
    public BirthDate getBirthDate() {
        return birthDate;
    }
    /**
     * @param birthDate the birthDate to set
     */
    public void setBirthDate(BirthDate birthDate) {
        this.birthDate = birthDate;
    }
    /**
     * @return the friends
     */
    public List<User> getFriends() {
        return friends;
    }
    /**
     * @param friends the friends to set
     */
    public void setFriends(List<User> friends) {
        this.friends = friends;
    }
    /**
     * @return the contactInfo
     */
    public Map<String, String> getContactInfo() {
        return contactInfo;
    }
    /**
     * @param contactInfo the contactInfo to set
     */
    public void setContactInfo(Map<String, String> contactInfo) {
        this.contactInfo = contactInfo;
    }



}

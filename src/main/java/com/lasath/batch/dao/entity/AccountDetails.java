package com.lasath.batch.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "AccountDetails")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDetails {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "time")
    private Long draftAccountId;
    @Column (name = "activityId")
    private Long activityId;
    @Column (name = "additionalNumber")
    private String additionalNumber;
    @Column (name = "buildingNumber")
    private String buildingNumber;
    @Column (name = "city")
    private Long city;
    @Column (name = "companyIdNumber")
    private String companyIdNumber;
    @Column (name = "companyIdType")
    private String companyIdType;
    @Column (name = "companyName")
    private String companyName;
    @Column (name = "companyType")
    private String companyType;
    @Column (name = "contractNumber")
    private String contractNumber;
    @Column (name = "customerIdType")
    private String customerIdType;
    @Column (name = "customerMobileNumber")
    private String customerMobileNumber;
    @Column (name = "district")
    private String district;
    @Column (name = "email")
    private String email;
    @Column (name = "firstName")
    private String firstName;
    @Column (name = "gender")
    private String gender;
    @Column (name = "idAttachment")
    private String idAttachment;
    @Column (name = "idNumber")
    private String idNumber;
    @Column (name = "idType")
    private String idType;
    @Column (name = "lastName")
    private String lastName;
    @Column (name = "method")
    private String method;
    @Column (name = "phoneNumber")
    private String phoneNumber;
    @Column (name = "postalCode")
    private String postalCode;
    @Column (name = "primaryContactPersonEmail")
    private String primaryContactPersonEmail;
    @Column (name = "primaryContactPersonName")
    private String primaryContactPersonName;
    @Column (name = "primaryContactPersonNumber")
    private String primaryContactPersonNumber;
    @Column (name = "referenceNumber")
    private String referenceNumber;
    @Column (name = "secondName")
    private String secondName;
    @Column (name = "streetName")
    private String streetName;
    @Column (name = "thirdName")
    private String thirdName;
    @Column (name = "type")
    private String type;
    @Column (name = "vatNumber")
    private String vatNumber;
    @Column (name = "otherDistrict")
    private String otherDistrict;
    @Column (name = "referenceName")
    private String referenceName;

    public AccountDetails(final String method, final String type) {
        this.setMethod(method);
        this.setType(type);

    }
}

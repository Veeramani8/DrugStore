package com.drugstore.DTO;

public class DistributorResDTO  {
    private long id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String licenseNumber;
	public DistributorResDTO(long id, String name, String email, String phone, String address,
			String licenseNumber) {
		this.id=id;
		this.name=name;
		this.email=email;
		this.phone=phone;
		this.address=address;
		this.licenseNumber=licenseNumber;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLicenseNumber() {
		return licenseNumber;
	}
	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}
}

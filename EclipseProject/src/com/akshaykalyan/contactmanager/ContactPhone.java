package com.akshaykalyan.contactmanager;

import java.util.Comparator;

public class ContactPhone {
	private String fMobilePhone, fHomePhone, fWorkPhone;
	
	public enum SortBy {
		MobilePhone(1), HomePhone(2), WorkPhone(3);
		Comparator<ContactPhone> fComparator;
		
        private SortBy(int i) {
            switch (i) {
                case 1:
                    fComparator = new Comparator<ContactPhone>() {
                        public int compare(ContactPhone lhs, ContactPhone rhs) {
                            return lhs.fMobilePhone.compareTo(rhs.fMobilePhone);
                        };
                    };
                    break;
                case 2:
                	fComparator = new Comparator<ContactPhone>() {
                        public int compare(ContactPhone lhs, ContactPhone rhs) {
                            return lhs.fHomePhone.compareTo(rhs.fHomePhone);
                        };
                    };
                    break;
                case 3:
                	fComparator = new Comparator<ContactPhone>() {
                        public int compare(ContactPhone lhs, ContactPhone rhs) {
                            return lhs.fWorkPhone.compareTo(rhs.fWorkPhone);
                        };
                    };
                    break;
            }

        }
	}
	
	public ContactPhone(String mobilePhone, String homePhone, String workPhone) {
		this.fMobilePhone = mobilePhone;
		this.fHomePhone = homePhone;
		this.fWorkPhone = workPhone;
	}
	
	public String getHomePhone() {
		return fHomePhone;
	}
	public String getMobilePhone() {
		return fMobilePhone;
	}
	public String getWorkPhone() {
		return fWorkPhone;
	}
	
	public void setHomePhone(String homePhone) {
		this.fHomePhone = homePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.fMobilePhone = mobilePhone;
	}
	public void setWorkPhone(String workPhone) {
		this.fWorkPhone = workPhone;
	}
	
}


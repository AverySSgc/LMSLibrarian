package com.SS.LibrarianMicroService.Entity;

import java.io.Serializable;
import java.util.List;

public class Branch implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 8659784412432270131L;
	private Integer branchId;
    private String branchName;
    private String branchAddress;
//    private List<Loans> loans;
    private List<Copies> copies;

    

    @Override
	public String toString() {
		return "Branch [branchId=" + branchId + ", branchName=" + branchName + ", branchAddress=" + branchAddress
				+  ", copies=" + copies + "]";
	}

	public Integer getBranchId() {
        return branchId;
    }

    public void setBranchId(Integer branchId) {
        this.branchId = branchId;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getBranchAddress() {
        return branchAddress;
    }

    public void setBranchAddress(String branchAddress) {
        this.branchAddress = branchAddress;
    }

//    public List<Loans> getLoans() {
//        return loans;
//    }
//
//    public void setLoans(List<Loans> loans) {
//        this.loans = loans;
//    }

    public List<Copies> getCopies() {
        return copies;
    }

    public void setCopies(List<Copies> copies) {
        this.copies = copies;
    }
}

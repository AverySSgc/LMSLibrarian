package com.SS.LibrarianMicroService.Entity;

import java.io.Serializable;

public class Copies implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -8551843874651755650L;
	private Book book;
    private Branch branch;
    private Integer noOfCopies;
    
    


    @Override
	public String toString() {
		return "Copies [book=" + book + ", branch=" + branch + ", noOfCopies=" + noOfCopies + "]";
	}

	public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public Integer getNoOfCopies() {
        return noOfCopies;
    }

    public void setNoOfCopies(Integer noOfCopies) {
        this.noOfCopies = noOfCopies;
    }
}

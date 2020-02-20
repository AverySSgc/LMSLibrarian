package com.SS.LibrarianMicroService;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.SS.LibrarianMicroService.Entity.Branch;
import com.SS.LibrarianMicroService.Entity.Copies;
import com.SS.LibrarianMicroService.Service.BranchService;

@RestController
public class BranchController {
	@Autowired
	BranchService branchService;
	
	@RequestMapping(path = "librarian/branches", method = RequestMethod.GET)
	public ResponseEntity<List<Branch>> getAllBranches(){
		try {
			List<Branch> branches = branchService.readAllBranches();
			return new ResponseEntity<List<Branch>>(branches,HttpStatus.OK);
			
		}catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return new ResponseEntity<List<Branch>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@RequestMapping(path = "librarian/branches/{branchId}", method = RequestMethod.GET)
	public ResponseEntity<Branch> getBranch(@PathVariable int branchId){
		try {
			Branch branch = branchService.readById(branchId);
			return new ResponseEntity<Branch>(branch,HttpStatus.OK);
		}catch(SQLException e) {
			return new ResponseEntity<Branch>(HttpStatus.NOT_FOUND);
		}catch(ClassNotFoundException e) {
			return new ResponseEntity<Branch>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@RequestMapping(path = "librarian/branches/{branchId}", method = RequestMethod.PUT)
	public ResponseEntity<Branch> updateBranch(@RequestBody Branch branch){
		try {
			branchService.updateBranch(branch);
			return new ResponseEntity<Branch>(branch,HttpStatus.ACCEPTED);
		}catch(SQLException e) {
			return new ResponseEntity<Branch>(HttpStatus.NOT_FOUND);
		}catch(ClassNotFoundException e) {
			return new ResponseEntity<Branch>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
//	@RequestMapping(path = "librarian/branches/{branchId}/copies", method = RequestMethod.GET)
//	public ResponseEntity<List<Copies>> readAllCopiesFromBranch(@PathVariable int branchId){
//		try {
//			List<Copies> copies = branchService.readCopiesFromBranch(branchId);
//			return new ResponseEntity<List<Copies>>(copies,HttpStatus.OK);
//		}catch(SQLException | ClassNotFoundException e) {
//			return new ResponseEntity<List<Copies>>(HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
}

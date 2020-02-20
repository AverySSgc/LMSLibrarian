package com.SS.LibrarianMicroService.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.SS.LibrarianMicroService.DAO.BranchDAO;
import com.SS.LibrarianMicroService.DAO.CopiesDAO;
import com.SS.LibrarianMicroService.Entity.Branch;
import com.SS.LibrarianMicroService.Entity.Copies;

@Component
public class BranchService {
	@Autowired
	BranchDAO bDao;
	@Autowired
	ConnectUtil util;
	@Autowired
	CopiesDAO cDao;
	
	public List<Branch> readAllBranches() throws ClassNotFoundException, SQLException{
		Connection conn = util.getConnection();
		List<Branch> b =bDao.read(conn);
		conn.close();
		return b;
	}
	
	public Branch readById(int id) throws ClassNotFoundException, SQLException {
		Connection conn = util.getConnection();
		Branch b = bDao.readByBranchId(id, conn);
		conn.close();
		return b;
	}
	
	public void updateBranch(Branch branch) throws ClassNotFoundException, SQLException {
		Connection conn = util.getConnection();
		bDao.update(branch, conn);
		conn.commit();
		conn.close();
	}
	public List<Copies> readCopiesFromBranch(int branchId) throws ClassNotFoundException, SQLException{
		Connection conn = util.getConnection();
		List<Copies> copies = cDao.readByBranchId(branchId, conn);
		conn.close();
		return copies;
	}
}

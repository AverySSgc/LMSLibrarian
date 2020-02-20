package com.SS.LibrarianMicroService.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.SS.LibrarianMicroService.Entity.Branch;

@Component
public class BranchDAO extends DAO<Branch> {
	@Autowired
	CopiesDAO cdao;

    @Override
    protected List<Branch> extractData(ResultSet rs, Connection conn) throws SQLException {
        List<Branch> branches = new ArrayList<>();
        while(rs.next()){
            Branch b = new Branch();
            b.setBranchId(rs.getInt("branchId"));
            b.setBranchName(rs.getString("branchName"));
            b.setBranchAddress(rs.getString("branchAddress"));
//            CopiesDOA cdoa = new CopiesDOA(conn);
            try {
            b.setCopies(cdao.readByBranchId(b.getBranchId(),conn));
            }catch(Exception e) {}
            //setup loans
//            LoanDAO ldao = new LoanDAO(conn);
//            b.setLoans(ldao.readByBranchId(b.getBranchId(),conn));
            branches.add(b);
        }
        return branches;
    }

    @Override
    protected List<Branch> extractEssentialData(ResultSet rs, Connection conn) throws SQLException {
        List<Branch> branches = new ArrayList<>();
        while(rs.next()){
            Branch b = new Branch();
            b.setBranchId(rs.getInt("branchId"));
            b.setBranchName(rs.getString("branchName"));
            b.setBranchAddress(rs.getString("branchAddress"));
            branches.add(b);
        }
        return branches;
    }


    @Override
    public Integer add(Branch object,Connection conn) throws SQLException {
        return saveRecieveKey("insert into tbl_library_branch (branchName, branchAddress) values (?,?)",
                new Object[]{object.getBranchName(),object.getBranchAddress()},conn);
    }

    @Override
    public void update(Branch object,Connection conn) throws SQLException {
        save("update tbl_library_branch set branchName = ? , branchAddress = ? where branchId = ?",
                new Object[]{object.getBranchName(),object.getBranchAddress(),object.getBranchId()},conn);
    }

    @Override
    public void delete(Branch object,Connection conn) throws SQLException {
        save("delete from tbl_library_branch where branchId = ?", new Object[]{object.getBranchId()},conn);
    }

    @Override
    public List<Branch> read(Connection conn) throws SQLException {
        return read("select * from tbl_library_branch", null,conn);
    }

     public Branch readByBranchId(int branchId,Connection conn) throws SQLException {
        List<Branch> b = read("select * from tbl_library_branch where branchId = ?", new Object[]{branchId},conn);
        return b.get(0);
     }

    public Branch readByBranchIdEssentialData(int branchId,Connection conn) throws SQLException {
        List<Branch> b = readEssential("select * from tbl_library_branch where branchId = ?", new Object[]{branchId},conn);
        return b.get(0);
    }
}

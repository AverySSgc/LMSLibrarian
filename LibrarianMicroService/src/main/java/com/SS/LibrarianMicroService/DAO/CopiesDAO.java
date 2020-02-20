package com.SS.LibrarianMicroService.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.SS.LibrarianMicroService.Entity.Copies;

@Component
public class CopiesDAO extends DAO<Copies> {
	@Autowired
	BranchDAO branDao;
	@Autowired
	BookDAO bdao;
    
    @Override
    protected List<Copies> extractData(ResultSet rs, Connection conn) throws SQLException {
        List<Copies> copies = new ArrayList<>();
        while (rs.next()){
            Copies c = new Copies();
            c.setBook(bdao.readByBookId(rs.getInt("bookId"),conn));
//            BranchDAO branch = new BranchDAO(conn);
            c.setBranch(branDao.readByBranchIdEssentialData(rs.getInt("branchId"),conn));
            c.setNoOfCopies(rs.getInt("noOfCopies"));
            copies.add(c);
        }
        return copies;
    }

    @Override
    protected List<Copies> extractEssentialData(ResultSet rs,Connection conn) throws SQLException {

        return extractData(rs,conn);
    }

    @Override
    public Integer add(Copies object,Connection conn) throws SQLException {
        save("insert into tbl_book_copies (bookId, branchId, noOfCopies) values (?,?,?)",
            new Object[]{object.getBook().getBookId(),object.getBranch().getBranchId(),object.getNoOfCopies()},conn);
        return null;
    }

    @Override
    public void update(Copies object,Connection conn) throws SQLException {
        save("update tbl_book_copies set noOfCopies = ? where bookId = ? && branchId = ?",
                new Object[]{object.getNoOfCopies(),object.getBook().getBookId(),object.getBranch().getBranchId()},conn);
    }

    @Override
    public void delete(Copies object, Connection conn) throws SQLException {
        save("delete from tbl_book_copies where bookId = ? && branchId = ?",
                new Object[]{object.getBook().getBookId(),object.getBranch().getBranchId()},conn);
    }

    @Override
    public List<Copies> read(Connection conn) throws SQLException {
        return read("select * from tbl_book_copies",null,conn);
    }

    public List<Copies> readByBranchId(int branchId, Connection conn) throws SQLException {
        return readEssential("select * from tbl_book_copies where branchId = ?",new Object[]{branchId},conn);
    }

    public List<Copies> readByBookId(int bookId, Connection conn) throws SQLException {
        return read("select * from tbl_books_copies where bookId = ?", new Object[]{bookId},conn);
    }
}

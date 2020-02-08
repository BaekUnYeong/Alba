package kr.or.ddit.buyer.dao;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PagingVO;

public class BuyerDAOImplTest {

	IBuyerDAO buyerDAO = new BuyerDAOImpl();
	
	PagingVO<BuyerVO> pagingVO;

	@Before
	public void setUp() throws Exception {
		pagingVO = new PagingVO<>();
		pagingVO.setCurrentPage(1);
	}

	@Test
	public void testSelectBuyerCount() {
		int rowCnt = buyerDAO.selectBuyerCount(pagingVO);
		assertNotEquals(0, rowCnt);
	}

	@Test
	public void testSelectBuyerList() {
		List<BuyerVO> buyerList = buyerDAO.selectBuyerList(pagingVO);
		assertNotEquals(0, buyerList.size());
	}

}

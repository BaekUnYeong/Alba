package kr.or.ddit.board.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SearchVO;

public class BoardDAOImplTest {
	IBoardDAO boardDAO = new BoardDAOImpl();

	PagingVO<BoardVO> pagingVO;
	BoardVO board;
	
	@Before
	public void setUp() throws Exception {
		pagingVO = new PagingVO<BoardVO>();
		pagingVO.setCurrentPage(1);
		pagingVO.setSearchVO(new SearchVO("title", "이은대"));
		
		board = new BoardVO();
		board.setBo_title("제목");
		board.setBo_writer("작성자");
		board.setBo_pass("1111");
		board.setBo_ip("111.111.111.111");
		board.setBo_content("내용");
	}

	@Test
	public void testSelectBoardList() {
		List<BoardVO> boardList = boardDAO.selectBoardList(pagingVO);
		assertNotEquals(0, boardList.size());
	}

	@Test
	public void testSelectBoardCount() {
		int totalRecord = boardDAO.selectBoardCount(pagingVO);
		assertNotEquals(0, totalRecord);
	}

	@Test
	public void testSelectBoard() {
		BoardVO board =	boardDAO.selectBoard(400);
		assertNotNull(board);
	}

	@Test(timeout = 1000)
	public void testIncrementHit() {
		boardDAO.incrementHit(400);
	}

	@Test
	public void testInsertBoard() {
		int rowcnt = boardDAO.insertBoard(board);
		assertEquals(1, rowcnt);
	}

	@Test
	public void testUpdateBoard() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteBoard() {
		fail("Not yet implemented");
	}

	@Test
	public void testIncrementLike() {
		int rowcnt = boardDAO.incrementLike(400);
		assertEquals(rowcnt, 1);
	}
}

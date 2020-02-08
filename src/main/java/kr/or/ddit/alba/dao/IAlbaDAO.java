package kr.or.ddit.alba.dao;

import java.util.List;

import kr.or.ddit.vo.AlbaVO;
import kr.or.ddit.vo.PagingVO;


public interface IAlbaDAO {
	public int selectAlbaCount(PagingVO<AlbaVO> pagingVO);
	public List<AlbaVO> selectAlbaList(PagingVO<AlbaVO> pagingVO);
	public AlbaVO selectAlba(String al_id);
	public int insertAlba(AlbaVO alba);
	public int updateAlba(AlbaVO alba);
}

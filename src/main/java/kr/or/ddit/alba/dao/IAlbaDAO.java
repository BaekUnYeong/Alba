package kr.or.ddit.alba.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.vo.AlbaVO;
import kr.or.ddit.vo.LicenseVO;
import kr.or.ddit.vo.PagingVO;


public interface IAlbaDAO {
	public int selectAlbaCount(PagingVO<AlbaVO> pagingVO);
	public List<AlbaVO> selectAlbaList(PagingVO<AlbaVO> pagingVO);
	public AlbaVO selectAlba(String al_id);
	public int insertAlba(AlbaVO alba);
	public int updateAlba(AlbaVO alba);
	public int deleteAlba(AlbaVO alba);
	public int uploadLicense(LicenseVO license);
	public int insertAlba(AlbaVO alba, SqlSession sqlSession);
	public int updateAlba(AlbaVO alba, SqlSession sqlSession);
	public int uploadLicense(LicenseVO license, SqlSession sqlSession);
}

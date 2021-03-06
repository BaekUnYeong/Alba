package kr.or.ddit.alba.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.mybatis.CustomSqlSessioFactoryBuilder;
import kr.or.ddit.vo.AlbaVO;
import kr.or.ddit.vo.LicenseVO;
import kr.or.ddit.vo.PagingVO;

public class AlbaDAOImpl implements IAlbaDAO {
	SqlSessionFactory sqlSessionFactory =
			CustomSqlSessioFactoryBuilder.getSqlSessionFactory();
	
	@Override
	public int selectAlbaCount(PagingVO<AlbaVO> pagingVO) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();
		) {
			IAlbaDAO mapper = sqlSession.getMapper(IAlbaDAO.class);
			return mapper.selectAlbaCount(pagingVO);
		}
	}

	@Override
	public List<AlbaVO> selectAlbaList(PagingVO<AlbaVO> pagingVO) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();
		) {
			IAlbaDAO mapper = sqlSession.getMapper(IAlbaDAO.class);
			return mapper.selectAlbaList(pagingVO);
		}
	}

	@Override
	public AlbaVO selectAlba(String al_id) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();
		) {
			IAlbaDAO mapper = sqlSession.getMapper(IAlbaDAO.class);
			return mapper.selectAlba(al_id);
		}
	}

	@Override
	public int insertAlba(AlbaVO alba) {
		return 0;
	}
	
	@Override
	public int insertAlba(AlbaVO alba, SqlSession sqlSession) {
		IAlbaDAO mapper = sqlSession.getMapper(IAlbaDAO.class);
		return mapper.insertAlba(alba);
	}
	
	@Override
	public int updateAlba(AlbaVO alba) {
		return 0;
	}

	@Override
	public int updateAlba(AlbaVO alba, SqlSession sqlSession) {
		IAlbaDAO mapper = sqlSession.getMapper(IAlbaDAO.class);
		return mapper.updateAlba(alba);
	}

	@Override
	public int deleteAlba(AlbaVO alba) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();
		){
			IAlbaDAO mapper = sqlSession.getMapper(IAlbaDAO.class);
			return mapper.deleteAlba(alba);
		}
	}

	@Override
	public int uploadLicense(LicenseVO license) {
		return 0;
	}
	
	@Override
	public int uploadLicense(LicenseVO license, SqlSession sqlSession) {
		IAlbaDAO mapper = sqlSession.getMapper(IAlbaDAO.class);
		return mapper.uploadLicense(license);
	}
}

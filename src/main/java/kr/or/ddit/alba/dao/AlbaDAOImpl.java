package kr.or.ddit.alba.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.mybatis.CustomSqlSessioFactoryBuilder;
import kr.or.ddit.vo.AlbaVO;
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
	public int insertAlba(AlbaVO alba) {//insert 수정 필요 TODO
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();
		) {
			IAlbaDAO mapper = sqlSession.getMapper(IAlbaDAO.class);
			return mapper.insertAlba(alba);
		}
	}

	@Override
	public int updateAlba(AlbaVO alba) {//update 수정 필요 TODO
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();
		) {
			IAlbaDAO mapper = sqlSession.getMapper(IAlbaDAO.class);
			return mapper.updateAlba(alba);
		}
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

}

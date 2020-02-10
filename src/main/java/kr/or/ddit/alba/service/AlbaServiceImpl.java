package kr.or.ddit.alba.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.alba.dao.AlbaDAOImpl;
import kr.or.ddit.alba.dao.IAlbaDAO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.mybatis.CustomSqlSessioFactoryBuilder;
import kr.or.ddit.vo.AlbaVO;
import kr.or.ddit.vo.PagingVO;

public class AlbaServiceImpl implements IAlbaService {
	IAlbaDAO albaDAO = new AlbaDAOImpl();
	
	SqlSessionFactory sqlSessionFactory =
			CustomSqlSessioFactoryBuilder.getSqlSessionFactory();
	
	@Override
	public int readAlbaCount(PagingVO<AlbaVO> pagingVO) {
		return albaDAO.selectAlbaCount(pagingVO);
	}

	@Override
	public List<AlbaVO> readAlbaList(PagingVO<AlbaVO> pagingVO) {
		return albaDAO.selectAlbaList(pagingVO);
	}

	@Override
	public AlbaVO readAlba(String al_id) {
		return albaDAO.selectAlba(al_id);
	}

	@Override
	public ServiceResult createAlba(AlbaVO alba) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();
		){
			int rowcnt = albaDAO.insertAlba(alba, sqlSession);
			ServiceResult result = ServiceResult.FAIL;
			if(rowcnt>0) {
				albaDAO.uploadLicense(alba.getLicense(), sqlSession);
				result = ServiceResult.OK;
				sqlSession.commit();
			}
			return result;
		}
	}

	@Override
	public ServiceResult modifyAlba(AlbaVO alba) {
		try(
				SqlSession sqlSession = sqlSessionFactory.openSession();
			){
				int rowcnt = albaDAO.updateAlba(alba, sqlSession);
				ServiceResult result = ServiceResult.FAIL;
				if(rowcnt>0) {
					albaDAO.uploadLicense(alba.getLicense(), sqlSession);
					result = ServiceResult.OK;
					sqlSession.commit();
				}
				return result;
			}
	}

	@Override
	public ServiceResult deleteAlba(AlbaVO alba) {
		ServiceResult result = ServiceResult.FAIL;
		int rownum = albaDAO.deleteAlba(alba);
		if(rownum > 0) result = ServiceResult.OK;
		return result;
	}
	
}

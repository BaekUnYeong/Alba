package kr.or.ddit.alba.service;

import java.util.List;

import kr.or.ddit.alba.dao.AlbaDAOImpl;
import kr.or.ddit.alba.dao.IAlbaDAO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.AlbaVO;
import kr.or.ddit.vo.PagingVO;

public class AlbaServiceImpl implements IAlbaService {
	IAlbaDAO dao = new AlbaDAOImpl();

	IAlbaDAO albaDAO = new AlbaDAOImpl();
	
	@Override
	public int readAlbaCount(PagingVO<AlbaVO> pagingVO) {
		return 0;
	}

	@Override
	public List<AlbaVO> readAlbaList(PagingVO<AlbaVO> pagingVO) {
		return null;
	}

	@Override
	public AlbaVO readAlba(String al_id) {
		return null;
	}

	@Override
	public ServiceResult createAlba(AlbaVO alba) {
		return null;
	}

	@Override
	public ServiceResult modifyAlba(AlbaVO alba) {
		return null;
	}

	@Override
	public ServiceResult deleteAlba(AlbaVO alba) {
		ServiceResult result = ServiceResult.FAIL;
		int rownum = albaDAO.deleteAlba(alba);
		if(rownum > 0) result = ServiceResult.OK;
		return result;
	}
	
}

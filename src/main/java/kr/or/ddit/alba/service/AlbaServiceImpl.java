package kr.or.ddit.alba.service;

import java.util.List;

import kr.or.ddit.alba.dao.AlbaDAOImpl;
import kr.or.ddit.alba.dao.IAlbaDAO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.AlbaVO;
import kr.or.ddit.vo.PagingVO;

public class AlbaServiceImpl implements IAlbaService {

	IAlbaDAO albaDAO = new AlbaDAOImpl();
	
	@Override
	public int readAlbaCount(PagingVO<AlbaVO> pagingVO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<AlbaVO> readAlbaList(PagingVO<AlbaVO> pagingVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AlbaVO readAlba(String al_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResult createAlba(AlbaVO alba) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResult modifyAlba(AlbaVO alba) {
		// TODO Auto-generated method stub
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

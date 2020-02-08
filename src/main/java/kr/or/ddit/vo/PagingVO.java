package kr.or.ddit.vo;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PagingVO<T> {


	public PagingVO(int screenSize, int blockSize) {
		super();
		this.screenSize = screenSize;
		this.blockSize = blockSize;
	}

	private int screenSize = 10;	// 한페이지당 리스트 수
	private int blockSize = 5; 	// 한 화면에 보여줄 페이지 수
	private int currentPage;// 현재 페이지 수
	
	private int totalRecord;// 총 게시물 수
	private int totalPage; // 총 페이지 수
	
	// 아래 4개(startRow, endRow, startPage, endPage)는 currentPage가 결정이 되어야 결정됨
	private int startRow; // 시작 게시물 번호
	private int endRow; // 종료 게시물 번호
	
	private int startPage;// 시작페이지
	private int endPage; // 종료페이지
	
	private List<T> dataList;
	private SearchVO searchVO; // has a 관계, 일반 검색에서 사용
	
	private T searchDetail; // 상세 검색에서 사용, 제한을 두지 않는다.
	
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
		totalPage = (int)Math.ceil(totalRecord/(double)screenSize);
	}
	
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
		endRow = screenSize * currentPage;
		startRow = endRow - (screenSize - 1);
		endPage = blockSize * ((currentPage+(blockSize-1))/blockSize);
		startPage = endPage - (blockSize-1);
	}
	
	public String getPagingHTML() {
		StringBuffer html = new StringBuffer();
		html.append("<nav aria-label='...'>\n");
		html.append("<ul class='pagination'>\n");
	 	int lastPage = endPage>totalPage ?
	 						totalPage : endPage;
	 	if(startPage>blockSize){
	 		html.append("<li class='page-item'>\n");
	 		html.append("<a data-page=' \"+(startPage-blockSize)+\"' class='page-link' href='?page="+(startPage-blockSize)+"' tabindex='-1' aria-disabled='true'>Previous</a>\n");
	 		html.append("</li>\n");
	 	}
	 	for(int i=startPage; i<=lastPage; i++){
	 		if(i==currentPage){
	 			html.append("<li class='page-item active' aria-current='page'>\n");
	 			html.append("<a data-page='" +i+ "' class='page-link' href='#'>"+i+"<span class='sr-only'>(current)</span></a>\n");
	 			html.append("</li>\n");
	 		}else{
	 			html.append("<li class='page-item'>\n");
	 			html.append("<a data-page='" +i+"' class='page-link' href='?page="+i+"'>"+i+"</a>\n");
	 			html.append("</li>\n");
	 		}
	 	}
	 	if(lastPage<totalPage){
	 		html.append("<li class='page-item'>\n");
	 		html.append("<a data-page='" +(lastPage + 1)+ "' class='page-link' href='?page="+(lastPage + 1)+"'>Next</a>\n");
	 		html.append("</li>\n");
	 	}
	 	html.append("</ul>\n");
	 	html.append("</nav>\n");
	 	return html.toString();
	}
}
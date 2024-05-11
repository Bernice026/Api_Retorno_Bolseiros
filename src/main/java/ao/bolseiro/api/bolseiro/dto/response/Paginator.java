package ao.bolseiro.api.bolseiro.dto.response;

public class Paginator {
	private Integer pageNumber;
	private long totalElements;
	private Integer totalPages;

	public Paginator() {
	}
	
	public Paginator(Integer pageNumber, long totalElements, Integer totalPages) {
		this.pageNumber = pageNumber;
		this.totalElements = totalElements;
		this.totalPages = totalPages;
	}
	public Integer getPageNumber() {
		return pageNumber;
	}
	
	public long getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(long totalElements) {
		this.totalElements = totalElements;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public void setTotalElements(Integer totalElements) {
		this.totalElements = totalElements;
	}
	public Integer getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}
}

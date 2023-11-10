package com.ssafit.util;

public class Pagination {
    private int currentPage; // 현재 페이지 번호
    private int totalItemCount; // 전체 글 개수 = 마지막 글 번호
    private int itemPerPage = 10; // 한 페이지에 보여줄 글 개수
    private int pagePerGroup = 5; // 한번에 보여줄 페이지 번호 개수

    public void setItemPerPage(int itemPerPage) {
        this.itemPerPage = itemPerPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public void setTotalItemCount(int totalItemCount) {
        this.totalItemCount = totalItemCount;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getTotalItemCount() {
        return totalItemCount;
    }

    public int getItemPerPage() {
        return itemPerPage;
    }

    public Pagination(int currentPage, int totalItemCount){
        this.currentPage = currentPage;
        this.totalItemCount = totalItemCount;
    }

    public int getTotalPageCount(){
        return (this.totalItemCount-1)/itemPerPage +1;
    }

    public int getStartItemNum(){
        return (this.currentPage - 1)*itemPerPage +1;
    }

    public int getEndItemNum(){
//        return (this.currentPage -1)*itemPerPage + itemPerPage;
        return Math.min(this.currentPage *itemPerPage, totalItemCount);

    }

    public int getCurrentGroup(){
        return (this.currentPage - 1) / pagePerGroup + 1;
    }

    public int getStartPageNum(){
        return (getCurrentGroup() -1) * pagePerGroup + 1;
    }


    public int getEndPageNum(){
        return Math.min(getCurrentGroup() * pagePerGroup, getTotalPageCount()) ;
    }

    public boolean getPreviousButtonActive(){
        return currentPage > 1;
    }

    public boolean getNextButtonActive() {
        return currentPage < getTotalPageCount();
    }

}

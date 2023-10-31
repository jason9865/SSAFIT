package com.ssafit.util;

public class PaginationTest {
    public static void main(String[] args) {
        int currentPage =13;
        int totalItemCount = 136;
        Pagination p = new Pagination(currentPage, totalItemCount);

        String sql = "SELECT * FROM article LIMIT #{itemPerPage} OFFSET #{(currentPage -1)* itemPerPage}";

        System.out.printf("current Page: %d\n", p.getCurrentPage());
        System.out.printf("last Page: %d\n", p.getTotalPageCount());
        System.out.println("start item num:" + p.getStartItemNum());
        System.out.println("end item num:"+p.getEndItemNum());
        System.out.println("current group: "+p.getCurrentGroup());

        if(p.getPreviousButtonActive())
            System.out.print("[<] ");

        for(int i=p.getStartPageNum(); i <= p.getEndPageNum(); i++){
            if(i==p.getCurrentPage())
                System.out.printf("[*%d] ",i);
            else
                System.out.printf("[%d] ",i);
        }

        if(p.getNextButtonActive())
            System.out.println("[>]");

        System.out.println();
    }
}

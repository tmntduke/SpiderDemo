package Model;

import java.io.Serializable;
import java.util.List;

public class AnswerInfo implements Serializable {


    /**
     * id : 0
     * page_id : 1
     * num : 2
     * content : [{"c_id":11,"content":"thtntth"},{"c_id":12,"content":"thtntth"}]
     */

    private int id;
    private int page_id;
    private int num;
    private List<ContentBean> content;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPage_id() {
        return page_id;
    }

    public void setPage_id(int page_id) {
        this.page_id = page_id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public List<ContentBean> getContent() {
        return content;
    }

    public void setContent(List<ContentBean> content) {
        this.content = content;
    }

    public static class ContentBean {
        /**
         * c_id : 11
         * content : thtntth
         */

        private int c_id;
        private String content;

        public int getC_id() {
            return c_id;
        }

        public void setC_id(int c_id) {
            this.c_id = c_id;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}

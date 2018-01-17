package com.issc.ding.util;


import java.util.List;

public class QueryResultInfo {
    private int error_code;
    private String reason;
    private List<Result> result;

    public QueryResultInfo(int error_code, String reason, List<Result> result) {
        this.error_code = error_code;
        this.reason = reason;
        this.result = result;
    }


    public List<Result> getResult() {
        return result;
    }

    /*public void setResult(List<Result> result) {
        this.result = result;
    }*/

    public class Result {

        private String content;
        private String hashId;
        private int unixtime;
        private String updatetime;

        public Result(String content, String hashId, int unixtime, String updatetime) {
            this.content = content;
            this.hashId = hashId;
            this.unixtime = unixtime;
            this.updatetime = updatetime;
        }

        public String getContent() {
            return content;
        }

       /* public void setContent(String content) {
            this.content = content;
        }*/

        @Override
        public String toString() {
            return "Result [content=" + content + ", hashId=" + hashId + ", unixtime=" + unixtime + ", updatetime=" + updatetime+",error_code="+error_code+",reason="+reason+"]";
        }

    }

    @Override
    public String toString() {
        return "QueryResultInfo [ result=" + result.toString() + "]";
    }

}




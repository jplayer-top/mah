package top.jplayer.baseprolibrary.mvp.model.bean;

import java.util.List;

/**
 * Created by Obl on 2018/1/13.
 * top.jplayer.baseprolibrary.mvp.model.bean
 */

public class SampleBean {

    /**
     * errorCode : 0000
     * data : {"list":[],"now":"2018-01-17 14:14:27","isTwo":{"hadNum":0,"maxNum":2,"validate":1}}
     */

    public String errorCode;
    public DataBean data;

    public static class DataBean {
        /**
         * list : []
         * now : 2018-01-17 14:14:27
         * isTwo : {"hadNum":0,"maxNum":2,"validate":1}
         */

        public String now;
        public IsTwoBean isTwo;
        public List<?> list;

        public static class IsTwoBean {
            /**
             * hadNum : 0
             * maxNum : 2
             * validate : 1
             */

            public int hadNum;
            public int maxNum;
            public int validate;
        }
    }
}

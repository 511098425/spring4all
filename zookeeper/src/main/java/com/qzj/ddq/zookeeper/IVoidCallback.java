package com.qzj.ddq.zookeeper;

import org.apache.zookeeper.AsyncCallback;

/**
 * Created by Administrator on 2017/7/27.
 */
public class IVoidCallback implements AsyncCallback.VoidCallback {
    @Override
    public void processResult(int i, String s, Object o) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("i=").append(i).append("\n");
        stringBuffer.append("s=").append(s).append("\n");
        stringBuffer.append("o=").append(o);
        System.out.println(stringBuffer.toString());
    }
}

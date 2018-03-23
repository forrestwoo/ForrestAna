package com.forrest.utils;

import java.util.ArrayList;
import java.util.List;

import com.forrest.model.IPMessage;


public class IPFilter {
	 //对IP进行过滤
    public static List<IPMessage> Filter(List<IPMessage> ipMessages1) {
        List<IPMessage> newIPMessages = new ArrayList<>();

        for (int i = 0; i < ipMessages1.size(); i++) {
            String ipType = ipMessages1.get(i).getIPType();
            String ipSpeed = ipMessages1.get(i).getIPSpeed();

            ipSpeed = ipSpeed.substring(0, ipSpeed.indexOf('秒'));
            double Speed = Double.parseDouble(ipSpeed);

            if (ipType.equals("HTTPS") && Speed <= 2.0) {
                newIPMessages.add(ipMessages1.get(i));
            }
        }

        return newIPMessages;
    }
}

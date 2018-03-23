package com.forrest.utils;

import static java.lang.System.out;

import java.util.ArrayList;
import java.util.List;

import com.forrest.model.IPMessage;

public class IPPool {
	//��Ա���������̰߳�ȫ��
    private List<IPMessage> ipMessages;

    public IPPool(List<IPMessage> ipMessages) {
        this.ipMessages = ipMessages;
    }

    public void getIP(List<String> urls) {
        String ipAddress;
        String ipPort;

        for (int i = 0; i < urls.size(); i++) {
            /** �����ѡ����IP(��ϸ�����룬���������������߳��п�����λ��ȷ��֮���ipMessages��������
             * ���ӣ���˵����ı��Ѿ�ѡ���ip�����λ�ã�������������ڶԹ���������ж�д��ʱ��Ҫ��֤
             * ��ԭ���ԣ������׷������)
             */
            //ÿ���߳��Ƚ��Լ�ץȡ������ip�������������й�������
            List<IPMessage> ipMessages1 = new ArrayList<>();
            String url = urls.get(i);

            synchronized (ipMessages) {
                int rand = (int) (Math.random()*ipMessages.size());
                out.println("��ǰ�߳� " + Thread.currentThread().getName() + " randֵ: " + rand +
                        " ipMessages ��С: " + ipMessages.size());

                ipAddress = ipMessages.get(rand).getIPAddress();
                ipPort = ipMessages.get(rand).getIPPort();
            }

            //����Ҫע��Java�зǻ������͵Ĳ������ݷ�ʽ��ʵ���϶���ͬһ������
            boolean status = URLFecter.urlParse(url, ipAddress, ipPort, ipMessages1);
            //���ip����������ip�����ã����л���һ��IP�Ա�ҳ��������ץȡ
            if (status == false) {
                i--;
                continue;
            } else {
                out.println("�̣߳�" + Thread.currentThread().getName() + "�ѳɹ�ץȡ " +
                url + " ipMessage1��" + ipMessages1.size());
            }

            //��ip���½��й��ˣ�ֻҪ�ٶ����������ڵĲ�������ΪHTTPS��
            ipMessages1 = IPFilter.Filter(ipMessages1);

            //��ip����������⣬���������ϸ��ip��List�����ɾ��
            IPUtils.IPIsable(ipMessages1);

            //�������ϸ��ip�ϲ����������ipMessages�У����кϲ���ʱ��֤ԭ����
            synchronized (ipMessages) {
                out.println("�߳�" + Thread.currentThread().getName() + "�ѽ���ϲ��� " +
                "���ϲ���С ipMessages1��" + ipMessages1.size());
                ipMessages.addAll(ipMessages1);
            }
        }
    }

}

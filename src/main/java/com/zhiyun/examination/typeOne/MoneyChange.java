package com.zhiyun.examination.typeOne;

/**
 * @Title: MoneyChange
 * @ProjectName: examination
 * @Description: TODO
 * @author: jiangxing
 * @date 2019/4/1811:19
 */
public class MoneyChange {
    //金额转换，阿拉伯数字的金额转换成中国传统的形式如：（￥1011）－>（一千零一拾一元整）输出。
    private static final char[] data = {'零', '壹', '贰', '叁', '肆',
            '伍', '陆', '柒', '捌', '玖'
    };
    private static final char[] untils = {'元', '拾', '佰', '仟', '万',
            '拾', '佰', '仟', '亿'};

    public static void main(String[] args) {
        int num=1000;
        System.err.println("大写中文:"+changeFormat(num));

    }

    private static String changeFormat(int number) {

        StringBuffer sb=new StringBuffer();

        int unit=0;

        while (number!=0){
            sb.insert(0, untils[unit ++]);
            int num = number % 10;
            sb.insert(0, data[num]);
            number /= 10;

        }
        return sb.toString().replaceAll("零[拾佰仟]","零").
                replaceAll("零+万","万").replaceAll("零+元","元").replaceAll("零+","零");
        //去零关键代码

    }
}

package com.barnettwong.basepro.mvp.ui.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Base64;
import android.widget.EditText;

import com.blankj.utilcode.util.StringUtils;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.WHITE;
import static java.util.regex.Pattern.compile;

public class UtilsTool {


    /**
     * ”^((13[0-9])|(14[5,7,9])|(15[^4])|(18[0-9])|(17[0,1,3,5,6,7,8]))\d{8}$”这句话其实很简单：
     * <p>
     * ①130-139这十个前三位已经全部开通，后面8位每一位都是0-9之间的任意数；
     * <p>
     * ②14开头的目前只有145、147、149三位，后面8位每一位都是0-9之间的任意数；
     * <p>
     * ③15开头的除了154以外第三位可以随意取，后面8位每一位都是0-9之间的任意数；
     * <p>
     * ④180-189这十个前三位已经全部开通，后面8位每一位都是0-9之间的任意数；
     * <p>
     * ⑤17开头的目前有170、171、173、175、176、177、178这七位，后面8位每一位都是0-9之间的任意数；
     *
     * @param mobileNums
     * @return
     */
    public static boolean isMobileNO(String mobileNums) {
        /**
         * 判断字符串是否符合手机号码格式
         * 移动号段: 134,135,136,137,138,139,147,150,151,152,157,158,159,170,178,182,183,184,187,188
         * 联通号段: 130,131,132,145,155,156,170,171,175,176,185,186
         * 电信号段: 133,149,153,170,173,177,180,181,189
         * @param str
         * @return 待检测的字符串
         */
        String telRegex = "^1(3[0-9]|4[579]|5[0-3,5-9]|6[6]|7[0135678]|8[0-9]|9[89])\\d{8}$";// "[1]"代表下一位为数字可以是几，"[0-9]"代表可以为0-9中的一个，"[5,7,9]"表示可以是5,7,9中的任意一位,[^4]表示除4以外的任何一个,\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(mobileNums))
            return false;
        else
            return mobileNums.matches(telRegex);
    }


    /**
     * 禁止EditText输入空格
     *
     * @param editText
     */
    public static void setEditTextInhibitInputSpace(EditText editText) {
        InputFilter filter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
//
                if (source.equals(" ")) return "";
                else return null;
            }
        };
        editText.setFilters(new InputFilter[]{filter});
    }

    /**
     * 禁止EditText输入特殊字符
     *
     * @param editText
     */
    public static void setEditTextInhibitInputSpeChat(EditText editText) {

        InputFilter filter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                String speChat = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
                Pattern pattern = compile(speChat);
                Matcher matcher = pattern.matcher(source.toString());
                if (matcher.find()) return "";
                else return null;
            }
        };
        editText.setFilters(new InputFilter[]{filter});
    }

    //支持数字、字母, 字符6-20位,必须包含其中至少两种

    public static boolean pwdCheck(String pwd) {
        String pwdRegex = "^(?=.*[a-zA-Z0-9].*)(?=.*[a-zA-Z\\W].*)(?=.*[0-9\\W].*).{6,20}$";
        if (TextUtils.isEmpty(pwd)) {
            return false;
        } else {
            return pwd.matches(pwdRegex);
        }
    }

    /**
     * 将double格式化为指定小数位的String，不足小数位用0补全
     *
     * @param v     需要格式化的数字
     * @param scale 小数点后保留几位
     * @return
     */
    public static String roundByScale(double v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The   scale   must   be   a   positive   integer   or   zero");
        }
        if (scale == 0) {
            return new DecimalFormat("#0").format(v);
        }
        String formatStr = "#0.";
        for (int i = 0; i < scale; i++) {
            formatStr = String.format("%s0", formatStr);
        }
        return new DecimalFormat(formatStr).format(v);
    }


    /**
     * double 转百分数
     *
     * @param rate
     * @return
     */
    public static String doubleToNumber(double rate) {
        NumberFormat num = NumberFormat.getPercentInstance();
        num.setMaximumFractionDigits(2);
        String rates = num.format(rate);
        return rates;
    }

    /**
     * double 转百分数
     *
     * @param rate
     * @param scal 百分数保留位数
     * @return
     */
    public static String doubleToNumber(double rate, int scal) {
        NumberFormat num = NumberFormat.getPercentInstance();
        num.setMaximumFractionDigits(scal);
        String rates = num.format(rate);
        return rates;
    }

    // 邮箱验证
    public static boolean emailFormat(String email) {
        Pattern pattern = Pattern
                .compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
        Matcher mc = pattern.matcher(email);
        return mc.matches();
    }


    //判断字符串是不是以数字开头?
    public static boolean isStartWithNumber(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str.charAt(0) + "");
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }


    /**
     * 判断字符串是否是数字
     */
    public static boolean isNumber(String value) {
        return isInteger(value) || isDouble(value);
    }

    /**
     * 判断字符串是否是整数
     */
    public static boolean isInteger(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * 判断字符串是否是浮点数
     */
    public static boolean isDouble(String value) {
        try {
            Double.parseDouble(value);
            if (value.contains("."))
                return true;
            return false;
        } catch (NumberFormatException e) {
            return false;
        }

    }

    public static Bitmap createQRCode(String str, int widthAndHeight) throws WriterException {
        Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        hints.put(EncodeHintType.MARGIN, "1");
        BitMatrix matrix = new MultiFormatWriter().encode(str,
                BarcodeFormat.QR_CODE, widthAndHeight, widthAndHeight);
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        int[] pixels = new int[width * height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (matrix.get(x, y)) {
                    pixels[y * width + x] = BLACK;
                } else {
                    pixels[y * width + x] = WHITE;
                }
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(width, height,
                Bitmap.Config.ARGB_8888);
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return bitmap;
    }

    /**
     * 将Base64位编码的图片进行解码
     */
    public static Bitmap base2Image(String base64Data/*,String imgName,String path*/) {
        byte[] bytes = Base64.decode(base64Data, Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        return bitmap;
    }

    /**
     * 手机号格式化
     */
    public static String phoneto(String phonenum) {

        if (!TextUtils.isEmpty(phonenum) && phonenum.length() > 6) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < phonenum.length(); i++) {
                char c = phonenum.charAt(i);
                if (i >= 3 && i <= 6) {
                    sb.append('*');
                } else {
                    sb.append(c);
                }
            }

            return sb.toString();
        }
        return null;
    }

    /*
     * 将银行卡中间八个字符隐藏为*
     */
    public static String getHideBankCardNum(String bankCardNum) {
        try {
            if (bankCardNum == null) return "";

            int length = bankCardNum.length();

            if (length > 4) {
                String startNum = bankCardNum.substring(0, 4);
                String endNum = bankCardNum.substring(length - 4, length);
                bankCardNum = startNum + " **** **** " + endNum;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bankCardNum;
    }

    /*
     * 将银行卡中间八个字符隐藏为*
     */
    public static String getHideIDCardNum(String IDCardNum) {
        try {
            if (IDCardNum == null) return "";

            int length = IDCardNum.length();

            if (length > 6) {
                String startNum = IDCardNum.substring(0, 6);
                String endNum = IDCardNum.substring(length - 6, length);
                IDCardNum = startNum + " ******** " + endNum;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return IDCardNum;
    }

    /**
     * 保留8位小数，去除多余0
     *
     * @param value
     * @return
     */
    public static String double_2(double value) {
//        return new DecimalFormat("#0.00").format(value);
        String s=new DecimalFormat("#0.00000000").format(value);
        if (s.isEmpty()) {
            return "";
        }
        if(s.indexOf(".") > 0){
            s = s.replaceAll("0+?$", "");//去掉多余的0
            s = s.replaceAll("[.]$", "");//如最后一位是.则去掉
        }
        return s;
    }

    /**
     * 保留8位小数，去除多余0
     *
     * @param value
     * @return
     */
    public static String double_8(double value) {
//        return new DecimalFormat("#0.00000000").format(value);
        String s=new DecimalFormat("#0.00000000").format(value);
        if (s.isEmpty()) {
            return "";
        }
        if(s.indexOf(".") > 0){
            s = s.replaceAll("0+?$", "");//去掉多余的0
            s = s.replaceAll("[.]$", "");//如最后一位是.则去掉
        }
        return s;
    }

    /*
     * 将金钱字符隐藏为*
     */
    public static String getHideMoneyNum(String moneyNum) {
        String res="";
        try {
            if (StringUtils.isEmpty(moneyNum)) return "";
            int length = moneyNum.length();
            for(int i=0;i<length;i++){
                res+="*";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     *  手机号空格格式化
     * @param phonenum  手机号
     * @param m 首次保留的位数
     * @param n 每格添加的数据
     * @return
     */
    public static String getPhoneNum (String phonenum,int m,int n){
        if (!TextUtils.isEmpty(phonenum)){
            StringBuffer hehe = new StringBuffer(phonenum);
            int j=0;
            while (m+n*j<phonenum.length()) {
                hehe.insert(m+(n+1)*j," ");
                j++;
            }
            return hehe.toString();
        }
      return  "";
    }

    /**
     * 保留八位，去除多余0
     * @param value
     * @return
     */
    public static String rvZeroAndDot(double value){
        String s=new DecimalFormat("#0.00000000").format(value);
        if (s.isEmpty()) {
            return "";
        }
        if(s.indexOf(".") > 0){
            s = s.replaceAll("0+?$", "");//去掉多余的0
            s = s.replaceAll("[.]$", "");//如最后一位是.则去掉
        }
        return s;
    }
}

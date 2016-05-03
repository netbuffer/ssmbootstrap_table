package cn.com.ttblog.ssmbootstrap_table;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestPinyin4j {
	
	private static Logger logger = LoggerFactory.getLogger(TestMyBatis.class);

    /** 
     * 提取每个汉字的首字母 
     * @param str 
     * @return String 
     */
    public static String getPinYinHeadChar(String str) {  
        String convert = "";  
        for (int j = 0; j < str.length(); j++) {  
            char word = str.charAt(j);  
            // 提取汉字的首字母  
            String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);  
            if (pinyinArray != null) {  
                convert += pinyinArray[0].charAt(0);  
            } else {  
                convert += word;  
            }  
        }  
        return convert;  
    }  
    
	/**
	 * 提取汉字的全拼
	 * @param str
	 * @return
	 */
    public static String getPinYin(String str) {  
    	HanyuPinyinOutputFormat format=new HanyuPinyinOutputFormat();
		format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		format.setVCharType(HanyuPinyinVCharType.WITH_V);
		try {
			return PinyinHelper.toHanYuPinyinString(str,format, "",true);
		} catch (BadHanyuPinyinOutputFormatCombination e) {
			e.printStackTrace();
		}
		return "";
    }
    
	@Test
	public void testSystemProperties() {
		logger.debug("pinyin:{}\r\n",getPinYin("联系人a"));
		logger.debug("toHanyuPinyinStringArray:{}\r\n",PinyinHelper.toHanyuPinyinStringArray('天').length);
		logger.debug("getPinYinHeadChar:{}\r\n",getPinYinHeadChar("百度网络"));
	}
}

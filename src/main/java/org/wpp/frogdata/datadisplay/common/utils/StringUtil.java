package org.wpp.frogdata.datadisplay.common.utils;

import org.apache.commons.lang3.StringUtils;
import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class StringUtil {

	private static final String SYMBOLS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

	private static final SimpleDateFormat billNoTimestampFormat = new SimpleDateFormat("yyMMddHHmmssSSS");

	private static final Random RANDOM = new SecureRandom();//java.security.SecureRandom

	public static String subStringByLength(String str, int length) {
		if (StringUtils.isNotBlank(str)) {
			if (str.length() <= length) {
				return str;
			} else {
				return str.substring(0, length);
			}
		} else {
			return "";
		}
	}

	public static String replaceNullString(Object str) {
		if (str == null) return "";
		else return str.toString();
	}

	public static String parseNumberWith2Decimals(Double d) {
		DecimalFormat df = new DecimalFormat("######0.00");
		if (d == null) {
			return "0.00";
		} else {
			return df.format(d);
		}
	}

	public static void main(String[] args) {
		/*ExecutorService executor = Executors.newFixedThreadPool(2);
		Set<String> set = new HashSet<>();
		Runnable runnable = () -> {
			long start = System.currentTimeMillis();
			for (int i = 0; i < 100000; i++) {
				String r = generateDateId();
				synchronized (set) {
					if (!set.contains(r)) {
						set.add(r);
					} else {
						System.out.println(r);
					}
				}
			}
			System.out.println("耗时: " + (System.currentTimeMillis() - start));
			System.out.println("数量: " + set.size());
		};
		executor.execute(runnable);
		executor.execute(runnable);
		executor.shutdown();*/
		System.out.println(getContractNo("9652"));
	}

	public static String generateId() {

		char[] nonceChars = new char[32];

		for (int index = 0; index < nonceChars.length; ++index) {

			nonceChars[index] = SYMBOLS.charAt(RANDOM.nextInt(SYMBOLS.length()));

		}
		return new String(nonceChars);

	}

	public static String generateId(int length) {

		char[] nonceChars = new char[length];

		for (int index = 0; index < nonceChars.length; ++index) {

			nonceChars[index] = SYMBOLS.charAt(RANDOM.nextInt(SYMBOLS.length()));

		}
		return new String(nonceChars);

	}

	public static String getContractNo(String merId) {

		char[] nonceChars = new char[8];

		for (int index = 0; index < nonceChars.length; ++index) {

			nonceChars[index] = SYMBOLS.charAt(RANDOM.nextInt(SYMBOLS.length()));

		}
		String str = new String(nonceChars);
		String date = DateUtil.format2yyyyMMdd(DateUtil.getCurrentDate());

		return date + merId + str;

	}

    public static String trim(String str) {
        if (StringUtils.isNotBlank(str)) {
            return str.trim();
        }
        return str;
    }

	private static final int SEQUENCE_BOUND = 1000;
	private static final int RANDOM_BOUND = 100;
	private static int sequence;

	public static String generateDateId() {
		synchronized (billNoTimestampFormat) {
			sequence = (sequence + 1) % SEQUENCE_BOUND;
			return billNoTimestampFormat.format(new Date())
					+ String.format("%05d", (int) (Math.random() * RANDOM_BOUND) * SEQUENCE_BOUND + sequence);
		}
	}
}

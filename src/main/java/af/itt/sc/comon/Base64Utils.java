/**
 * Copyright (C) 2018 Tang.
 */
package af.itt.sc.comon;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

/**
 * Base64Utilsクラス。
 *
 * @author tang
 * @version 1.00 2018/07/26
 *
 */
public class Base64Utils {

	/**
	 * 入力されたデータをBASE64変換する。
	 *
	 * @param in 変換対象データ
	 * @return BASE64文字列
	 * @throws IOException
	 */
	public static String base64Encode(InputStream in) throws IOException {
		byte[] data = null;
		data = new byte[in.available()];
		in.read(data);
		in.close();
		return Base64.getEncoder().withoutPadding().encodeToString(data);
	}

	/**
	 * 入力されたBASE64をデータ変換する。
	 *
	 * @param in BASE64文字列
	 * @return 変換対象データ
	 * @throws IOException
	 */

	public static void base64Decode(String strBase64, String filename) throws IOException {
		filename = filename + ".pdf";
		FileOutputStream fos = new FileOutputStream(new File(filename));
		try {
			byte[] bytes = Base64.getDecoder().decode(strBase64);
			fos.write(bytes);
		}catch(IOException ex) {

        }finally {
            fos.close();
        }
	}

}

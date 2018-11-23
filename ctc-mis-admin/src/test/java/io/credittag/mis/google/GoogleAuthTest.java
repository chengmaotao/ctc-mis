package io.credittag.mis.google;

import org.junit.Test;

import io.credittag.mis.common.utils.GoogleAuthenticator;

/* 
 * Not really a unit test- but it shows usage 
 */
public class GoogleAuthTest {

	@Test
	public void genSecretTest() {
		for (int i = 0; i < 14; i++) {
			String secret = GoogleAuthenticator.generateSecretKey();
			String url = GoogleAuthenticator.getQRBarcodeURL("admin", "localhost", secret);
			// String qrcode = GoogleAuthenticator.getQRBarcode("2816661736@qq.com",
			// secret);
			System.out.println( secret);
		}
	}

	// Change this to the saved secret from the running the above test.
	static String savedSecret = "XLZZ7ZEPK5IB55W3";

	@Test
	public void authTest() {
		// enter the code shown on device. Edit this and run it fast before the
		// code expires!
		long code = 184679;
		long t = System.currentTimeMillis();
		GoogleAuthenticator ga = new GoogleAuthenticator();
		ga.setWindowSize(5); // should give 5 * 30 seconds of grace...
		boolean r = ga.check_code(savedSecret, code, t);
		System.out.println("Check code = " + r);
	}
}
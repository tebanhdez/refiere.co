package co.refiere.services.qrcode;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.decoder.Mode;
import com.google.zxing.qrcode.encoder.ByteMatrix;
import com.google.zxing.qrcode.encoder.QRCode;




public class QrCodeTest {

	@Test
	public void test1() {

		QRCode qrCode = new QRCode();
		String expected = "<<\n" + " mode: null\n" + " ecLevel: null\n" + " version: null\n" + " maskPattern: -1\n"
				+ " matrix: null\n" + ">>\n";
		assertEquals(expected, qrCode.toString());
	}

	@Test
	public void test2() {
		
		QRCode qrCode = new QRCode();
		qrCode.setMode(Mode.BYTE);
		qrCode.setECLevel(ErrorCorrectionLevel.H);
		qrCode.setMaskPattern(3);
		ByteMatrix matrix = new ByteMatrix(21, 21);
		for (int y = 0; y < 21; ++y) {
			for (int x = 0; x < 21; ++x) {
				matrix.set(x, y, (y + x) % 2);
			}
		}

	}

	@Test
	public void test3() {
		assertFalse(QRCode.isValidMaskPattern(-1));
		assertTrue(QRCode.isValidMaskPattern(0));
		assertTrue(QRCode.isValidMaskPattern(7));
		assertFalse(QRCode.isValidMaskPattern(8));
	}

}

		 
	
	


	

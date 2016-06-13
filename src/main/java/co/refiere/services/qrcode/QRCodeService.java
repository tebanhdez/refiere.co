package co.refiere.services.qrcode;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.google.zxing.qrcode.QRCodeWriter;

import co.refiere.resources.base.QRCodeRequest;
import co.refiere.services.mailer.MailService;
import co.refiere.services.mailer.ResourceManager;

import javax.imageio.ImageIO;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class QRCodeService {
    private static final Log LOGGER = LogFactory.getLog(MailService.class);
    static Properties QRCodeProperties;
    static File QRFile;
    
    public QRCodeService(){
        QRCodeProperties = new Properties();
        QRFile = new File("qrRefiere.png");
        try {
            QRCodeService.QRCodeProperties.load(ResourceManager.getResourceAsInputStream("qrcodeservice.properties"));
        } catch (IOException e) {
            LOGGER.error(e);
        }
    }
    
    
    public void generateQRCode(QRCodeRequest qrRequest) {
        String text = QRCodeProperties.getProperty("qr.code.text");
        text = String.format(text, qrRequest.getIdPerson(), qrRequest.getIdCampaign());
        try {
            generateQR(QRFile, text, 300, 300);
        } catch (Exception e) {
            LOGGER.error(e);
        }

    }

    public File generateQR(File file, String text, int h, int w) throws Exception {

        QRCodeWriter writer = new QRCodeWriter();
        BitMatrix matrix = writer.encode(text, com.google.zxing.BarcodeFormat.QR_CODE, w, h);

        BufferedImage image = new BufferedImage(matrix.getWidth(), matrix.getHeight(), BufferedImage.TYPE_INT_RGB);
        image.createGraphics();

        Graphics2D graphics = (Graphics2D) image.getGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, matrix.getWidth(), matrix.getHeight());
        graphics.setColor(Color.BLACK);

        for (int i = 0; i < matrix.getWidth(); i++) {
            for (int j = 0; j < matrix.getHeight(); j++) {
                if (matrix.get(i, j)) {
                    graphics.fillRect(i, j, 1, 1);
                }
            }
        }

        ImageIO.write(image, "png", file);

        return file;

    }

    public String decoder(File file) throws Exception {

        FileInputStream inputStream = new FileInputStream(file);

        BufferedImage image = ImageIO.read(inputStream);

        int width = image.getWidth();
        int height = image.getHeight();
        int[] pixels = new int[width * height];

        LuminanceSource source = new BufferedImageLuminanceSource(image);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

        // decode the barcode
        QRCodeReader reader = new QRCodeReader();
        Result result = reader.decode(bitmap);
        return new String(result.getText());
    }
}

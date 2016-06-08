package co.refiere.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import co.refiere.resources.base.QRCodeRequest;
import co.refiere.services.qrcode.QRCodeService;

@Path("v1/qrcode")
public class QRCodeGeneratorResource {
    
    @POST
    @Path("/generate")
    @Consumes(MediaType.APPLICATION_JSON)
    public void generateQrCode(QRCodeRequest qrCodeRequest){
        QRCodeService qrCodeService = new QRCodeService();
        qrCodeService.generateQRCode(qrCodeRequest); 
    }

}

import jp.ne.paypay.ApiClient;
import jp.ne.paypay.ApiException;
import jp.ne.paypay.Configuration;
import jp.ne.paypay.api.PaymentApi;
import jp.ne.paypay.model.MoneyAmount;
import jp.ne.paypay.model.QRCode;
import jp.ne.paypay.model.QRCodeDetails;
import java.util.UUID;


public class DynamicQrWebinarMain {
    public static void main(String[] args) throws ApiException {
        ApiClient apiClient = createApiClient();
        createQRCode(apiClient);
    }

    private static void createQRCode(ApiClient apiClient) throws ApiException {
        // Creating the payload to create a QR Code, additional parameters can be added basis the API Documentation
        QRCode qrCode = new QRCode();
        qrCode.setAmount(new MoneyAmount().amount(10).currency(MoneyAmount.CurrencyEnum.JPY));
        UUID uuid = UUID.randomUUID();
        qrCode.setMerchantPaymentId(uuid.toString());
        qrCode.setCodeType("ORDER_QR");
        qrCode.setOrderDescription("Mune's Favourite Cake");
        qrCode.isAuthorization(false);
        qrCode.setRedirectUrl("https://paypay.ne.jp/");
        //For Deep Link, RedirectTypeEnum.APP_DEEP_LINK
        qrCode.setRedirectType(QRCode.RedirectTypeEnum.WEB_LINK);
        qrCode.setUserAgent("Mozilla/5.0 (iPhone; CPU iPhone OS 10_3 like Mac OS X) AppleWebKit/602.1.50 (KHTML, like Gecko) CriOS/56.0.2924.75 Mobile/14E5239e Safari/602.1");

        // Calling the method to create a qr code
        PaymentApi apiInstance = new PaymentApi(apiClient);
        QRCodeDetails response = apiInstance.createQRCode(qrCode);
        // Printing if the method call was SUCCESS
        System.out.println(response);
    }

    private static ApiClient createApiClient() throws ApiException {
        ApiClient apiClient = new Configuration().getDefaultApiClient();
        apiClient.setProductionMode(false);
        apiClient.setApiKey("a_L8PIcikokp_K8GQ");
        apiClient.setApiSecretKey("ZP0heFgOW8jC4fT++MIxnLiPuvwVgmrALvKWx2VhKQU=");
        apiClient.setAssumeMerchant("563070414751072256");
        return apiClient;
    }
}

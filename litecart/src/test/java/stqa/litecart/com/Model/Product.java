package stqa.litecart.com.Model;

import java.io.File;
import java.util.Calendar;
import java.util.Date;

public class Product {

    Calendar calendar = Calendar.getInstance();
    private boolean generalStatus;
    private String generalName;
    private String generalCode;
    private String generalCategorie;
    private String generalGender;
    private String generalQuantity;
    private File generalImg;
    private Date generalDateFrom;
    private Date generalDateTo;
    private String infoManufacture;
    private String infoKeyword;
    private String infoShortDesc;
    private String infoDesc;
    private String infoHeadTitle;
    private String infoMetaDesc;
    private String pricesPurchasePrice;
    private String pricesPurchaseUnit;
    private String pricesPriceUSD;
    private String pricesPriceTaxUSD;
    private String pricesPriceEUR;
    private String pricesPriceTaxEUR;

    public Product(boolean generalStatus, String generalName, String generalCode, String generalCategorie, String generalGender, String generalQuantity, File generalImg, String infoManufacture, String infoKeyword, String infoShortDesc, String infoDesc, String infoHeadTitle, String infoMetaDesc, String pricesPurchasePrice, String pricesPurchaseUnit, String pricesPriceUSD, String pricesPriceTaxUSD, String pricesPriceEUR, String pricesPriceTaxEUR) {
        this.generalStatus = generalStatus;
        this.generalName = generalName;
        this.generalCode = generalCode;
        this.generalCategorie = generalCategorie;
        this.generalGender = generalGender;
        this.generalQuantity = generalQuantity;
        this.generalImg = generalImg;
        this.infoManufacture = infoManufacture;
        this.infoKeyword = infoKeyword;
        this.infoShortDesc = infoShortDesc;
        this.infoDesc = infoDesc;
        this.infoHeadTitle = infoHeadTitle;
        this.infoMetaDesc = infoMetaDesc;
        this.pricesPurchasePrice = pricesPurchasePrice;
        this.pricesPurchaseUnit = pricesPurchaseUnit;
        this.pricesPriceUSD = pricesPriceUSD;
        this.pricesPriceTaxUSD = pricesPriceTaxUSD;
        this.pricesPriceEUR = pricesPriceEUR;
        this.pricesPriceTaxEUR = pricesPriceTaxEUR;
        this.generalDateFrom = calendar.getTime();
        calendar.add(Calendar.YEAR, 1);
        this.generalDateTo = calendar.getTime();
    }

    public boolean isGeneralStatus() {
        return generalStatus;
    }

    public String getGeneralName() {
        return generalName;
    }

    public String getGeneralCode() {
        return generalCode;
    }

    public String getGeneralCategorie() {
        return generalCategorie;
    }

    public String getGeneralGender() {
        return generalGender;
    }

    public String getGeneralQuantity() {
        return generalQuantity;
    }

    public File getGeneralImg() {
        return generalImg;
    }

    public Date getGeneralDateFrom() {
        return generalDateFrom;
    }

    public Date getGeneralDateTo() {
        return generalDateTo;
    }

    public String getInfoManufacture() {
        return infoManufacture;
    }

    public String getInfoKeyword() {
        return infoKeyword;
    }

    public String getInfoShortDesc() {
        return infoShortDesc;
    }

    public String getInfoDesc() {
        return infoDesc;
    }

    public String getInfoHeadTitle() {
        return infoHeadTitle;
    }

    public String getInfoMetaDesc() {
        return infoMetaDesc;
    }

    public String getPricesPurchasePrice() {
        return pricesPurchasePrice;
    }

    public String getPricesPurchaseUnit() {
        return pricesPurchaseUnit;
    }

    public String getPricesPriceUSD() {
        return pricesPriceUSD;
    }

    public String getPricesPriceTaxUSD() {
        return pricesPriceTaxUSD;
    }

    public String getPricesPriceEUR() {
        return pricesPriceEUR;
    }

    public String getPricesPriceTaxEUR() {
        return pricesPriceTaxEUR;
    }
}

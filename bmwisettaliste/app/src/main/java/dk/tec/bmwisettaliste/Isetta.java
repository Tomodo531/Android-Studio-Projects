package dk.tec.bmwisettaliste;

public class Isetta {

    int bidle;
    String ovreskrift;
    String detaljer;

    public Isetta(int bdlId, String ovreskrift, String detaljer) {
        this.bidle = bdlId;
        this.ovreskrift = ovreskrift;
        this.detaljer = detaljer;
    }

    public int getBidle() {
        return bidle;
    }

    public void setBidle(int bidle) {
        this.bidle = bidle;
    }

    public String getOvreskrift() {
        return ovreskrift;
    }

    public void setOvreskrift(String ovreskrift) {
        this.ovreskrift = ovreskrift;
    }

    public String getDetaljer() {
        return detaljer;
    }

    public void setDetaljer(String detaljer) {
        this.detaljer = detaljer;
    }
}

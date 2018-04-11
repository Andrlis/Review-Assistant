package bsuirAPI;

public class BsuirParserFactory {

    private enum dataType {XML, JSON}

    private static dataType dType = dataType.XML;

    public static BsuirParserInterface getParser(){
        switch (dType){
            case XML:
                return new BsuirXmlParser();
            case JSON:
                return new BsuirJsonParser();
            default:
                return null;
        }
    }
}